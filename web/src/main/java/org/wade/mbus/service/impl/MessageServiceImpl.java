package org.wade.mbus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wade.mbus.dao.IMessageRepository;
import org.wade.mbus.model.StandardRespMsg;
import org.wade.mbus.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * 上报数据
     *
     * @param number
     * @return
     */
    @Override
    public boolean upload(Integer number) {
        System.out.println("upload " + number);
        messageRepository.put(number, false);
        return true;
    }

    /**
     * 是否为偶数
     *
     * @param number
     * @return
     */
    @Override
    public boolean isEven(Integer number) {
        System.out.println("isEven " + number);
        return number % 2 != 1;
    }

    /**
     * 修改表数据
     *
     * @param number
     * @param isEven
     * @return
     */
    @Override
    public boolean edit(Integer number, boolean isEven) {
        System.out.println("edit " + number);
        messageRepository.put(number, isEven);
        return true;
    }

    /**
     * 查询数据
     *
     * @param number
     * @return
     */
    @Override
    public boolean get(Integer number) {
        return messageRepository.get(number);
    }
}
