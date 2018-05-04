package org.wade.mbus.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.wade.mbus.common.jedis.JedisUtil;
import org.wade.mbus.site.common.base64.Base64Util;
import org.wade.mbus.site.model.HttpResp;
import org.wade.mbus.site.repository.IMessageRepository;
import org.wade.mbus.site.service.IMessageService;

import java.util.UUID;

@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository messageRepository;
    private final RedisTemplate redisTemplate;

    @Autowired
    public MessageServiceImpl(IMessageRepository messageRepository, RedisTemplate redisTemplate) {
        this.messageRepository = messageRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public HttpResp upload(byte[] bytes) {
        UUID ticket = UUID.randomUUID();
        String mid = messageRepository.pub(ticket, bytes);
        System.out.println("msg id:" + mid);
        final Long startTime = System.currentTimeMillis();
        final Long overtime = 30 * 1000L;
        while (true) {
            Long nowTime = System.currentTimeMillis();
            if ((nowTime - startTime) > overtime) return new HttpResp(1, "timeout");
            String code = null;
            try {
                code =  redisTemplate.opsForValue().get(ticket.toString()).toString();
                System.out.println("ticket:" + ticket.toString() + " code:" + code);
            } catch (Exception e) {
                continue;
            }
            if (code != null)
                return new HttpResp(0, code);
        }
    }
}
