package org.wade.mbus.model;

import java.util.concurrent.ConcurrentHashMap;

public class Constants {
    private static ConcurrentHashMap<Integer, Boolean> table;

    public static ConcurrentHashMap<Integer, Boolean> getTable() {
        if(table == null) table = new ConcurrentHashMap<>();
        return table;
    }

    public static void put(Integer key, Boolean value){
        getTable().put(key, value);
    }

    public static Boolean get(Integer key){
        return getTable().get(key);
    }
}
