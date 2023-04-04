/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yunbot.windog.agent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yunbot.windog.common.conf.DBConfig;
import com.yunbot.windog.common.conf.V2Config;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class StartBot {

    static String gs_dburl;
    static String gs_user;
    static String gs_pwd;
    static String gs_token;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StartBot.class);

    public static void startBotAgent() {

//        StartBot.sendToWeixin("text", "[系统消息]  系统开始运行...");
        sendToWeixinMD(MsgUtils.getNormalInfo("系统信息","系统已经启动"));
        logger.info("YunBot is running...");

        gs_dburl = DBConfig.getUrl();
        if (("".equals(gs_dburl))) {
            logger.warn("url is null.");
        }

        gs_user = DBConfig.getUsername();
        if (("".equals(gs_user))) {
            logger.warn("username is null.");
        }

        gs_pwd = DBConfig.getPassword();
        if (("".equals(gs_pwd))) {
            logger.warn("password is null.");
        }
        gs_token = V2Config.getToken();
        if (("".equals(gs_token))) {
            logger.warn("token is null.");
        }
        //gs_token = "38971d3a1c984329bbf977a24ecdaaef0a2dd2845d7caab7f6447ec2";
        //gs_dburl = "jdbc:mysql://127.0.0.1:3306/yunbot?characterEncoding=UTF-8";
        //gs_user = "root";
        //gs_pwd = "welcome";

        //启动实时行情系统
        StockQuotes.ExecuteQuotesThread startThread1 = new StockQuotes.ExecuteQuotesThread();
        startThread1.setUrl(gs_dburl);
        startThread1.setUser(gs_user);
        startThread1.setPwd(gs_pwd);
        Thread myQuotesThread = new Thread(startThread1);
        myQuotesThread.start();

        //启动策略更新系统
        StockPolicy.ExecutePolicyThread startThread2 = new StockPolicy.ExecutePolicyThread();
        startThread2.setUrl(gs_dburl);
        startThread2.setUser(gs_user);
        startThread2.setPwd(gs_pwd);
        Thread myPolicyThread = new Thread(startThread2);
        myPolicyThread.start();

        //启动机器人交易系统
        ExchangeThread ycBot = new ExchangeThread();
        Thread myBotThread;
        myBotThread = new Thread(ycBot);
        myBotThread.start();

    }

    //今天是否为开盘日
    public static boolean isOpening() {
        MysqlObj stmt = new MysqlObj(gs_dburl, gs_user, gs_pwd, false);
        try {
            ResultSet ds_cal = stmt.executeQuery("select * from stock_cal where opendt=DATE_FORMAT(NOW(),'%Y%m%d')");
            try {
                if (ds_cal.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        } finally {
            stmt.close();
        }

    }

    //判断当前时间是否开盘
    public static boolean inOperation() {
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        String curTime = sd.format(new Date());
        if (((0 > "09:30:00".compareTo(curTime)) && (0 < "11:30:00".compareTo(curTime)))
                || ((0 > "13:00:00".compareTo(curTime)) && (0 < "15:00:00".compareTo(curTime)))) {
            return true;
        } else {
            return false;
        }
    }

    //机器人启动线程类
    static class ExchangeThread implements Runnable {

        @Override
        public void run() {
            logger.info("交易系统开始启动...");
            //循环更新
            int iWaitMax = 0;       //等待超时时间
            String curTime = "";    //当前时间
            SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
            //日志埋点计数器
            int iLoop = 0;
            while (true) {
                //每个循环更新
                curTime = sd.format(new Date());
                //工作日//开盘时间，最后有两分钟不做交易
                //这样做的目的是:防止在最后两分钟买卖操盘无法执行完毕拖延到交易截至时间以后，产生不合理交易记录
                if ((isOpening() && (((0 > "09:30:00".compareTo(curTime)) && (0 < "11:28:00".compareTo(curTime)))
                        || ((0 > "13:00:00".compareTo(curTime)) && (0 < "14:58:00".compareTo(curTime)))))) {
                    iLoop++;
                    //进入交易时段，先必须满足策略得到了更新
                    //策略首先是没有更新的，在等待行情系统更新几次之后，交易线程会触发策略开始更新
                    if (!StockPolicy.policyEnable) {
                        if (StockQuotes.quotesEnable) {
                            logger.info("等待策略更新完毕后执行操盘交易[" + iLoop + "]...");
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    //策略更新需要一些时间，等所有策略都计算一轮之后，策略标记更新为true，则开始执行如下流程
                    logger.info("----------------------------");
                    logger.info("检测模型买入[" + iLoop + "]...");
                    ModelBuy.gi_thrd = 2;
                    ModelBuy.main(null);
                    iWaitMax = 120;
                    while ((0 < ModelBuy.gi_thrd) && (0 < iWaitMax)) {
                        try {
                            Thread.sleep(1000);
                            iWaitMax--;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (0 < ModelBuy.gi_thrd) {
                        logger.error("ModelBuy thread is exited with error: " + ModelBuy.gi_thrd);
                    }
                    logger.info("检测模型买入完毕[" + iLoop + "].");
                    logger.info("----------------------------");
                    logger.info("检测模型卖出[" + iLoop + "]...");
                    ModelSell.gi_thrd = 2;
                    ModelSell.main(null);
                    iWaitMax = 120;
                    while ((0 < ModelSell.gi_thrd) && (0 < iWaitMax)) {
                        try {
                            Thread.sleep(1000);
                            iWaitMax--;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (0 < ModelSell.gi_thrd) {
                        logger.error("ModelSell thread is exited with error: " + ModelSell.gi_thrd);
                    }
                    logger.info("检测模型卖出完毕[" + iLoop + "].");
                    logger.info("----------------------------");
                    logger.info("检测用户买入[" + iLoop + "]...");
                    UserBuy.gi_thrd = 2;
                    UserBuy.main(null);
                    iWaitMax = 120;
                    while ((0 < UserBuy.gi_thrd) && (0 < iWaitMax)) {
                        try {
                            Thread.sleep(1000);
                            iWaitMax--;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (0 < UserBuy.gi_thrd) {
                        logger.error("StockBuy thread is exited with error: " + UserBuy.gi_thrd);
                    }
                    logger.info("检测用户买入完毕[" + iLoop + "].");
                    logger.info("----------------------------");
                    logger.info("检测用户卖出[" + iLoop + "]...");
                    UserSell.gi_thrd = 2;
                    UserSell.main(null);
                    iWaitMax = 120;
                    while ((0 < UserSell.gi_thrd) && (0 < iWaitMax)) {
                        try {
                            Thread.sleep(1000);
                            iWaitMax--;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (0 < UserSell.gi_thrd) {
                        logger.error("StockSell thread is exited with error: " + UserSell.gi_thrd);
                    }
                    logger.info("检测用户卖出完毕[" + iLoop + "].");
                    logger.info("----------------------------");
                } else {
                    if (iLoop > 10) {
                        StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","系统停止挂单..."));
                    }
                    logger.info("操盘系统暂停运行...");
                    //重置计数器
                    iLoop = 0;
                }
                //如果行情标记false，策略标记true，则说明可以执行收盘操作了
                //执行完收盘流程，再将策略标记变更为false，这样可确保收盘流程不会重复执行
                if ((!StockQuotes.quotesEnable) && (StockPolicy.policyEnable)) {
                    logger.info("-----------执行收盘流程--------------");
                    StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","执行收盘分析..."));
                    //等待60秒以后，再获取一次行情，然后执行收盘流程
                    //防止部分股票数据未获得及时更新
                    try {
                        Thread.sleep(1000 * 60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    logger.info("收盘前更新行情数据...");
                    callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_quotes.py");
                    logger.info("收盘前更新行情数据完毕.");
                    //更新日历
                    logger.info("收盘前更新交易日历数据...");
                    callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_cal.py");
                    logger.info("收盘前更新交易日历数据完毕.");
                    //更新日数据
                    logger.info("收盘前更新日线数据...");
                    callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_daily.py");
                    logger.info("收盘前更新日线数据完毕.");
                    //更新日数据
                    logger.info("收盘前更新行业数据...");
                    callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_industry.py");
                    logger.info("收盘前更新行业数据完毕.");

                    logger.info("执行收盘分析...");
                    StockClose.main(null);
                    iWaitMax = 120;
                    while ((StockClose.gb_isrun) && (0 < iWaitMax)) {
                        try {
                            Thread.sleep(1000);
                            iWaitMax--;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (!StockClose.gb_isrun) {
                        logger.info("StockData thread is finished!");
                    } else {
                        logger.error("StockData thread is exited with error!");
                    }
                    logger.info("执行收盘分析完毕.");
                    //防止重复执行收盘,策略标记随行情变更为true，随收盘结束变更为false
                    StockPolicy.policyEnable = false;
                    //重置
                    iLoop = 0;
                }
                //等待两秒，执行下一轮操盘检查
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //发送信息到微信群
    public static void sendToWeixin(String type, String msg) {
        HashMap info = new HashMap();
        info.put("content", msg + " [" + String.format("%tT", new Date()) + "]");
        JSONObject res = new JSONObject();
        res.put("msgtype", type);
        res.put(type, info);
//        logger.info(res.toString());
        //云博
//        sendPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=b3ccc1e2-1532-435c-bc19-e89fe593a027", res.toString());
        //赢财
//        HttpRequest.sendPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=de5ddf4d-7f8b-49e0-bc44-dbfd0332444d", res.toString());
    }

    //markdown格式
    public static void sendToWeixinMD(String msg)  {
        JSONObject markdown = new JSONObject();
        markdown.put("content", msg);
        JSONObject reqBody = new JSONObject();
        reqBody.put("msgtype", "markdown");
        reqBody.put("markdown", markdown);
        reqBody.put("safe", 0);
        //云博
//        HttpRequest.sendPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=b3ccc1e2-1532-435c-bc19-e89fe593a027", reqBody.toString());
        //赢财
        HttpRequest.sendPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=de5ddf4d-7f8b-49e0-bc44-dbfd0332444d", reqBody.toString());
    }

    //执行外部python脚本
    public static void callPython(String python, String program) {
        Process proc;
        try {
            File srcFile = new File(program);
            if (!srcFile.exists()) {
                logger.warn("脚本文件不存在：" + program);
                return;
            }
            proc = Runtime.getRuntime().exec(python + " " + program);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String line = null;
            logger.info("Executing " + program + " ...");
            while ((line = stdIn.readLine()) != null) {
                logger.info(line);
            }
            while ((line = stdErr.readLine()) != null) {
                logger.info("error:" + line);
            }
            logger.info("Executing " + program + " finished.");
            stdIn.close();
            stdErr.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
