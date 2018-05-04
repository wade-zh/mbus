package org.wade.mbus.site.common.rabbitmq;

public class RabbitSource {
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public RabbitSource(String queueName, String exchange, String routingKeys) {

        this.queueName = queueName;
        this.exchange = exchange;
        this.routingKeys = routingKeys;
    }

    private String exchange;
    private String routingKeys;

    public RabbitSource() {
    }

    public String getExchange() {
        return exchange;
    }

    @Override
    public String toString() {
        return "RabbitSource{" +
                "queueName='" + queueName + '\'' +
                ", exchange='" + exchange + '\'' +
                ", routingKeys='" + routingKeys + '\'' +
                '}';
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKeys() {
        return routingKeys;
    }

    public void setRoutingKeys(String routingKeys) {
        this.routingKeys = routingKeys;
    }
}
