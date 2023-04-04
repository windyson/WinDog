package com.yunbot.windog.agent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;

/**
 *
 * @author William
 */
public class StockRT {

    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";

    static int gi_thrd = 0;
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(StockRT.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        logger.info("Stock real-time job is running...");

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

        gs_dburl = "jdbc:mysql://127.0.0.1:3306/yunbot?characterEncoding=UTF-8";
        gs_user = "root";
        gs_pwd = "welcome";

        //gs_task1,gs_task2表示不同线程要做的数据抓取id
        String gs_task1 = "";
        String gs_task2 = "";
        //取数据
        try {
            // TODO add your handling code here:
            MysqlObj mysqlobj = new MysqlObj(gs_dburl, gs_user, gs_pwd, false);
            ResultSet rs_data = mysqlobj.executeQuery("select * from stock_pool");
            int i_flag = 1;   //标记
            while (rs_data.next()) {

                if (1 == i_flag) {
                    gs_task1 = gs_task1 + rs_data.getString("CODE") + ",";
                    i_flag = 2;
                } else if (2 == i_flag) {
                    gs_task2 = gs_task2 + rs_data.getString("CODE") + ",";
                    i_flag = 1;
                }
            }
            mysqlobj.close();
        } catch (SQLException ex) {
            logger.error("Get stock list error.");
        }
        startSQLthread startthread1 = new startSQLthread();
        startthread1.setURL(gs_dburl);
        startthread1.setUSER(gs_user);
        startthread1.setPWD(gs_pwd);
        startthread1.setTask(gs_task1);
        startthread1.setTid(1);
        Thread mysqlthread1 = new Thread(startthread1);
        mysqlthread1.start();

        startSQLthread startthread2 = new startSQLthread();
        startthread2.setURL(gs_dburl);
        startthread2.setUSER(gs_user);
        startthread2.setPWD(gs_pwd);
        startthread2.setTask(gs_task2);
        startthread2.setTid(2);
        Thread mysqlthread2 = new Thread(startthread2);
        mysqlthread2.start();
    }

    //启动线程类 
    static class startSQLthread implements Runnable {

        private String s_url;
        private String s_user;
        private String s_pwd;
        private String s_task;
        private int i_thread = 0;

        public void setURL(String s_url) {
            this.s_url = s_url;
        }

        public void setUSER(String s_user) {
            this.s_user = s_user;
        }

        public void setPWD(String s_pwd) {
            this.s_pwd = s_pwd;
        }

        public void setTask(String s_task) {
            this.s_task = s_task;
        }

        public void setTid(int i_thread) {
            this.i_thread = i_thread;
        }

        public void run() {
//            logger.info("Thread " + String.valueOf(this.i_thread) + " is executing...");
//            logger.info("Thread " + String.valueOf(this.i_thread) + " connecting to mysql...");
            MysqlObj stmt = null;
            stmt = new MysqlObj(s_url, s_user, s_pwd, false);
//            logger.info("Thread " + String.valueOf(this.i_thread) + " connected mysql successful.");
            String[] strArray = null;
            strArray = this.s_task.split(",");
            for (int i = 0; i < strArray.length; i++) {
                //System.out.println(strArray[i]);
                if (!"".equals(strArray[i])) {
                    if (true == InsertStockQuotesTX(stmt, strArray[i])) {
//                        logger.info("Thread " + String.valueOf(this.i_thread) + " refresh stock quotes " + strArray[i] + " successful.");
                    } else {
                        logger.error("Thread " + String.valueOf(this.i_thread) + " refresh stock quotes " + strArray[i] + " failed.");
                    }
                }
            }
            logger.info("Thread " + String.valueOf(this.i_thread) + " is finished!");
            stmt.close();
            synchronized (this) {
                gi_thrd--;
            }
        }

