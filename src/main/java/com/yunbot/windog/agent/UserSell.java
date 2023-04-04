package com.yunbot.windog.agent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;

/**
 *
 * @author William
 */
public class UserSell {
    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    static int gi_stocks = 0;
    static double di_precent = 0;
    static double dv_win = 0.0;
    static double dv_lost = 0.0;
    
    static int gi_thrd = 0;
    static String gs_alarm = "";
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(UserSell.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        logger.info("Stock sell job is running...");

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
                }
                if ("FUNDS_PERCENT".equals(rs_data.getString("name"))) {
                    di_precent = rs_data.getDouble("value");
                    logger.info("Parameter FUNDS_PERCENT=" + String.valueOf(di_precent));
                }
                if ("WIN".equals(rs_data.getString("name"))) {
                    dv_win = rs_data.getDouble("value");
                    logger.info("Parameter WIN=" + String.valueOf(dv_win));
                }
                if ("LOST".equals(rs_data.getString("name"))) {
                    dv_lost = rs_data.getDouble("value");
                    logger.info("Parameter LOST=" + String.valueOf(dv_lost));
                }
            }
            //获取任务配置
            rs_data = mysqlobj.executeQuery("select uid from user_info");
            int i_flag = 1;   //标记
            while (rs_data.next()) {
                if (1 == i_flag) {
                    gs_task1 = gs_task1 + rs_data.getString("UID") + ",";
                    i_flag = 2;
                } else if (2 == i_flag) {
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
        UserSellThread startthread1 = new UserSellThread();
        startthread1.setURL(gs_dburl);
        startthread1.setUSER(gs_user);
        startthread1.setPWD(gs_pwd);
        startthread1.setTask(gs_task1);
        startthread1.setTid(1);
        Thread mysqlthread1 = new Thread(startthread1);
        mysqlthread1.start();

        UserSellThread startthread2 = new UserSellThread();
        startthread2.setURL(gs_dburl);
        startthread2.setUSER(gs_user);
        startthread2.setPWD(gs_pwd);
        startthread2.setTask(gs_task2);
        startthread2.setTid(2);
        Thread mysqlthread2 = new Thread(startthread2);
        mysqlthread2.start();
    }

    //启动线程类 
    static class UserSellThread implements Runnable {

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
            MysqlObj stmt = null;
            MysqlObj stmttmp = null;
            stmt = new MysqlObj(s_url, s_user, s_pwd, false);
            stmttmp = new MysqlObj(s_url, s_user, s_pwd, false);
            String[] strArray;
            strArray = this.s_task.split(",");
            for (String strArray1 : strArray) {
                //开盘时间执行
                if ((!"".equals(strArray1)) && (StartBot.inOperation())) {
                    if (!SellingStock(stmt, stmttmp, strArray1)) {
                        logger.error("Thread " + this.i_thread + " selling operation " + strArray1 + " failed.");
                    }
                }
            }
            //计数器
            changeThreadCount();
            logger.info("Thread " + String.valueOf(this.i_thread) + " is finished: " + gi_thrd);
            stmt.close();
            stmttmp.close();
        }

        //计算止赢
        public boolean StopProfit(MysqlObj mysql, String s_tid, double dwin, double dlost, String s_price, String cur_price, String top_price) {
            //单边上涨更新最高价，不卖出
//            if (Double.valueOf(top_price) < Double.valueOf(cur_price)) {
//                String s_sql = "update bot_hold set top_price = " + cur_price + " where tid = " + s_tid;
//                if (true == mysql.execute(s_sql)) {
//                    mysql.executeCommit();
//                }
//                return false;
//            }
            //最高价大于买入价的止盈点位,才计算止盈
            //利润30以内，按照原有方式止盈
            if ((Double.valueOf(s_price) * (100 + dwin) < Double.valueOf(top_price)*100) && (Double.valueOf(s_price) * (130) > Double.valueOf(top_price)*100)) {
                //最高价回撤三分之一利润
                if (Double.valueOf(cur_price)
                        < ((Double.valueOf(top_price) - (Double.valueOf(s_price) * (100 + dwin) / 100)) * 2 / 3)
                        + Double.valueOf(s_price) * (100 + dwin) / 100) {
                    return true;
                }
            }
            //利润30%以上的，按照当前价和最高价计算止损，不按买入价了
            if ((Double.valueOf(s_price) * (100 + dwin) < Double.valueOf(top_price)*100) && (Double.valueOf(s_price) * (130) <= Double.valueOf(top_price)*100)) {
                //最高价下跌10%止盈
                if (Double.valueOf(top_price)* (100 - dlost) > (Double.valueOf(cur_price)*100)) {
                    return true;
                }
            }
            return false;
        }
        
        //执行一次卖出检查，找到持有的
        public boolean SellingStock(MysqlObj stmt, MysqlObj stmttmp, String s_uid) {
            //避免T+0
            //8%止损
            //仓位过大需抛售业绩差的，按nn排序了 di_precent
            //2018/4/12修改   按-mm排序,使收益高的能够在调仓过程卖出
            String s_sql = "select a.tid,a.code,a.cname,a.price,a.amount,a.top_price,a.cur_date,b.update_dt,"
                    + "b.grade, b.vbeg, b.lastend,b.act,b.cur_price,(b.cur_price-a.price)/a.price mm, c.funds "
                    + " from user_hold a, stock_pool b, user_info c "
                    + "WHERE a.code=b.code and a.uid=c.uid and a.uid=" + s_uid 
                    + " and a.cur_date<CURRENT_DATE() and TO_DAYS(b.update_dt) = TO_DAYS(CURRENT_DATE()) ORDER BY b.grade, -mm ASC";
            ResultSet rs_data = stmt.executeQuery(s_sql);
            String s_tid = "";
            String s_code = "";
            String s_cname = "";
            String s_amount = "";
            String buy_price = "";
            String cur_price = "";
            String top_price = "";
            String s_indate = "";   //买入价格
            String s_outdate = "";  //卖出价格
            String s_type = "0";     //卖出原因
            double d_sellflag = 0;
            double dpreend = 0;
            boolean b_stopprofit = false;
            int i_grade = 0;
            int i_act = 0;
            int istock = 0;
            try {
                ResultSet rs_tmp = stmttmp.executeQuery("select count(*) istock from user_hold where uid = " + s_uid);
                try {
                    if (rs_tmp.next()) {
                        istock = rs_tmp.getInt("istock");
                    }
                } catch (SQLException ex) {
                    logger.error("Get stock " + s_uid + " value error.");
                }
                //循环
                while (rs_data.next()) {
                    s_tid = rs_data.getString("TID");
                    s_code = rs_data.getString("CODE");
                    s_cname = rs_data.getString("CNAME");
                    s_amount = rs_data.getString("AMOUNT");
                    i_grade = rs_data.getInt("GRADE");
                    i_act = rs_data.getInt("ACT");
                    buy_price = rs_data.getString("PRICE");
                    s_indate = rs_data.getString("CUR_DATE");
                    s_outdate = rs_data.getString("UPDATE_DT");
                    cur_price = rs_data.getString("CUR_PRICE");
                    top_price = rs_data.getString("TOP_PRICE");  //曾经的最高价
                    s_type = "0";       //卖出原因
                    if ((!"".equals(s_tid)) && (!"".equals(s_code)) && (!"".equals(s_cname)) && (!"".equals(s_amount)) && (!"".equals(buy_price)) && (!"".equals(top_price))) {
                        //策略要求抛售，或者亏8%(SELLFLAG)的执行卖出
                        //System.out.println((Double.valueOf(cur_price) - Double.valueOf(s_price)) / Double.valueOf(s_price));
                        b_stopprofit = StopProfit(stmttmp, s_tid, dv_win, dv_lost, buy_price, cur_price, top_price);
                        if (b_stopprofit) {
                            s_type = "1";           //止赢
                        }
                        //要考虑停牌,除权
                        d_sellflag = (Double.valueOf(cur_price) - Double.valueOf(buy_price)) / Double.valueOf(buy_price);
                        if ((-dv_lost > d_sellflag * 100) && (0 < Double.valueOf(cur_price))) {
//                            if (-10.4 > d_sellflag * 100) {
//                                dpreend = isExDiv(stmttmp, s_code, rs_data.getDouble("vbeg"));
//                                if (0 < dpreend) {
//                                    int curamount = (int) ((dpreend * rs_data.getInt("AMOUNT"))
//                                            / rs_data.getDouble("lastend"));
//                                    stmttmp.executeUpdate("update user_hold set amount="
//                                            + String.valueOf(curamount) + ", price=" + rs_data.getDouble("lastend")
//                                            + ", top_price=" + rs_data.getDouble("lastend") + " where tid=" + s_tid);
//                                    return stmttmp.executeCommit();
//                                }
//                            } else {
                                s_type = "2";           //止损
//                            }
                        }
                        if ((-1 == i_grade) && (1 == i_act)) {
                            s_type = "3";           //调级
                        }
                        if ((istock > gi_stocks) && ("0".equals(s_type))) {
                            s_type = "4";           //调仓
                        }
                        //调级卖出，止损卖出，止赢卖出，调仓卖出（仓位空余为负数）
                        if (!"0".equals(s_type)) {
                            String s_execute1 = "insert into user_transaction(uid, code, cname, amount, price, in_price, in_date, cur_date, type)"
                                    + " values(" + s_uid + ",'" + s_code + "','" + s_cname + "',"
                                    + s_amount + "," + cur_price + ", " + buy_price + ",'" + s_indate + "','" + s_outdate +"', " + s_type + ")";
                            String s_execute3 = "delete from user_hold where (uid=" + s_uid + ") and (code = '" + s_code + "') ";
                            //插入交易卖出
                            boolean b_flag1 = stmttmp.execute(s_execute1);
                            if (!b_flag1) {
                                stmttmp.executeRollback();
                                logger.error("Selling user stock is error1!");
                            }
                            //更改账户余额
                            boolean b_flag2 = UpdateUserAccount(stmttmp, Integer.valueOf(s_amount) * Double.valueOf(cur_price), s_uid);
                            if (!b_flag2) {
                                stmttmp.executeRollback();
                                logger.error("Selling user stock is error2!");
                            }
                            //删除账户持仓
                            boolean b_flag3 = stmttmp.execute(s_execute3);   //删除账户持仓
                            if (!b_flag3) {
                                stmttmp.executeRollback();
                                logger.error("Selling user stock is error3!");
                            }
                            //超跌卖出后，应该对股票池自动调级为0，避免被继续自动买入
                            //止赢或止损都停止买入
                            //if ((SELLFLAG > d_sellflag) || b_stopprofit) {
                            //    b_flag4 = mysqlobj.execute("update stock_pool set grade=0 where code='" + s_code + "'");
                            //}
                            if (gs_alarm.contains(s_uid)) {
                                boolean b_flag4 = stmttmp.execute("insert into alarm_msg(uid, type, indate, flag, msg) values ("
                                        + s_uid + ", 'R', '" + s_outdate + "', 1, '  卖出:" + s_code + "(" + s_cname + ")  价格:"
                                        + cur_price + "  数量:" + s_amount + "')");
                                if (!b_flag4) {
                                    stmttmp.executeRollback();
                                    logger.error("Selling user stock is error4!");
                                }
                            }
                            return stmttmp.executeCommit();
                        }
                        return true;
                    } else {
                        logger.error("Get stock detail info error!");
                        return false;
                    }
                }
                return true;
            } catch (SQLException ex) {
                logger.error("Get user " + s_uid + " stock list error." + ex.getMessage());
                return false;
            }
        }
        
        //判断是否除权
        public static double isExDiv(MysqlObj mysql, String scode, double curbeg) {
            double preend = 0;
            try {
                ResultSet rs_tmp = mysql.executeQuery("select * from stock_day where code='" + scode
                        + "' order by keydt desc limit 1");
                if (rs_tmp.next()) {
                    preend = rs_tmp.getDouble("vend");
                    if (curbeg < preend * 0.89) {
                        return preend;
                    }
                }
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(ModelSell.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
        
        //获取某股票当前价格
        public static boolean UpdateUserAccount(MysqlObj mysql, double d_amount, String s_uid) {
            String s_sql = "update user_info set funds = funds + " + d_amount + " where uid = " + s_uid;
            if (true == mysql.execute(s_sql)) {
                return true;
            }
            return false;
        }
    }
}
