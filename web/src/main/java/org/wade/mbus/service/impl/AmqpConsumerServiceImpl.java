package org.wade.mbus.service.impl;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wade.mbus.model.StandardRespMsg;
import org.wade.mbus.service.IMessageService;

@Component
public class AmqpConsumerServiceImpl implements ChannelAwareMessageListener {

    private final IMessageService messageService;

    @Autowired
    public AmqpConsumerServiceImpl(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 消息到达
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            Integer number = Integer.valueOf(new String(message.getBody()));
            boolean result = messageService.isEven(number);
            messageService.edit(number, result);
            Thread.sleep(100000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}