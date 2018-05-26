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
import org.wade.mbus.worker.model.TcpResp;
import org.wade.mbus.worker.repository.IValidateCodeRepository;
import org.wade.mbus.worker.service.IMessageListenerService;
import  org.wade.mbus.model.*;
import org.wade.mbus.worker.service.utils.SocketUtil;
import sun.misc.BASE64Encoder;

@Service
public class MessageListenServiceImpl implements IMessageListenerService {


    @Autowired
    @Qualifier("validateCodeRepositoryImpl")
    private IValidateCodeRepository validateCodeRepositoryImpl;

    @Autowired
    @Qualifier("enValidateCodeRepositoryImpl")
    private IValidateCodeRepository eNValidateCodeRepositoryImpl;

    public IValidateCodeRepository geteNExValidateCodeRepositoryImpl() {
        return eNExValidateCodeRepositoryImpl;
    }

    public void seteNExValidateCodeRepositoryImpl(IValidateCodeRepository eNExValidateCodeRepositoryImpl) {
        this.eNExValidateCodeRepositoryImpl = eNExValidateCodeRepositoryImpl;
    }

    @Autowired
    @Qualifier("enExValidateCodeRepositoryImpl")
    private IValidateCodeRepository eNExValidateCodeRepositoryImpl;

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
            logger.debug(message);
            TransportTemplate model = JsonUtil.getModel(new String(message.getBody()), TransportTemplate.class);
            // 通过socket完成数据交换
            BASE64Encoder encoder = new BASE64Encoder();
            String body =  model.getType().ordinal() + "|" + model.getTicket().toString() + "|" + encoder.encode(model.getData());
            TcpResp tcpResp = SocketUtil.send(body);
            if (tcpResp.getRes().equalsIgnoreCase("success")){
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