        //执行一次插入
        public boolean InsertStockQuotesWY(MysqlObj stmt, String s_code) {
            String s_sql = "";
            String s_price = "0";
            String s_date = "";
            String s_time = "";
            String wycode = "";
            //http://api.money.126.net/data/feed/0601398%2Cmoney.api
            if (s_code.contains("sh")) {
                wycode = "0" + s_code.substring(2, 8);
            } else if (s_code.contains("sz")) {
                wycode = "1" + s_code.substring(2, 8);
            } else {
                wycode = "2" + s_code.substring(2, 8);
            }
            String s_html = HttpRequest.urlConnectionPost("http://api.money.126.net/data/feed/" + wycode, "%2Cmoney.api").toString();
//            System.out.println(s_html);
            //System.out.println("list=" + s_html);
            if ((100 > s_html.length()) || (!s_html.contains("{\"" + wycode + "\":{"))) {
                logger.error("该股票已退市或接口出错:" + s_code);
                return false;
            }
            s_html = s_html.substring(21, s_html.length() - 4);
//            System.out.println(s_html);
            com.alibaba.fastjson.JSONObject rtJson = new com.alibaba.fastjson.JSONObject();
            rtJson = com.alibaba.fastjson.JSONObject.parseObject(s_html);
            s_price = rtJson.getJSONObject(wycode).getString("price");
            //停牌的不更新价格
            if ("0.000".contains(s_price)) {
                s_price = rtJson.getJSONObject(wycode).getString("yestclose");
            }
            s_date = rtJson.getJSONObject(wycode).getString("time").substring(0, rtJson.getJSONObject(wycode).getString("time").indexOf(" "));
            s_time = rtJson.getJSONObject(wycode).getString("time").substring(rtJson.getJSONObject(wycode).getString("time").indexOf(" ") + 1);
            if (0 > s_time.compareTo("09:30:00")) {
                s_time = "09:30:00";
            } else if ((0 < s_time.compareTo("11:30:00")) && (0 > s_time.compareTo("12:00:00"))) {
                s_time = "11:30:00";
            } else if (0 < s_time.compareTo("15:00:00")) {
                s_time = "15:00:00";
            }
            s_date = s_date + " " + s_time;
            s_sql = "insert into stock_quotes(code, cur_date, "
                    + "col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, "
                    + "col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, "
                    + "col21, col22, col23, col24, col25, col26, col27, col28, col29) values('"
                    + s_code + "','" + s_date + "',"
                    + rtJson.getJSONObject(wycode).getFloat("open") + "," + rtJson.getJSONObject(wycode).getFloat("yestclose") + ","
                    + rtJson.getJSONObject(wycode).getFloat("price") + "," + rtJson.getJSONObject(wycode).getFloat("high") + ","
                    + rtJson.getJSONObject(wycode).getFloat("low") + "," + rtJson.getJSONObject(wycode).getFloat("ask1") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid1") + "," + rtJson.getJSONObject(wycode).getFloat("volume") + ","
                    + rtJson.getJSONObject(wycode).getFloat("turnover") + "," + rtJson.getJSONObject(wycode).getFloat("askvol1") + ","
                    + rtJson.getJSONObject(wycode).getFloat("ask1") + "," + rtJson.getJSONObject(wycode).getFloat("askvol2") + ","
                    + rtJson.getJSONObject(wycode).getFloat("ask2") + "," + rtJson.getJSONObject(wycode).getFloat("askvol3") + ","
                    + rtJson.getJSONObject(wycode).getFloat("ask3") + "," + rtJson.getJSONObject(wycode).getFloat("askvol4") + ","
                    + rtJson.getJSONObject(wycode).getFloat("ask4") + "," + rtJson.getJSONObject(wycode).getFloat("askvol5") + ","
                    + rtJson.getJSONObject(wycode).getFloat("ask5") + "," + rtJson.getJSONObject(wycode).getFloat("bidvol1") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid1") + "," + rtJson.getJSONObject(wycode).getFloat("bidvol2") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid2") + "," + rtJson.getJSONObject(wycode).getFloat("bidvol3") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid3") + "," + rtJson.getJSONObject(wycode).getFloat("bidvol4") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid4") + "," + rtJson.getJSONObject(wycode).getFloat("bidvol5") + ","
                    + rtJson.getJSONObject(wycode).getFloat("bid5") + ")";
//            System.out.println(s_sql);

//            //行情太大,不保存了
//            stmt.execute(s_sql);
//            boolean b_flag1 = stmt.execute(s_sql);  //true;
            boolean b_flag1 = true;
            boolean b_flag2 = true;
            if (0 < Double.valueOf(rtJson.getJSONObject(wycode).getFloat("open"))) {
                //by willia 2019-9-3
                if ((rtJson.getJSONObject(wycode).getString("name").contains("*")) || (rtJson.getJSONObject(wycode).getString("name").contains("ST"))) {
                    b_flag2 = stmt.executeUpdate("update stock_pool set cur_price=" + s_price
                            + ", update_dt = '" + s_date
                            + "', cname = '" + rtJson.getJSONObject(wycode).getString("name")
                            + "', vbeg = " + rtJson.getJSONObject(wycode).getFloat("open")
                            + ", vend = " + rtJson.getJSONObject(wycode).getFloat("price")
                            + ", lastend = " + rtJson.getJSONObject(wycode).getFloat("yestclose")
                            + ", vhigh = " + rtJson.getJSONObject(wycode).getFloat("high")
                            + ", vlow = " + rtJson.getJSONObject(wycode).getFloat("low")
                            + ", vtotal = " + rtJson.getJSONObject(wycode).getFloat("volume")
                            + ", vamount = " + rtJson.getJSONObject(wycode).getFloat("turnover")
                            + ", grade = -3" //调成垃圾股
                            + " where code='" + s_code + "'");
                } else {
                    b_flag2 = stmt.executeUpdate("update stock_pool set cur_price=" + s_price
                            + ", update_dt = '" + s_date
                            + "', cname = '" + rtJson.getJSONObject(wycode).getString("name")
                            + "', vbeg = " + rtJson.getJSONObject(wycode).getFloat("open")
                            + ", vend = " + rtJson.getJSONObject(wycode).getFloat("price")
                            + ", lastend = " + rtJson.getJSONObject(wycode).getFloat("yestclose")
                            + ", vhigh = " + rtJson.getJSONObject(wycode).getFloat("high")
                            + ", vlow = " + rtJson.getJSONObject(wycode).getFloat("low")
                            + ", vtotal = " + rtJson.getJSONObject(wycode).getFloat("volume")
                            + ", vamount = " + rtJson.getJSONObject(wycode).getFloat("turnover")
                            + " where code='" + s_code + "'");
                }
            } else {
                logger.info("Code " + s_code + " is closed!");
            }
            if ((true == b_flag1) && (true == b_flag2)) {
                return stmt.executeCommit();
            } else {
                stmt.executeRollback();
                return false;
            }
        }

        /*请求url获取返回的内容*/
        public static String getHttpsResult(HttpURLConnection connection) {
            StringBuffer buffer = new StringBuffer();
            //将返回的输入流转换成字符串  
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "gbk"));
                {
                    String str = null;
                    while ((str = bufferedReader.readLine()) != null) {
                        buffer.append(str);
                    }
                    String result = buffer.toString();
                    return result;
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
            return "";
        }

        //https://qt.gtimg.cn/q=sh600021
        //v_sh600021="1~上海电力~600021~8.92~8.50~8.65~748555~403056~345500~8.91~916~8.90~808~8.89~193~8.88~690~8.87~990~8.92~1532~8.93~358~8.94~5834~8.95~5943~8.96~2478~~20220429155913~0.42~
        //4.94~9.03~8.32~8.92/748555/654924491~748555~65492~2.86~-11.33~~9.03~8.32~8.35~233.45~233.45~1.61~9.35~7.65~1.04~-12548~8.75~57.60~-12.33~~~1.36~65492.4491~0.0000~0~ 
        //~GP-A~-30.42~-3.36~1.23~-11.63~-1.29~16.68~6.64~2.18~-10.26~-30.20~2617164219~2617164219~-63.56~31.76~2617164219~";
        public boolean InsertStockQuotesTX(MysqlObj stmt, String s_code) {
            String s_sql = "";
            String s_price = "0";
            String s_date = "";
            String s_time = "";
            String s_html = "";
            String dateTemp = "";
            URL serverUrl = null;
            try {
                serverUrl = new URL("https://qt.gtimg.cn/q=" + s_code);
                HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                //必须设置false，否则会自动redirect到重定向后的地址  
                conn.setInstanceFollowRedirects(false);
                conn.connect();
                s_html = getHttpsResult(conn);
//                System.out.println(s_html);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }

            //System.out.println("list=" + s_html);
            if (100 > s_html.length()) {
                System.out.println("该股票已退市或接口出错:" + s_code);
                return false;
            }
            s_html = s_html.replaceAll("~~", "~");
            s_html = s_html.substring(s_html.indexOf("=") + 1, s_html.length());
//            System.out.println(s_html);
            String[] strArray;
            strArray = s_html.split("~");
//            System.out.println("今开" + strArray[5]);   //今开
//            System.out.println("昨收" + strArray[4]);   //昨收
//            System.out.println("当前" + strArray[3]);   //当前
//            System.out.println("最高" + strArray[32]);  //今日最高
//            System.out.println("最低" + strArray[33]);  //今日最低
//            System.out.println("买1" + strArray[19]);  //买1  
//            System.out.println("卖1" + strArray[9]);   //卖1
//            System.out.println("成交量" + strArray[6]);  //成交量   
//            System.out.println("成交金额" + strArray[7]);  //成交金额   34
//            System.out.println("买1手" + strArray[18]);  //买1手
//            System.out.println("买1价" + strArray[19]);  //买1价
//            System.out.println("买2手" + strArray[20]);  //买2手
//            System.out.println("买2价" + strArray[21]);  //买2价
//            System.out.println("买3手" + strArray[22]);  //买3手
//            System.out.println("买3价" + strArray[23]);  //买3价
//            System.out.println("买4手" + strArray[24]);  //买4手
//            System.out.println("买4价" + strArray[25]);  //买4价
//            System.out.println("买5手" + strArray[26]);  //买5手
//            System.out.println("买5价" + strArray[27]);  //买5价
//            System.out.println("卖1手" + strArray[8]);  //卖1手
//            System.out.println("卖1价" + strArray[9]);  //卖1价
//            System.out.println("卖2手" + strArray[10]);  //卖2手
//            System.out.println("卖2价" + strArray[11]);  //卖2价
//            System.out.println("卖3手" + strArray[13]);  //卖3手
//            System.out.println("卖3价" + strArray[13]);  //卖3价
//            System.out.println("卖4手" + strArray[14]);  //卖4手
//            System.out.println("卖4价" + strArray[15]);  //卖4价
//            System.out.println("卖5手" + strArray[16]);  //卖5手
//            System.out.println("卖5价" + strArray[17]);  //卖5价
//            System.out.println("日期" + strArray[29]);

            s_price = strArray[3];
            //停牌的不更新价格
            if ("0.00".contains(s_price)) {
                s_price = strArray[4];
            }
            dateTemp = strArray[29];
            //bj股票格式乱
            if (!dateTemp.startsWith("202")) {
                dateTemp = strArray[30];
            }
            s_date = dateTemp.substring(0, 4) + "/" + dateTemp.substring(4, 6) + "/" + dateTemp.substring(6, 8);
            s_time = dateTemp.substring(8, 10) + ":" + dateTemp.substring(10, 12) + "/" + dateTemp.substring(12, 14);
            if (0 > s_time.compareTo("09:30:00")) {
                s_time = "09:30:00";
            } else if ((0 < s_time.compareTo("11:30:00")) && (0 > s_time.compareTo("12:00:00"))) {
                s_time = "11:30:00";
            } else if (0 < s_time.compareTo("15:00:00")) {
                s_time = "15:00:00";
            }
            s_date = s_date + " " + s_time;
            s_sql = "insert into stock_quotes(code, cur_date, "
                    + "col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, "
                    + "col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, "
                    + "col21, col22, col23, col24, col25, col26, col27, col28, col29) values('"
                    + s_code + "','" + s_date + "',"
                    + strArray[5] + "," + strArray[4] + "," + strArray[3] + "," + strArray[32] + "," + strArray[33] + "," + strArray[19] + ","
                    + strArray[9] + "," + strArray[6] + "," + strArray[7] + "," + strArray[18] + "," + strArray[19] + "," + strArray[20] + ","
                    + strArray[21] + "," + strArray[22] + "," + strArray[23] + "," + strArray[24] + "," + strArray[25] + "," + strArray[26] + ","
                    + strArray[27] + "," + strArray[8] + "," + strArray[9] + "," + strArray[10] + "," + strArray[11] + "," + strArray[12] + ","
                    + strArray[13] + "," + strArray[14] + "," + strArray[15] + "," + strArray[16] + "," + strArray[17] + ")";
//            System.out.println(s_sql);

//            //行情太大,不保存了
//            stmt.execute(s_sql);
//            boolean b_flag1 = stmt.execute(s_sql);  //true;
            boolean b_flag1 = true;
            boolean b_flag2 = true;
//            if (0 < Double.valueOf(strArray[5])) {
                //by willia 2019-9-3
                if ((strArray[1].contains("*")) || (strArray[1].contains("ST")) || (strArray[1].contains("退市"))) {
                    b_flag2 = stmt.executeUpdate("update stock_pool set cur_price=" + s_price
                            + ", update_dt = '" + s_date
                            + "', cname = '" + strArray[1]
                            + "', vbeg = " + strArray[5]
                            + ", vend = " + strArray[3]
                            + ", lastend = " + strArray[4]
                            + ", vhigh = " + strArray[32]
                            + ", vlow = " + strArray[33]
                            + ", vtotal = " + strArray[6]
                            + ", vamount = " + strArray[7]
                            + ", grade = -3" //调成垃圾股
                            + " where code='" + s_code + "'");
                } else {
                    b_flag2 = stmt.executeUpdate("update stock_pool set cur_price=" + s_price
                            + ", update_dt = '" + s_date
                            + "', cname = '" + strArray[1]
                            + "', vbeg = " + strArray[5]
                            + ", vend = " + strArray[3]
                            + ", lastend = " + strArray[4]
                            + ", vhigh = " + strArray[32]
                            + ", vlow = " + strArray[33]
                            + ", vtotal = " + strArray[6]
                            + ", vamount = " + strArray[7]
                            + " where code='" + s_code + "'");
                }
//            } else {
//                logger.info("Code " + s_code + " is closed!");
//            }
            if ((true == b_flag1) && (true == b_flag2)) {
                return stmt.executeCommit();
            } else {
                stmt.executeRollback();
                return false;
            }
        }

    }

    /*0：”大秦铁路”，股票名字；
     1：”27.55″，今日开盘价；
     2：”27.25″，昨日收盘价；
     3：”26.91″，当前价格；
     4：”27.55″，今日最高价；
     5：”26.20″，今日最低价；
     6：”26.91″，竞买价，即“买一”报价；
     7：”26.92″，竞卖价，即“卖一”报价；
     8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
     9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
     10：”4695″，“买一”申请4695股，即47手；
     11：”26.91″，“买一”报价；
     12：”57590″，“买二”
     13：”26.90″，“买二”
     14：”14700″，“买三”
     15：”26.89″，“买三”
     16：”14300″，“买四”
     17：”26.88″，“买四”
     18：”15100″，“买五”
     19：”26.87″，“买五”
     20：”3100″，“卖一”申报3100股，即31手；
     21：”26.92″，“卖一”报价
     (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
     30：”2008-01-11″，日期；
     31：”15:05:32″，时间；*/
}
