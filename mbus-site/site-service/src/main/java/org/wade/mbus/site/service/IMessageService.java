package org.wade.mbus.site.service;

import org.wade.mbus.model.CallMsgReq;
import org.wade.mbus.site.model.HttpResp;

public interface IMessageService {
    HttpResp upload(byte[] bytes, Integer type);
    HttpResp uploadAsync(byte[] bytes, Integer type);
    HttpResp getResult(String ticket);
    HttpResp update(CallMsgReq callMsgReq);
}
