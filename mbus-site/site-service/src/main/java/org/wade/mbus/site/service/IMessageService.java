package org.wade.mbus.site.service;

import org.wade.mbus.site.model.HttpResp;

public interface IMessageService {
    HttpResp upload(byte[] bytes, Integer type);
}
