package com.yunbot.windog.agent;

import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.yunbot.windog.agent.StartBot.callPython;

/**
 * @author William
 */
public class StockPolicy {

    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    public static boolean policyEnable = false;       //开盘更新策略后置为true

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StockPolicy.class);

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

        //策略线程，开启后持续运行，监控市场热度，如果热度发生变化，则重新激活选股流程
        ExecutePolicyThread startThread = new ExecutePolicyThread();
        startThread.setUrl(gs_dburl);
        startThread.setUser(gs_user);
        startThread.setPwd(gs_pwd);
        Thread myPolicyThread = new Thread(startThread);
        myPolicyThread.start();

    }

    //启动线程类 
    static class ExecutePolicyThread implements Runnable {

        private String s_url;
        private String s_user;
        private String s_pwd;
        //上一次计算的涨跌别对应的持股数量值
        //如果当次循环计算的持股数量与该值一致，则不调整仓位，不计激活选股策略重算
        private int lastCount = 0;

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
            //涨跌比
            float fundsPercent;
            //应持股数量
            int stockCount = 0;
            MysqlObj stmt;
            ResultSet rs_data;
            //标记程序执行的流程逻辑，反应执行策略的情况
            //可通过日志埋点分析，判断该线程是否正常工作
            int iLoop = 1;
            while (true) {
                //涨跌比初始值，每次重置，重新计算
                fundsPercent = 0.1f;
                //工作日//开盘时间
                if (StartBot.isOpening() && StartBot.inOperation()) {
                    if (!StockQuotes.quotesEnable) {
                        logger.info("*****等待行情获取完毕后执行策略分析...");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    //如果行情热度变化，则触发信号计算
                    // 2023-01-20
                    //新增涨跌比计算2022-6-7
                    stmt = new MysqlObj(s_url, s_user, s_pwd, true);
                    try {
                        //开盘时，连续三次更新行情后标记变为true，且策略标记为false--》修改lastCount=0--》触发策略执行--》修改策略标记为true--》激活买卖操盘流程
                        //收盘时，行情标记置为false且策略标记为true--》触发收盘流程--》修改策略标记为false
                        if (!policyEnable) {
                            lastCount = 0;
                        }
                        //重新计算涨跌比
                        rs_data = stmt.executeQuery("select count(*) upCount from stock_pool where lastend <= cur_price and vtotal > 0");
                        if (rs_data.next()) {
                            fundsPercent = rs_data.getFloat("upCount");
                        }
                        rs_data = stmt.executeQuery("select count(*) openCount from stock_pool where vtotal > 0");
                        if (rs_data.next()) {
                            fundsPercent = fundsPercent / rs_data.getFloat("openCount");
                            logger.info("*****计算市场涨跌比[" + iLoop + "]为: " + String.format("%.3f", fundsPercent));
                        }
                        //四舍五入，根据涨跌比，计算应持股数量
                        stockCount = (int) Math.round(fundsPercent * 10);
                        //涨跌比小数整理，保留一位小数
                        fundsPercent = stockCount * 0.1f;
                        //线程首次启动，需先获取当前持股初始值，然后执行选股策略
                        //否则根据热度变化触发策略执行
                        if (0 != lastCount) {
                            //差距到两只，则调仓
                            //william独创————狗绳算法
                            //2023-02-01
                            if (2 > Math.abs(lastCount - stockCount)) {
                                //执行完毕等待30秒
                                try {
                                    Thread.sleep(1000 * 30);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                logger.info("*****计算市场热度[" + iLoop + "]为: " + fundsPercent + "  建议持股数: " + lastCount);
                                continue;
                            } else {
                                //两者差达到2
                                if (0 < (lastCount - stockCount)) {
                                    stockCount = lastCount - 1;     //说明要减仓，那么每次仅仅-1
//                                    StartBot.sendToWeixin("text", "[交易提示]  市场热度降低至[" + String.format("%.3f", stockCount * 0.1f) + "]，触发减仓操作...");
                                    StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","市场热度降低...", "热度详情",
                                            "指  数", String.format("%.2f", stockCount * 0.1f),
                                            "建  议", "执行减仓", "info"));
                                } else {
                                    stockCount = lastCount + 1;     //说明要加仓，那么每次仅仅+1
//                                    StartBot.sendToWeixin("text", "[交易提示]  市场热度升高至[" + String.format("%.3f", stockCount * 0.1f) + "]，触发加仓操作...");
                                    StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","市场热度升高...", "热度详情",
                                            "指  数", String.format("%.2f", stockCount * 0.1f),
                                            "建  议", "执行加仓", "warning"));
                                }
                                //重置持仓
                                fundsPercent = stockCount * 0.1f;

                                //股票只数也根据仓位变化,否则增加仓位可能买不进股票
                                stmt.executeUpdate("update bot_policy set value="
                                        + stockCount + " where name='STOCK_COUNT'");
                                logger.info("*****计算市场热度[" + iLoop + "]变化，调整持股数：从 " + lastCount + " 到 " + stockCount);
                                //调仓
                                stmt.executeUpdate("update bot_policy set value="
                                        + fundsPercent + " where name='FUNDS_PERCENT'");
                                //更新上一次持股数量
                                lastCount = stockCount;
                            }
                        } else {
                            rs_data = stmt.executeQuery("select value from bot_policy where name='STOCK_COUNT'");
                            if (rs_data.next()) {
                                lastCount = rs_data.getInt("value");
                            }
                            StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","开盘操作建议", "建议详情",
                                    "仓  位", String.format("%.2f", stockCount * 10.1f) + "%",
                                    "持股数", String.valueOf(stockCount) + "支"));
                        }
                        logger.info("*****触发执行策略分析[" + iLoop + "]...");
                        //去掉选股标记
                        if (stmt.executeUpdate("update stock_pool set vol1=0, vol2=0, vol3=0, vol4=0, vol5=0, vol6=0, vol7=0, vol8=0, vol9=0, vol10=0, vol11=0, vol12=0, vol13=0, vol14=0, vol15=0")) {
                            logger.info("*****重置所有策略的选股池标记[" + iLoop + "]...");
                        } else {
                            logger.error("*****重置所有策略的选股池标记[" + iLoop + "]失败！");
                            return;
                        }
                        //只在开始时取一次
                        if (!policyEnable) {
//                            //更新日历
//                            callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb_cal.py");
//                            //更新日数据
//                            callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb-daily.py");
                        }
                        //执行指标计算
                        logger.info("*****开始计算所有策略选股[" + iLoop + "]...");
//                        StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","执行策略选股..."));
                        for (int i = 1; i <= 15; i++) {
                            callPython("python.exe", System.getProperty("user.dir") + File.separator + "python" + File.separator + "yb-" + i + ".py");
                        }
                        logger.info("*****计算完毕所有策略选股[" + iLoop + "]...");
                        //确保策略更新方可交易
                        policyEnable = true;
                        iLoop++;
                    } catch (SQLException ex) {
                        logger.error("*****执行策略分析[" + iLoop + "]失败：" + ex.getMessage());
                        return;
                    } finally {
                        stmt.close();
                    }

                } else {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    if (policyEnable) {
//                        logger.info("*****收盘期间策略标记状态为true----------");
//                    }
                    iLoop = 1;
                }

            }

        }

    }

}
