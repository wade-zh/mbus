package org.wade.mbus.dao;

public interface IMessageRepository {
    void put(Integer key, Boolean value);

    boolean get(Integer number);
}
