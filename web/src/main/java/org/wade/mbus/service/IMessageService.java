package org.wade.mbus.service;

import org.wade.mbus.model.StandardRespMsg;

public interface IMessageService {

    /**
     * 上报数据
     * @param number
     * @return
     */
    boolean upload(Integer number);


    /**
     * 是否为偶数
     * @param number
     * @return
     */
    boolean isEven(Integer number);

    /**
     * 修改表数据
     * @param number
     * @param isEven
     * @return
     */
    boolean edit(Integer number, boolean isEven);

    /**
     * 查询数据
     * @param number
     * @return
     */
    boolean get(Integer number);
}
