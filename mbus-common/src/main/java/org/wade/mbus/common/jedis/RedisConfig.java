package org.wade.mbus.common.jedis;

public class RedisConfig {
    private String host;
    private Integer port;
    private String passWord;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public RedisConfig() {

    }

    public RedisConfig(String host, Integer port, String passWord) {

        this.host = host;
        this.port = port;
        this.passWord = passWord;
    }
}
