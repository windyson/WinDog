/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yunbot.windog.agent;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author William
 */
public class MysqlObj {

    private static org.slf4j.Logger logger= LoggerFactory.getLogger(MysqlObj.class);

    //String url = "jdbc:mysql://localhost/cjdsj"; // URL指向要访问的数据库名scutcs
    //jdbc:mysql://127.0.0.1:3306/cjdsj?characterEncoding=UTF-8
    //String user = "root"; // MySQL配置时的用户名
    //String pwd = ""; // MySQL配置时的密码
    Connection conn = null;
    Statement stmt;
    ResultSet rs = null;

    public MysqlObj(String url, String user, String pwd, boolean b_auto) {
    //public MysqlObj() {
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();   
            Class.forName("com.mysql.jdbc.Driver").newInstance(); // 加载驱动程序
            DriverManager.setLoginTimeout(5000);
            conn = DriverManager.getConnection(url, user, pwd);
            //是否自动提交
            conn.setAutoCommit(b_auto);
            stmt = conn.createStatement();
        } catch (Exception e) {
            //e.printStackTrace();
            //System.exit(0);
            logger.error(e.getMessage());
        }
    }
    
    public boolean executeCommit() {
        try {
            conn.commit();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
    
    public boolean executeRollback() {
        try {
            conn.rollback();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean executeUpdate(String sql) {
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(sql);
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean execute(String sql) {
        try {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
    
    public ResultSet executeQuery(String sql) {
        rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.printf(sql);
            logger.error(e.getMessage());
        }
        return rs;
    }

    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
//                e.printStackTrace();
            }
        }
    }
    
}
