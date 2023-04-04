package com.yunbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static com.yunbot.windog.agent.StartBot.startBotAgent;


/**
 * 项目启动方法
 *
 * @author fuce
 * 买卖时机点，1为买入，0为不操作，-1为卖出
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootStart {

    private static Logger logger = LoggerFactory.getLogger(SpringbootStart.class);

    public static void main(String[] args) {

        try {
            SpringApplication.run(SpringbootStart.class, args);
            logger.info("************************************************************************");
            logger.info("*            WinDog                                                    *");
            logger.info("*            Version:   3.3.30.1                                       *");
            logger.info("************************************************************************");
            startBotAgent();
        } catch (Exception e) {
//            logger.info(e.getMessage());
        }
    }
}
