package org.wade.mbus.site.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.wade.mbus.common.json.JsonUtil;
import org.wade.mbus.model.TransportTemplate;
import org.wade.mbus.model.enums.ValidateCodeType;
import org.wade.mbus.site.common.rabbitmq.RabbitSource;
import org.wade.mbus.site.common.rabbitmq.RabbitUtil;
import org.wade.mbus.site.repository.IMessageRepository;

import java.util.UUID;

@Repository
public class MessageRepository implements IMessageRepository {
    @Value("${rabbitmq.mbusQueue}")
    private String mbusQueue;
    @Value("${rabbitmq.socketQueue}")
    private String socketQueue;
    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;
    @Value("${spring.rabbitmq.template.socket-routing-key}")
    private String socketRoutingKey;
    @Autowired
    private RabbitUtil rabbitUtil;

    @Override
    public String pub(String queue, String nRoutingKey,  String ticket, String code) {
        return rabbitUtil.pub(new RabbitSource(queue, exchange, nRoutingKey), UUID.fromString(ticket), code);
    }

    public String pub(UUID uuid, Integer type, byte[] bytes) {
        return rabbitUtil.pub(new RabbitSource(mbusQueue, exchange, routingKey), uuid, JsonUtil.getJson(new TransportTemplate(uuid, ValidateCodeType.values()[type], bytes)));
    }
}
