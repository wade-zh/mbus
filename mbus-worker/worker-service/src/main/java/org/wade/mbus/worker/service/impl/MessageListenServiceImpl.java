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
import org.wade.mbus.worker.repository.IValidateCodeRepository;
import org.wade.mbus.worker.repository.impl.ValidateCodeRepositoryImpl;
import org.wade.mbus.worker.service.IMessageListenerService;
import  org.wade.mbus.model.*;

@Service
public class MessageListenServiceImpl implements IMessageListenerService {

    @Autowired
    private IValidateCodeRepository validateCodeRepository;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private RedisConfig redisConfig;

    static final Logger logger = LogManager.getLogger(MessageListenServiceImpl.class);

    public IValidateCodeRepository getValidateCodeRepository() {
        return validateCodeRepository;
    }

    public void setValidateCodeRepository(IValidateCodeRepository validateCodeRepository) {
        this.validateCodeRepository = validateCodeRepository;
    }

    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            TransportTemplate model = JsonUtil.getModel(new String(message.getBody()), TransportTemplate.class);
            String vCodeStr = validateCodeRepository.getVCodeStr(model.getData());
            jedisUtil.setRedisConfig(redisConfig);
            jedisUtil.put(model.getTicket().toString(), 60, vCodeStr);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
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
