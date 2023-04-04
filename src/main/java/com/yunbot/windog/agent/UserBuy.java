package com.yunbot.windog.agent;

import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author William
 */
public class UserBuy {
    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    static int gi_stocks = 0;           //持股个数
    static double gd_percent = 0.0;     //仓位
    static int ipolicy = 0;             //策略
    
    static int gi_thrd = 0;
    static String gs_alarm = "";
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(UserBuy.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        logger.info("Stock buy job is running...");

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

        //gs_task1,gs_task2表示不同线程要做的数据抓取id
        String gs_task1 = "";
        String gs_task2 = "";
        //取数据
        try {
            // TODO add your handling code here:
            MysqlObj mysqlobj = new MysqlObj(gs_dburl, gs_user, gs_pwd, false);
            //获取参数
            ResultSet rs_data = mysqlobj.executeQuery("select * from bot_policy");
            while (rs_data.next()) {
                if ("STOCK_COUNT".equals(rs_data.getString("name"))) {
                    gi_stocks = rs_data.getInt("value");
                    logger.info("Parameter STOCK_COUNT=" + String.valueOf(gi_stocks));
                } else if ("FUNDS_PERCENT".equals(rs_data.getString("name"))) {
                    gd_percent = rs_data.getDouble("value");
                    logger.info("Parameter FUNDS_PERCENT=" + String.valueOf(gd_percent));
                } else if ("POLICY".equals(rs_data.getString("name"))) {
                    ipolicy = rs_data.getInt("value");
                    logger.info("Parameter POLICY=" + String.valueOf(ipolicy));
                }
            }
            //获取任务配置
            rs_data = mysqlobj.executeQuery("select uid from user_info");
            int i_flag = 1;   //标记
            while (rs_data.next()) {
                if (1 ==  i_flag) {
                    gs_task1 = gs_task1 + rs_data.getString("UID") + ",";
                    i_flag = 2;
                } else if (2 ==  i_flag) {
                    gs_task2 = gs_task2 + rs_data.getString("UID") + ",";
                    i_flag = 1;
                }
            }
            //获取消息报警用户
            rs_data = mysqlobj.executeQuery("select uid from user_info where alarm=1");
            while (rs_data.next()) {
                gs_alarm = gs_alarm + rs_data.getString("UID") + ",";
            }  
            mysqlobj.close();
        } catch (SQLException ex) {
            logger.error("Get user list error.");
        }

        UserBuyThread startthread1 = new UserBuyThread();
        startthread1.setURL(gs_dburl);
        startthread1.setUSER(gs_user);
        startthread1.setPWD(gs_pwd);
        startthread1.setTask(gs_task1);
        startthread1.setTid(1);
        Thread mysqlthread1 = new Thread(startthread1);
        mysqlthread1.start();

        UserBuyThread startthread2 = new UserBuyThread();
        startthread2.setURL(gs_dburl);
        startthread2.setUSER(gs_user);
        startthread2.setPWD(gs_pwd);
        startthread2.setTask(gs_task2);
        startthread2.setTid(2);
        Thread mysqlthread2 = new Thread(startthread2);
        mysqlthread2.start();
    }
    
    //启动线程类 
    static class UserBuyThread implements Runnable {
        
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
        public synchronized void changeThreadCount() {
            gi_thrd--;
        }
        
        public void run() {
//            logger.info("Thread " + String.valueOf(this.i_thread) + " is executing...");
//            logger.info("Thread " + String.valueOf(this.i_thread) + " connecting to mysql...");
            MysqlObj stmt = null;
            stmt = new MysqlObj(s_url, s_user, s_pwd, false);
            if (null == stmt) {
                logger.error("Thread " + String.valueOf(this.i_thread) + " connected mysql error.");
            }
            String[] strArray;
            strArray = this.s_task.split(",");
            for (String strArray1 : strArray) {
                //开盘时间执行
                if ((!"".equals(strArray1)) && (StartBot.inOperation())) {
                    if (!BuyingStock(stmt, strArray1)) {
                        logger.error("Thread " + String.valueOf(this.i_thread) + " buying operation " + strArray1 + " failed.");
                    }
                }
            }
            //计数器
            changeThreadCount();
            logger.info("Thread " + String.valueOf(this.i_thread) + " is finished: " + gi_thrd);
            stmt.close();
        }
        
