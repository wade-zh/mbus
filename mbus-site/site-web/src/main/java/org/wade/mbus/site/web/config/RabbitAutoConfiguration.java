package org.wade.mbus.site.web.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.wade.mbus.site.web.config")
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitAutoConfiguration {

    private String exchange;
    private String createTopic;
    private String changeTopic;
    private String routingKey;

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    private String queue;

    //构造ConnectionFactory
    @Bean
    @ConfigurationProperties(prefix = "spring.rabbitmq")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setPublisherConfirms(Boolean.TRUE);
        return factory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //指定从哪个exchange接收数据
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    // 持久化队列
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    //绑定队列，并接收指定topic的数据
    @Bean
    public Binding bindingCreate() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(createTopic);
    }

    @Bean
    public Binding bindingChange() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(changeTopic);
    }


    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKey);
    }

    //设置监听
   /* @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory, MessageServiceImpl messageService) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setMessageListener(messageService);
        return container;
    }*/

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setCreateTopic(String createTopic) {
        this.createTopic = createTopic;
    }

    public void setChangeTopic(String changeTopic) {
        this.changeTopic = changeTopic;
    }
}