package org.wade.mbus.worker.service.impl;

import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wade.mbus.common.jedis.JedisUtil;
import org.wade.mbus.common.jedis.RedisConfig;
import org.wade.mbus.common.json.JsonUtil;
import org.wade.mbus.model.enums.ValidateCodeType;
import org.wade.mbus.worker.repository.IValidateCodeRepository;
import org.wade.mbus.worker.service.IMessageListenerService;
import  org.wade.mbus.model.*;

@Service
public class MessageListenServiceImpl implements IMessageListenerService {


    @Autowired
    @Qualifier("validateCodeRepositoryImpl")
    private IValidateCodeRepository validateCodeRepositoryImpl;

    @Autowired
    @Qualifier("enValidateCodeRepositoryImpl")
    private IValidateCodeRepository eNValidateCodeRepositoryImpl;

    public IValidateCodeRepository getValidateCodeRepositoryImpl() {
        return validateCodeRepositoryImpl;
    }

    public void setValidateCodeRepositoryImpl(IValidateCodeRepository validateCodeRepositoryImpl) {
        this.validateCodeRepositoryImpl = validateCodeRepositoryImpl;
    }

    public IValidateCodeRepository geteNValidateCodeRepositoryImpl() {
        return eNValidateCodeRepositoryImpl;
    }

    public void seteNValidateCodeRepositoryImpl(IValidateCodeRepository eNValidateCodeRepositoryImpl) {
        this.eNValidateCodeRepositoryImpl = eNValidateCodeRepositoryImpl;
    }

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private RedisConfig redisConfig;

    static final Logger logger = LogManager.getLogger(MessageListenServiceImpl.class);

    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            TransportTemplate model = JsonUtil.getModel(new String(message.getBody()), TransportTemplate.class);
            // TODO shit code!!!
            // 这个地方由于时间紧迫, 暂时用if..else区分类型...
            String vCodeStr = null;
            if (model.getType() == ValidateCodeType.T_DEFAULT) vCodeStr = validateCodeRepositoryImpl.getImageText(model.getData());
            if(model.getType() == ValidateCodeType.T_EN) vCodeStr = eNValidateCodeRepositoryImpl.getImageText(model.getData());
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
