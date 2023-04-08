package com.yunbot.windog.agent;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static mgrDaemon.SendMail.autoReplyMail;

import com.yunbot.windog.common.conf.DBConfig;
import com.yunbot.windog.common.conf.V2Config;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author William
 */
public class StockClose {

    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    static boolean gb_isrun;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StockClose.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        gb_isrun = true;
        logger.info("Stock data analysis job is running...");

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

//        gs_dburl = "jdbc:mysql://127.0.0.1:3306/yunbot?characterEncoding=UTF-8";
//        gs_user = "root";
//        gs_pwd = "welcome";

        //找到最新的日数据
        MysqlObj mysqlObj = new MysqlObj(gs_dburl, gs_user, gs_pwd, true);
        try {
            doClosingWork(mysqlObj);
            gb_isrun = false;
        } finally {
            mysqlObj.close();
            gb_isrun = false;
        }
    }

    //执行收盘操作
    static public void doClosingWork(MysqlObj stmt) {
        double fundsPercent = 0.1;
        double stopWin = 0;
        double stopLost = 0;
        int execPolicy = 0;
        int stockCount = 1;
        String bestUser = "";    //表现最好的用户
        //新增个人收益计算2019-2-22
        stmt.execute("REPLACE INTO user_profit (uid, profit, curdt) "
                + "SELECT a.uid, 100*((a.funds + sum(b.amount*c.cur_price) - a.funds_init)/a.funds_init) profit, "
                + "CURDATE() from user_info a, user_hold b , stock_pool c "
                + "where a.uid=b.uid and b.code=c.code "
                + "GROUP BY a.name, a.uid");
        //结束
        StringBuilder smailMsg = new StringBuilder();
        smailMsg.append("<html><body><table border=\"1\">"
                + "<tr><th>代码</th><th>名称</th><th>价格</th><th>策略</th><th>行业</th></tr>");

        ResultSet rs_data;
        for (int i = 1; i <= 15; i++) {
            logger.info("Get KPI" + i + " result ...");
            rs_data = stmt.executeQuery("select * from stock_pool "
                    + "where (vol" + i + " > 0 and grade > 0) "
                    + " order by vol" + i + " DESC limit 20");
            try {
                while (rs_data.next()) {
                    smailMsg.append("<tr>"
                            + "<td>" + rs_data.getString("code") + "</td>"
                            + "<td>" + rs_data.getString("cname") + "</td>"
                            + "<td>" + rs_data.getString("cur_price") + "</td>"
                            + "<td>" + i + "</td>"
                            + "<td>" + rs_data.getString("description") + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                logger.error("Get KPI" + i + " result is error!");
            }
        }

        smailMsg.append("</table></body></html>");
        //[五日收益测试结果]
        smailMsg.append("<br><br>-----------[五日收益测试结果]-----------<br><br><html><body><table border=\"1\">"
                + "<tr><th>名称</th><th>策略</th><th>止盈</th><th>止损</th><th>五日收益比</th>");
        stmt.execute("delete from bot_profit5 where curdt=CURDATE()");
        stmt.execute("insert INTO bot_profit5(uid,curdt,name,policy,win,lost, benefit)"
                + "(select a.uid,CURDATE(),b.name,b.policy,b.win,b.lost, sum(aa)/sum(bb)*100 svalue from ("
                + "select b.uid, (a.cur_price - b.price) * b.amount aa, b.price*b.amount bb "
                + " from stock_pool a, bot_hold b "
                + "where a.code=b.code and date_sub(now(),interval 1 week) < b.cur_date"
                + " UNION "
                + " select uid, (price - in_price)*amount aa , in_price*amount bb "
                + " from bot_transaction "
                + " where date_sub(now(),interval 1 week) < in_date "
                + " ) a, bot_info b "
                + " WHERE a.uid=b.uid "
                + " group by a.uid,b.name,b.policy,b.win,b.lost "
                + "order by svalue DESC)");
        rs_data = stmt.executeQuery("select * from bot_profit5 where curdt=CURDATE() "
                + " order by benefit DESC, (win - lost) DESC limit 20");
        int iord = 101;
        double dbenefit = 0.0;
        MysqlObj stexec = null;
        ResultSet rstmp = null;
        try {
            stexec = new MysqlObj(gs_dburl, gs_user, gs_pwd, true);
            while (rs_data.next()) {
                smailMsg.append("<tr>"
                        + "<td>" + rs_data.getString("name") + "</td>"
                        + "<td>" + rs_data.getInt("policy") + "</td>"
                        + "<td>" + rs_data.getString("win") + "</td>"
                        + "<td>" + rs_data.getString("lost") + "</td>"
                        + "<td>" + String.format("%.3f", rs_data.getDouble("benefit")) + "</td>"
                        + "</tr>");
                //所有正收益都参与排名
                if (rs_data.getDouble("benefit") > 0) {
                    if (dbenefit != rs_data.getDouble("benefit")) {
                        iord--;
                        dbenefit = rs_data.getDouble("benefit");
                    }
                    if (0 < iord) {
                        stexec.executeUpdate("update bot_profit5 set ord="
                                + String.valueOf(iord) + " where sid=" + rs_data.getString("sid"));
                    }
                }
            }
        } catch (SQLException ex) {
            logger.error("Get KPI result is error!");
        }

        //新增涨跌比计算2022-6-7
        try {
            //上涨和平盘的，都记为分子
            rs_data = stmt.executeQuery("select count(*) upCount from stock_pool where lastend <= cur_price and vtotal > 0");
            if (rs_data.next()) {
                fundsPercent = rs_data.getDouble("upCount");
            }
            //所有开盘交易的股票，停牌的除外
            rs_data = stmt.executeQuery("select count(*) openCount from stock_pool where vtotal > 0");
            if (rs_data.next()) {
                fundsPercent = fundsPercent / rs_data.getDouble("openCount");
                logger.info("计算市场涨跌比：" + fundsPercent);
                StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("市场信息","今日市场行情", "热度详情",
                        "指  数", String.format("%.2f", fundsPercent)));
            }
        } catch (SQLException ex) {
            logger.error("计算市场涨跌比失败!");
            return;
        }
        //四舍五入
        stockCount = (int) Math.round(fundsPercent * 10);
        fundsPercent = stockCount * 0.1;

        try {
            rs_data = stmt.executeQuery("select * from bot_profit5 where curdt=CURDATE() "
                    + " order by benefit DESC, win-lost DESC");
            if (rs_data.next()) {
                //选出最好的策略
                stopWin = rs_data.getDouble("win");
                stopLost = rs_data.getDouble("lost");
                execPolicy = rs_data.getInt("policy");
                //5日收益大于10个百分点,满仓
                if (1 < fundsPercent) {
                    fundsPercent = 1.0;
                    stockCount = 10;
                }
                //5日收益小于1个百分点,至少持仓10%
                if (0.1 > fundsPercent) {
                    fundsPercent = 0.1;
                    stockCount = 1;
                }
                StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","近五日最优账户", "账户概要",
                        "账  号", rs_data.getString("uid"),
                        "收  益", String.format("%.2f", rs_data.getFloat("benefit"))+ "%"));
                stexec.execute("insert into bot_policy_his(curdt, funds_percent, win, lost, policy) values"
                        + "(now(), " + fundsPercent + "," + stopWin
                        + "," + stopLost + "," + rs_data.getInt("policy") + ")");
                stexec.executeUpdate("update bot_policy set value="
                        + execPolicy + " where name='POLICY'");
                stexec.executeUpdate("update bot_policy set value="
                        + stopWin + " where name='WIN'");
                stexec.executeUpdate("update bot_policy set value="
                        + stopLost + " where name='LOST'");

                smailMsg.append("</table></body></html>");
                smailMsg.append("<br><br>-----------[最优方案]-----------<br><br>");
                smailMsg.append("<br>策略------" + execPolicy + "<br>");
                smailMsg.append("<br>仓位------" + String.format("%.3f", fundsPercent) + "<br>");
                smailMsg.append("<br>支数------" + stockCount + "<br>");
                smailMsg.append("<br>止赢------" + String.format("%.3f", stopWin) + "<br>");
                smailMsg.append("<br>止损------" + String.format("%.3f", stopLost) + "<br>");
//                StartBot.sendToWeixin("text", "[最优方案]  策略:" + execPolicy + "  仓位:" + String.format("%.3f", fundsPercent)
//                        + "  支数:" + stockCount + "  止赢:" + String.format("%.3f", stopWin) + "  止损:" + String.format("%.3f", stopLost));
                StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","近五日最优策略", "策略详情", "策  略", String.valueOf(execPolicy),
                        "仓  位", String.format("%.2f", fundsPercent * 100) + "%",
                        "持股数", String.valueOf(stockCount) + "支",
                        "止  赢", String.format("%.2f", stopWin) + "%",
                        "止  损", String.format("%.2f", stopLost) + "%"));
            }
        } catch (SQLException ex) {
            logger.error("Get KPI result is error!");
        }

        //[累计收益测试结果]
        smailMsg.append("<br><br>-----------[累计收益测试结果]-----------<br><br><html><body><table border=\"1\">"
                + "<tr><th>名称</th><th>运行天数</th><th>累计收益比</th>");
        //删除
        stmt.execute("delete from bot_profit where curdt=CURDATE()");
        //插入
        stmt.execute("insert into bot_profit(uid, nick, curdt, days, benefit)"
                + "SELECT a.uid, a.nick, CURDATE(),"
                + "TIMESTAMPDIFF(DAY,a.register_dt,CURDATE()) days, "
                + "((a.funds + sum(b.amount*c.cur_price) - a.funds_init)/a.funds_init)*100 svalue "
                + "from bot_info a, bot_hold b , stock_pool c "
                + " where a.uid=b.uid and b.code=c.code "
                + "GROUP BY a.uid, a.nick ORDER BY svalue DESC");
        //获取
        rs_data = stmt.executeQuery("select * from bot_profit where curdt=CURDATE() ORDER BY benefit DESC limit 20");
        try {
            while (rs_data.next()) {
                if (rs_data.isFirst()) {
                    bestUser = rs_data.getString("uid");
//                    StartBot.sendToWeixin("text", "[累计最优]  账户：" + rs_data.getString("nick")
//                            + "  天数：" + rs_data.getInt("days") + "  收益：" + String.format("%.3f", rs_data.getDouble("benefit")));
//                    StartBot.sendToWeixinMD(MsgUtils.getNormalInfo("操盘信息","近五日最优账户", "账户概要",
//                            "账  号", bestUser,
//                            "账户名", rs_data.getString("nick"),
//                            "天  数", rs_data.getString("days") + "天",
//                            "收  益", String.format("%.2f", rs_data.getFloat("benefit"))+ "%"));
                    //更新推送消息的最优账号
                    stexec.executeUpdate("update bot_info set alarm=0 where alarm=1");
                    stexec.executeUpdate("update bot_info set alarm=1 where uid=" + bestUser);
                }
                smailMsg.append("<tr>"
                        + "<td>" + rs_data.getString("nick") + "</td>"
                        + "<td>" + rs_data.getInt("days") + "</td>"
                        + "<td>" + String.format("%.3f", rs_data.getDouble("benefit")) + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            logger.error("Get KPI result is error!");
        }
        smailMsg.append("</table></body></html>");
        if (autoReplyMail(V2Config.getEmail_receiver(), smailMsg.toString())) {
            logger.info("Send mail to administrotor successful.");
        } else {
            logger.error("Send mail to administrotor failed.");
        }
        //增加微信输出
        String stock_hold = "当前持仓：\n|股票|价格|收益|时间|\n";
        rs_data = stmt.executeQuery("select cname,price, benefit,cur_date from bot_hold_view where uid=" + bestUser);    // + bestUser
        try {
            while (rs_data.next()) {
                stock_hold = stock_hold + "|" + rs_data.getString("cname") + "|" + rs_data.getString("price") + "|";
                stock_hold = stock_hold + String.format("%.1f", rs_data.getDouble("benefit")) + "|" + rs_data.getString("cur_date").substring(8, 19) + "|\n";
            }
        } catch (SQLException ex) {
            logger.error("Get hold stock list error!");
            //Logger.getLogger(StockData.class.getName()).log(Level.SEVERE, null, ex);
        }

        StartBot.sendToWeixinMD(MsgUtils.getStockInfo(stmt, bestUser));

        //////////////////////////////////
        //执行redis行情保存
        //上午收盘保存上午的行情到文件中
        //下午收盘保存下午的行情，追加到文件中
        //万一出部分数据丢失的情况，可根据行进行补齐
        //可通在不同的环境中开启两个采集端，对于不完整的数据进行补齐
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        List tmLst = getMinuteList(new Date());
        List stockLst = getStockList(stmt);
        HashMap<String, String> stocksMap = new HashMap();
        String curTm = "";
        String curStock = "";
        String stockinfo = "";
        JSONObject json;
        logger.info("开始保存行情数据到文件中...");
        for (int i = 0; i < tmLst.size(); i++) {
            curTm = tmLst.get(i).toString();
            for (int j = 0; j < stockLst.size(); j++) {
                curStock = stockLst.get(j).toString();
                stockinfo = jedis.get(curTm + curStock);
                if (null != stockinfo) {
                    stocksMap.put(curStock, stockinfo);
                }
            }
            if (!stocksMap.isEmpty()) {
                json = new JSONObject(stocksMap);
                writeLogData(curTm + ":" + json);
            } else {
                logger.warn("数据不完整，请通过其他数据文件补齐:" + curTm);
            }
            stocksMap.clear();
        }
        logger.info("保存行情数据到文件中完成.");
        jedis.save();
        stmt.close();
        //标记执行完毕
        gb_isrun = false;
        logger.info("Stock close thread is exit.");
    }

    // 写数据文件，在当前目录的data目录下，创建以日期为名称的数据文件
    // 一行表示开盘的某一分钟全体股票的行情信息
    public static boolean writeLogData(String data) {
        String saveFilePath = System.getProperty("user.dir") + File.separator + "data" + File.separator;
        String dateNow = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File(saveFilePath + dateNow + ".dat");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(data);
//        pw.println("\r\n");
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    //获取开盘的每一分钟信息，上午从0930——1130，下午从1300——1500
    public static List getMinuteList(Date curDt) {
        List minList = new ArrayList();
        minList.clear();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begDt = null;
        try {
            if (formatter.format(curDt).compareTo("1200") < 0) {
                begDt = format.parse("2023-01-01 09:30:00");
                for (int i = 0; i <= 120; i++) {
                    minList.add(formatter.format(begDt.getTime() + 60000 * i));
                }
            } else {
                begDt = format.parse("2023-01-01 13:00:00");
                for (int i = 0; i <= 120; i++) {
                    minList.add(formatter.format(begDt.getTime() + 60000 * i));
                }
            }
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
        return minList;
    }

    //获取全体股票的代码，包含sh、sz、bj
    public static List getStockList(MysqlObj stmt) {
        List stockList = new ArrayList();
        stockList.clear();
        ResultSet rs_data = stmt.executeQuery("select code from stock_pool order by code asc");
        try {
            while (rs_data.next()) {
                stockList.add(rs_data.getString("code"));
            }
        } catch (SQLException ex) {
            logger.error("Get stock list error!");
        }
        return stockList;
    }

}