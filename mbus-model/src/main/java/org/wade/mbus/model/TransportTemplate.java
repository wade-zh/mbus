package org.wade.mbus.model;

import java.io.Serializable;
import java.util.UUID;

public class TransportTemplate implements Serializable{
    private UUID ticket;
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

    public TransportTemplate() {

    }

    public TransportTemplate(UUID ticket, byte[] data) {

        this.ticket = ticket;
        this.data = data;
    }
}
