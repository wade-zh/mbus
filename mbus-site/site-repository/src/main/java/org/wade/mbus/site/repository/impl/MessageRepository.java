package org.wade.mbus.site.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.wade.mbus.common.json.JsonUtil;
import org.wade.mbus.model.TransportTemplate;
import org.wade.mbus.site.common.rabbitmq.RabbitSource;
import org.wade.mbus.site.common.rabbitmq.RabbitUtil;
import org.wade.mbus.site.repository.IMessageRepository;

import java.util.UUID;

@Repository
public class MessageRepository implements IMessageRepository {
    @Value("${spring.rabbitmq.queue}")
    private String queue;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;
    @Autowired
    private RabbitUtil rabbitUtil;

    public String pub(UUID uuid, byte[] bytes) {
        return rabbitUtil.pub(new RabbitSource(queue, exchange, routingKey), uuid, JsonUtil.getJson(new TransportTemplate(uuid, bytes)));
    }
}
