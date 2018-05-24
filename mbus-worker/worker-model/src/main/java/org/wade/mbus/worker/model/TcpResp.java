package org.wade.mbus.worker.model;

public class TcpResp {
    private Integer connectionId;
    private String res;

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public TcpResp() {

    }

    public TcpResp(Integer connectionId, String res) {

        this.connectionId = connectionId;
        this.res = res;
    }
}
