package org.wade.mbus.model;

import java.util.UUID;

/**
 * 回调消息响应类
 */
public class CallMsgReq {
    private String ticket;
    private String code;

    public CallMsgReq(String ticket, String code) {
        this.ticket = ticket;
        this.code = code;
    }

    public CallMsgReq() {

    }

    public String getTicket() {

        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
