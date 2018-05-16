package org.wade.mbus.worker;

import com.sun.jna.NativeLibrary;
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

        // 加载英数验证码网络模型
        String path = App.class.getResource("/").getPath();
        System.load(path + "/resources/en_external.dll");
        NativeLibrary.addSearchPath("en_external.dll", path);

        System.out.println("Hello world!");
    }
}
