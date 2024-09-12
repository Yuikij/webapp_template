package com.soukon.listener;

import com.soukon.config.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitMQConsumer {

    // 监听队列并处理消息
    @RabbitListener(queues = RabbitmqConfig.queue )
    public void receiveMessage(Message message) {
        System.out.println("Received message: " + message);
        // 处理消息逻辑

        log.info(message.getMessageProperties().getType());
    }
}
