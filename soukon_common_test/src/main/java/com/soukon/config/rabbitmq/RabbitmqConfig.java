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
    // 备用交换器
    public static final String ALTERNATE_EXCHANGE = "soukon_alternate_exchange";
    public static final String ALTERNATE_QUEUE = "soukon_alternate_queue";

    // 第二交换器
    public static final String SECOND_EXCHANGE = "soukon_second_exchange";
    public static final String SECOND_QUEUE = "soukon_second_queue";
    public static final String queue = "soukon_test";

    public static final String routingKey = "soukon_test";
    public static final String deadLetterExchange = "soukon_test_dead_letter";
    public static final String deadLetterQueue = "soukon_test_dead_letter";
    public static final String deadLetterRoutingKey = "soukon_test_dead_letter";
    public static final String deadLetterExchangeType = "direct";
    public static final String exchangeType = "direct";

    // 创建第二交换器
    @Bean
    public HeadersExchange secondExchange() {
        return new HeadersExchange(SECOND_EXCHANGE);
    }
    // 创建第二队列
    @Bean
    public Queue secondQueue() {
        return new Queue(SECOND_QUEUE, true);
    }

    // 绑定第二
    @Bean
    public Binding secondBinding(Queue secondQueue, HeadersExchange secondExchange) {
        return BindingBuilder.bind(secondQueue).to(secondExchange).where("second").matches("ok");
    }

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // 创建备用交换器
    @Bean
    public FanoutExchange alternateExchange() {
        return new FanoutExchange(ALTERNATE_EXCHANGE);
    }
    // 创建备用队列
    @Bean
    public Queue alternateQueue() {
        return new Queue(ALTERNATE_QUEUE, true);
    }

    // 绑定备用队列到备用交换器
    @Bean
    public Binding alternateBinding(Queue alternateQueue, FanoutExchange alternateExchange) {
        return BindingBuilder.bind(alternateQueue).to(alternateExchange);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(exchange).durable(true)
                .withArgument("alternate-exchange", ALTERNATE_EXCHANGE)  // 设置备用交换器
                .build();
    }



    // 定义交换机到交换机的绑定
    @Bean
    public Binding exchangeToExchangeBinding() {
        // 绑定 sourceExchange 到 destinationExchange，使用 routingKey "my.routing.key"
        return BindingBuilder.bind(secondExchange())
                .to(exchange())
                .with(routingKey)
                .noargs();
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
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }
//
//    @Bean
//    public RabbitTemplate jackSonRabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        //不指定消息转换器,默认是SimpleMessageConverter().
////        调用convertAndSend()时使用到该转换器,不同的转换器使用到的序列化和反序列化方式不同
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        return rabbitTemplate;
//    }
}
