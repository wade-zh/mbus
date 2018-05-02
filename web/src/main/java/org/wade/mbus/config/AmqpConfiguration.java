package org.wade.mbus.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.amqp.rabbit.listener.*;
import org.springframework.beans.factory.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.wade.mbus.service.impl.AmqpConsumerServiceImpl;

@Configuration
@ComponentScan("org.wade.mbus")
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class AmqpConfiguration {

    private String exchange;
    private String createTopic;
    private String changeTopic;

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
        return new Queue("message_bus", true);
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

    //设置监听
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory, AmqpConsumerServiceImpl amqpConsumerServiceImpl) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setMessageListener(amqpConsumerServiceImpl);
        return container;
    }

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