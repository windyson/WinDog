package com.yunbot.windog.agent;

import com.yunbot.windog.common.conf.DBConfig;
import org.slf4j.LoggerFactory;

/**
 * @author William
 */
public class SysDataInit {

    static String gs_dburl = "";
    static String gs_user = "";
    static String gs_pwd = "";
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SysDataInit.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        logger.info("Init system data is running...");

        gs_dburl = DBConfig.getUrl();
        if (("".equals(gs_dburl))) {
            logger.warn("DBURL is null.");
        }

        gs_user = DBConfig.getUsername();
        if (("".equals(gs_user))) {
            logger.warn("DBUser is null.");
        }

        gs_pwd = DBConfig.getPassword();
        if (("".equals(gs_pwd))) {
            logger.warn("DBPwd is null.");
        }

//        gs_dburl = "jdbc:mysql://127.0.0.1:3306/yunbot?characterEncoding=UTF-8";
//        gs_user = "root";
//        gs_pwd = "welcome";

        //找到最新的日数据
        MysqlObj mysqlobj = new MysqlObj(gs_dburl, gs_user, gs_pwd, false);
        String insSql = "";
        try {
            try {
                //删除bot_info
                if (mysqlobj.executeUpdate("delete from bot_info")) {
                    for (int i=1; i<=15; i++) {
                        for (int j=3; j <= 15; j++) {
                            for (int k=3; k <= j; k++) {
                                insSql = String.format("insert into bot_info(name, nick, funds, funds_init, policy, win, lost) values('%s', '%s', %d, %d, %d, %d, %d)",
                                        (char)(64+i) + (j+"-"+k), (char)(64+i) + (j+"-"+k), 10000000, 10000000, i, j, k);
                                mysqlobj.execute(insSql);
                            }
                        }
                    }
                    mysqlobj.executeCommit();
                } else {
                    logger.error("delete bot_info error.");
                    return;
                }
            } catch (Exception ex) {
                logger.error("Execute error:" + ex.getMessage());
            }
            try {
                //删除bot_hold
                if (mysqlobj.executeUpdate("delete from bot_hold")) {
                    mysqlobj.executeCommit();
                } else {
                    logger.error("delete bot_hold error.");
                    return;
                }
            } catch (Exception ex) {
                logger.error("Execute error:" + ex.getMessage());
            }
            try {
                //删除bot_transaction
                if (mysqlobj.executeUpdate("delete from bot_transaction")) {
                    mysqlobj.executeCommit();
                } else {
                    logger.error("delete bot_transaction error.");
                    return;
                }
            } catch (Exception ex) {
                logger.error("Execute error:" + ex.getMessage());
            }
            try {
                //删除bot_profit
                if (mysqlobj.executeUpdate("delete from bot_profit")) {
                    mysqlobj.executeCommit();
                } else {
                    logger.error("delete bot_profit error.");
                    return;
                }
            } catch (Exception ex) {
                logger.error("Execute error:" + ex.getMessage());
            }
            try {
                //bot_profit5
                if (mysqlobj.executeUpdate("delete from bot_profit5")) {
                    mysqlobj.executeCommit();
                } else {
                    logger.error("delete bot_profit5 error.");
                    return;
                }
            } catch (Exception ex) {
                logger.error("Execute error:" + ex.getMessage());
            }
        } finally {
            mysqlobj.close();
        }
    }

}