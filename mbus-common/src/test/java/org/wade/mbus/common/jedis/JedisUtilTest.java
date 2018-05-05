package org.wade.mbus.common.jedis;

import org.junit.Test;

import org.apache.logging.log4j.*;


import static org.junit.Assert.*;

public class JedisUtilTest extends JedisUtil {

    protected static final Logger logger = LogManager.getLogger();

    @Test
    public void testGet() {
        super.setRedisConfig(new RedisConfig("192.168.43.108", 6379, "123456"));
        String result = super.get("test");
        logger.info(result);
        System.out.println(result);
    }
}