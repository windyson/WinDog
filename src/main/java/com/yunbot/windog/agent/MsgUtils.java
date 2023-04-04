package com.yunbot.windog.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MsgUtils {

    private static Logger logger = LoggerFactory.getLogger(MsgUtils.class);

//    public static String getNormalMsg(String uid) {
//       String markdownMsg =
//        "测试：您的会议室已经预定，稍后会同步到`邮箱` \n" +
//        "    >**事项详情** \n" +
//        "    >事　项：<font color=\"info\">开会</font> \n" +
//        "    >组织者：@admin \n" +
//        "    >参与者：@admin \n" +
//        "    >\n" +
//        "    >会议室：<font color=\"info\">广州TIT 1楼 301</font>\n" +
//        "    >日　期：<font color=\"warning\">2022年4月22日</font>\n" +
//        "    >时　间：<font color=\"comment\">上午9:00-11:00</font>\n" +
//        "    >\n" +
//        "    >请准时参加会议。\n";
//    }

    //持仓行情
    public static String getStockInfo(MysqlObj stmt, String uid) {
        String sTitle = "";
        String sBody = "";
        String sTag = "";
        ResultSet rsTmp = stmt.executeQuery("select * from bot_profit_view where uid=" + uid);
        try {
            if (rsTmp.next()) {
                if (0 < rsTmp.getFloat("svalue")) {
                    sTag = "warning";
                } else {
                    sTag = "info";
                }
                sTitle = String.format("[操盘信息] 累计最优账户持仓 \n" +
                        "    >**持仓详情** \n" +
                        "    >账  号：<font color=\"comment\">%s</font> \n" +
                        "    >账户名：<font color=\"comment\">%s</font> \n" +
                        "    >交易日：<font color=\"comment\">%s天</font> \n" +
                        "    >总盈亏：<font color=\"%s\">%.2f%% </font> \n" +
                        "    >————————————\n",
                        uid, rsTmp.getString("nick"),
                        rsTmp.getString("days"),
                        sTag,
                        rsTmp.getFloat("svalue"));
            }

            rsTmp = stmt.executeQuery("select * from bot_hold_view where uid=" + uid);
            while (rsTmp.next()) {
                if (0 < rsTmp.getFloat("benefit")) {
                    sTag = "warning";
                } else {
                    sTag = "info";
                }
                sBody = sBody + String.format(
                                "    >代  码：<font color=\"comment\">%s</font>\n" +
                                "    >名  称：<font color=\"comment\">%s</font>\n" +
                                "    >买入时：<font color=\"comment\">%s</font>\n" +
                                "    >数  量：<font color=\"comment\">%s</font>\n" +
                                "    >买入价：<font color=\"comment\">%s</font>\n" +
                                "    >当前价：<font color=\"comment\">%s</font>\n" +
                                "    >涨跌幅：<font color=\"%s\">%.2f%%</font>\n" +
                                "    >————————————\n",
                        rsTmp.getString("code"),
                        rsTmp.getString("cname"),
                        rsTmp.getString("cur_date"),
                        rsTmp.getString("amount"),
                        rsTmp.getString("price"),
                        rsTmp.getString("cur_price"),
                        sTag,
                        rsTmp.getFloat("benefit"));
            }

        } catch (SQLException ex) {
            logger.error("Select stock from pool is error.");
        }
        return sTitle + sBody + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    //买入信息
    public static String getBuyInfo(String act, String code, String name, String cdt, float cprice, int amt) {
        String sTitle = String.format("[操盘信息] 机器人买入股票 \n" +
                "    >**交易详情** \n" +
                "    >账  号：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", act);
        String sBody = "";
        String sTag = "";
        sBody = sBody + String.format(
                        "    >代  码：<font color=\"comment\">%s</font>\n" +
                        "    >名  称：<font color=\"comment\">%s</font>\n" +
                        "    >买入时：<font color=\"comment\">%s</font>\n" +
                        "    >买入价：<font color=\"comment\">%.2f</font>\n" +
                        "    >数  量：<font color=\"comment\">%d</font>\n" +
                        "    >————————————\n",
                code,
                name,
                cdt,
                cprice,
                amt);
        return sTitle + sBody + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    //卖出信息
    public static String getSellInfo(String act, String code, String name, String cdt, float iprice, int amt,
                                     String odt, float oprice, String reson, float benefit) {
        String sTitle = String.format("[操盘信息] 机器人卖出股票 \n" +
                "    >**交易详情** \n" +
                "    >账  号：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", act);
        String sBody = "";
        String sTag = "";
        if (0 < benefit) {
            sTag = "warning";
        } else {
            sTag = "info";
        }
        sBody = sBody + String.format(
                        "    >代  码：<font color=\"comment\">%s</font>\n" +
                        "    >名  称：<font color=\"comment\">%s</font>\n" +
                        "    >买入时：<font color=\"comment\">%s</font>\n" +
                        "    >买入价：<font color=\"comment\">%.2f</font>\n" +
                        "    >数  量：<font color=\"comment\">%d</font>\n" +
                        "    >卖出时：<font color=\"comment\">%s</font>\n" +
                        "    >卖出价：<font color=\"comment\">%.2f</font>\n" +
                        "    >原  因：<font color=\"comment\">%s</font>\n" +
                        "    >盈  亏：<font color=\"%s\">%.2f%%</font>\n" +
                        "    >————————————\n",
                code, name, cdt,  iprice, amt, odt, oprice, reson, sTag, benefit);
        return sTitle + sBody + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    //卖出信息
    public static String getNormalInfo(String type, String title, String label, String item, String value) {
        String sTitle = String.format("[%s] %s \n" +
                "    >**%s** \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", type, title, label, item, value);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    public static String getNormalInfo(String type, String title, String label, String item1, String value1, String item2, String value2) {
        String sTitle = String.format("[%s] %s \n" +
                "    >**%s** \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", type, title, label, item1, value1, item2, value2);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    //带颜色显示，支持warning红 info绿
    public static String getNormalInfo(String type, String title, String label, String item1, String value1, String item2, String value2, String cols) {
        String sTitle = String.format("[%s] <font color=\"%s\">%s</font>\n" +
                "    >**<font color=\"%s\">%s</font>** \n" +
                "    >%s：<font color=\"%s\">%s</font> \n" +
                "    >%s：<font color=\"%s\">%s</font> \n" +
                "    >————————————\n", type, cols, title, cols, label, item1, cols, value1, item2, cols, value2);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    public static String getNormalInfo(String type, String title, String label, String item1, String value1, String item2, String value2, String item3, String value3) {
        String sTitle = String.format("[%s] %s \n" +
                "    >**%s** \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", type, title, label, item1, value1, item2, value2, item3, value3);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    public static String getNormalInfo(String type, String title, String label, String item1, String value1, String item2, String value2,
                                       String item3, String value3,  String item4, String value4) {
        String sTitle = String.format("[%s] %s \n" +
                "    >**%s** \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", type, title, label, item1, value1, item2, value2, item3, value3, item4, value4);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    public static String getNormalInfo(String type, String title, String label, String item1, String value1, String item2, String value2,
                                       String item3, String value3, String item4, String value4, String item5, String value5) {
        String sTitle = String.format("[%s] %s \n" +
                "    >**%s** \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >%s：<font color=\"comment\">%s</font> \n" +
                "    >————————————\n", type, title, label, item1, value1, item2, value2, item3, value3, item4, value4, item5, value5);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

    //卖出信息
    public static String getNormalInfo(String type, String title) {
        String sTitle = String.format("[%s] %s \n" +
                "    >————————————\n", type, title);
        return sTitle + "    > 系统时间[" + String.format("%tT", new Date()) + "]";
    }

}
