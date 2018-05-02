package org.wade.mbus.dao.impl;

import org.springframework.stereotype.Repository;
import org.wade.mbus.dao.IMessageRepository;
import org.wade.mbus.model.Constants;

@Repository
public class MessageRepository implements IMessageRepository {
    @Override
    public void put(Integer key, Boolean value) {
        Constants.put(key,value);
    }

    @Override
    public boolean get(Integer number) {
        return Constants.get(number);
    }
}
