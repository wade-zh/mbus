package org.wade.mbus.site.service;

import org.wade.mbus.model.CallMsgReq;
import org.wade.mbus.site.model.HttpResp;

import java.io.UnsupportedEncodingException;

public interface IMessageService {
    HttpResp upload(byte[] bytes, Integer type) throws UnsupportedEncodingException;
    HttpResp uploadAsync(byte[] bytes, Integer type);
    HttpResp getResult(String ticket);
    HttpResp update(CallMsgReq callMsgReq);

    HttpResp getServerCount();
}
