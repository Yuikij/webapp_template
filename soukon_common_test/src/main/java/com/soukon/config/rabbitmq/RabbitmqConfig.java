package com.soukon.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    public static final String exchange = "soukon_test";
    public static final String queue = "soukon_test";
    public static final String routingKey = "soukon_test";
    public static final String deadLetterExchange = "soukon_test_dead_letter";
    public static final String deadLetterQueue = "soukon_test_dead_letter";
    public static final String deadLetterRoutingKey = "soukon_test_dead_letter";
    public static final String deadLetterExchangeType = "direct";
    public static final String exchangeType = "direct";

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingTestQueue() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey).noargs();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public RabbitTemplate jackSonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //不指定消息转换器,默认是SimpleMessageConverter().
//        调用convertAndSend()时使用到改转换器,不同的转换器使用到的序列化和反序列化方式不同
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
