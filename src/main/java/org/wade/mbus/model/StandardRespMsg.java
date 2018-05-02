package org.wade.mbus.model;

public class StandardRespMsg {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public StandardRespMsg() {

    }

    public StandardRespMsg(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }
}