        //执行一次插入
        public boolean BuyingStock(MysqlObj stmt, String s_uid) {
            String s_sql = "select funds, funds_init from user_info where uid = " + s_uid;
            ResultSet rs_data = stmt.executeQuery(s_sql);
            double f_funds = 0;
            double f_fundsvalue = 0;
            //获得可用资金
            try {
                if (rs_data.next()) {
                    //可用现金
                    f_funds = rs_data.getDouble("FUNDS");
                }
            } catch (SQLException ex) {
                logger.error("Get user " + s_uid +" stock funds error.");
                //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (1000 > f_funds) {
//                logger.info("User funds1 " + s_uid +" less than 1000.");
                return true;
            }
            //获得持仓价值
            rs_data = stmt.executeQuery("select sum(amount*price) tvalue from user_hold where uid = " + s_uid);
            try {
                if (rs_data.next()) {
                    //股票持仓市值
                    f_fundsvalue = rs_data.getDouble("tvalue");
                }
            } catch (SQLException ex) {
                logger.error("Get stock " + s_uid + " value error.");
                //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
            }
            //可用金额=总资产*持仓比例-股票市值
            f_funds = (f_funds + f_fundsvalue) * gd_percent - f_fundsvalue;
            //资金小于1000不买入
            if (1000 > f_funds) {
//                logger.warn("User funds2 " + s_uid +" less than 1000.");
                return true;
            }
            //获得可买股票只数
            int i_count = 0;
            //避免买入指数或重复的
            String s_codes = "'sh000001','sh000905'";
            s_sql = "select code from user_hold where (uid = " + s_uid + ")";
            rs_data = stmt.executeQuery(s_sql);
            try {
                while (rs_data.next()) {
                    i_count++;
                    s_codes = s_codes + ",'" + rs_data.getString("CODE") + "'";
                }
            } catch (SQLException ex) {
                logger.error("Get user " + s_uid + " stock list error.");
                //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
            }
            //最近10日买过该股票,也不再买入
            s_sql = "select code from user_transaction where (uid = " + s_uid + ") and (in_date > DATE_SUB(CURDATE(),INTERVAL 14 DAY))";
            rs_data = stmt.executeQuery(s_sql);
            try {
                while (rs_data.next()) {
                    s_codes = s_codes + ",'" + rs_data.getString("CODE") + "'";
                }
            } catch (SQLException ex) {
                logger.error("Get user " + s_uid + " stock list error.");
                //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
            }
            //可以买入
            if (gi_stocks > i_count) {
                //不选择重复持仓的股票
                //不买涨停的
                //不买停牌的
                //要在指定选股策略中的
                s_sql = "select * from stock_pool where (vol" + ipolicy
                        + " > 0) and (cur_price < (lastend*1.099)) and (vbeg > 0) and (code not in ("
                        + s_codes + ")) and (grade >= 0) ORDER BY vol" + ipolicy + " desc";
                rs_data = stmt.executeQuery(s_sql);
                String s_code = "";
                String s_cname = "";
                try {
                    if (rs_data.next()) {
                        s_code = rs_data.getString("CODE");
                        s_cname = rs_data.getString("CNAME");
                        StockPriceInfo ret_val = new StockPriceInfo();
                        //获取某股票价格及时间点
                        ret_val = GetCurrentPrice(stmt, s_code);
                        double d_price = ret_val.getPrice();
                        String curtime = ret_val.getDate();
                        //资金分gi_stocks份
                        int i_num = (int) (f_funds / ((gi_stocks - i_count) * d_price));
                        //取整100
                        i_num = (i_num / 100) * 100;
                        //UserLib.WriteLog(true, "Error", String.valueOf(f_funds) + "  " + String.valueOf(d_price) + "  " + String.valueOf(i_num));
                        if ((0 < d_price) && (0 < i_num)) {
                            //执行买入
                            String s_execute1 = "insert into user_hold(uid, code, cname, amount, price,cur_date)"
                                    + " values(" + s_uid + ",'" + s_code + "','" + s_cname + "',"
                                    + String.valueOf(i_num) + "," + String.valueOf(d_price) + ",'" + curtime + "')";
                            boolean b_flag1 = stmt.execute(s_execute1);
                            if (!b_flag1) {
                                stmt.executeRollback();
                                logger.error("Buying user stock is error1!");
                            }
                            boolean b_flag2 = UpdateUserAccount(stmt, i_num * d_price, s_uid);
                            if (!b_flag2) {
                                stmt.executeRollback();
                                logger.error("Buying user stock is error2!");
                            }
                            if (gs_alarm.contains(s_uid)) {
                                boolean b_flag3 = stmt.execute("insert into alarm_msg(uid, type, indate, flag, msg) values ("
                                        + s_uid + ", 'R', '" + curtime + "', 1, '  买入:" + s_code + "(" + s_cname 
                                        + ")  价格:" + d_price + "  数量:" + i_num + "')");
                                if (!b_flag3) {
                                    stmt.executeRollback();
                                    logger.error("Buying user stock is error3!");
                                }
                            }
//                            logger.info("Buying stock " + s_code + "is successful!");
                            return stmt.executeCommit();
                        }
                        return true;
                    }
                    //UserLib.WriteLog(true, "Error", "None stock can buy!");
                } catch (SQLException ex) {
                    logger.error("Select stock from pool is error.");
                    //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
//                logger.info("Stock count is full!");
                return true;
            }
            return true;
        }
    }
    
    //获取某股票当前价格
    public static StockPriceInfo GetCurrentPrice(MysqlObj mysqlobj, String s_code) {
        //没有实时行情，则提示招不到行情数据，不买入
        String s_sql = "select update_dt, cur_price from stock_pool where (code = '" + s_code + "')";
        ResultSet rs_data = mysqlobj.executeQuery(s_sql);
        rs_data = mysqlobj.executeQuery(s_sql);
        StockPriceInfo ret_val;
        ret_val = new StockPriceInfo();
        try {
            if (rs_data.next()) {
                ret_val.setDate(rs_data.getString("update_dt"));
                ret_val.setPrice(rs_data.getDouble("cur_price"));
                return ret_val;
            }
        } catch (SQLException ex) {
            logger.error("Get " + s_code + " stock " + s_code + " realtime price error.");
            return ret_val;
            //Logger.getLogger(StockBuy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret_val;
    }
    
    //获取某股票当前价格
    public static boolean UpdateUserAccount(MysqlObj mysql, double d_amount, String s_uid) {
        String s_sql = "update user_info set funds = funds - " + d_amount + " where uid = " + s_uid;
        if (true == mysql.execute(s_sql)) {
            return true;
        }
        return false;
    }
    
    //当前价格
    public static class StockPriceInfo {
        private double d_price = 0;
        private String s_date = "";
        public void setPrice(double d_price) {
            this.d_price = d_price;
        }
        public void setDate(String s_date) {
            this.s_date = s_date;
        }
        public double getPrice() {
            return d_price;
        }
        public String getDate() {
            return s_date;
        }
    }
}
