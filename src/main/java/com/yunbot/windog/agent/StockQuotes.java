package com.yunbot.windog.agent;

import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.yunbot.windog.agent.StartBot.callPython;
import redis.clients.jedis.Jedis;

/**
 * @author William
 */
public class StockQuotes {

    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    public static boolean quotesEnable = false;          //开盘更新行情后置为true

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StockQuotes.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

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

        //gs_token = "38971d3a1c984329bbf977a24ecdaaef0a2dd2845d7caab7f6447ec2";
        //gs_dburl = "jdbc:mysql://127.0.0.1:3306/yunbot?characterEncoding=UTF-8";
        //gs_user = "root";
        //gs_pwd = "welcome";

        ExecuteQuotesThread startThread = new ExecuteQuotesThread();
        startThread.setUrl(gs_dburl);
        startThread.setUser(gs_user);
        startThread.setPwd(gs_pwd);
        Thread myQuotesThread = new Thread(startThread);
        myQuotesThread.start();

    }

    //启动线程类 
    static class ExecuteQuotesThread implements Runnable {

        private String s_url;
        private String s_user;
        private String s_pwd;

        public void setUrl(String s_url) {
            this.s_url = s_url;
        }

        public void setUser(String s_user) {
            this.s_user = s_user;
        }

        public void setPwd(String s_pwd) {
            this.s_pwd = s_pwd;
        }

        public void run() {
            int iLoop = 1;
            while (true) {
                //工作日//开盘时间
                if (StartBot.isOpening() && StartBot.inOperation()) {
                    logger.info("获取行情数据[" + iLoop + "]...");
//                    updateQuotes();
                    callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_quotes.py");
                    logger.info("获取行情数据[" + iLoop + "]完毕.");
                    iLoop++;
                    //如果连续获取三次行情，则开始改变状态标记
                    //与之相关的策略线程将开始更新策略
                    if (iLoop > 3) {
                        quotesEnable = true;
                    } else if (iLoop == 2) {
                        StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","启动机器操盘..."));
                        //清理redis
                        Jedis jedis = new Jedis("127.0.0.1",6379);
                        jedis.flushDB();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StockQuotes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Thread.sleep(2000);
                        if (quotesEnable) {
                            logger.info("当前已经休市，暂停获取行情数据.");
                        }
                        quotesEnable = false;
                        iLoop = 1;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StockQuotes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        public void updateQuotes() {
            //策略更新需要一些时间，等所有策略都计算一轮之后，策略标记更新为true，则开始执行如下流程
            logger.info("----------------------------");
            StockRT.gi_thrd = 2;
            StockRT.main(null);
            int iWaitMax = 120;
            while ((0 < StockRT.gi_thrd) && (0 < iWaitMax)) {
                try {
                    Thread.sleep(1000);
                    iWaitMax--;
                } catch (InterruptedException ex) {
                    Logger.getLogger(StartBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (0 < StockRT.gi_thrd) {
                logger.error("StockRT thread is exited with error: " + StockRT.gi_thrd);
            }
        }

    }

}
