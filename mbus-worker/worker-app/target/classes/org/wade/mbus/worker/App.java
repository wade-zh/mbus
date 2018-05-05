package org.wade.mbus.worker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wade.mbus.common.jedis.JedisUtil;
import org.wade.mbus.common.jedis.RedisConfig;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("resources/application.xml");
        System.out.println("Hello world!");
    }
}
