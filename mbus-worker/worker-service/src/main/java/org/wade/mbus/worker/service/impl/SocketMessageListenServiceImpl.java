package org.wade.mbus.worker.service.impl;

import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wade.mbus.common.jedis.JedisUtil;
import org.wade.mbus.common.jedis.RedisConfig;
import org.wade.mbus.common.json.JsonUtil;
import org.wade.mbus.model.CallMsgReq;
import org.wade.mbus.model.TransportTemplate;
import org.wade.mbus.model.enums.ValidateCodeType;
import org.wade.mbus.worker.service.IMessageListenerService;

/**
 * socket消息监听器
 * @author HongWei
 * @apiNote 验证码引擎不再使用JNA调用，改为由易语言作引擎，此监听器监听server回调的验证码消息
 */
@Service
public class SocketMessageListenServiceImpl implements IMessageListenerService {
    static final Logger logger = LogManager.getLogger(MessageListenServiceImpl.class);

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private RedisConfig redisConfig;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            CallMsgReq model = JsonUtil.getModel(new String(message.getBody()), CallMsgReq.class);
            jedisUtil.setRedisConfig(redisConfig);
            boolean flag = jedisUtil.put(model.getTicket().toString(), 60, model.getCode());
            if (flag){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            logger.error("exception:" + e.toString());
        }
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setJedisUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    public JedisUtil getJedisUtil() {
        return jedisUtil;
    }
}
