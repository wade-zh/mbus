package org.wade.mbus.model;

import org.wade.mbus.model.enums.ValidateCodeType;

import java.io.Serializable;
import java.util.UUID;

/**
 * 传输消息请求参数类
 */
public class TransportTemplate implements Serializable{
    private UUID ticket;
    private ValidateCodeType type;
    private byte[] data;

    public UUID getTicket() {
        return ticket;
    }

    public void setTicket(UUID ticket) {
        this.ticket = ticket;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public ValidateCodeType getType() {
        return type;
    }

    public void setType(ValidateCodeType type) {
        this.type = type;
    }

    public TransportTemplate(UUID ticket, ValidateCodeType type, byte[] data) {
        this.ticket = ticket;
        this.type = type;
        this.data = data;
    }

    public TransportTemplate() {

    }

}
