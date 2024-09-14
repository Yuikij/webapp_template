package com.soukon.listener;

import com.rabbitmq.client.Channel;
import com.soukon.config.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class RabbitMQConsumer {

//     监听队列并处理消息
    @RabbitListener(queues = RabbitmqConfig.queue )
//    @Retryable
    public void receiveMessage(Message message, Channel channel) throws InterruptedException, IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("监听到的消息message: {}", message);
        log.info("监听到的消息channel: {}", channel);
//        channel.basicNack(deliveryTag,false,false);
        throw new RuntimeException();
    }

    //     监听备用交换机的消息
    @RabbitListener(queues = RabbitmqConfig.ALTERNATE_QUEUE )
    public void receiveAlternateMessage(String message) throws InterruptedException {
        log.info("监听到备用交换机的消息: {}", message);

    }

    //     监听第二交换机的消息
    @RabbitListener(queues = RabbitmqConfig.SECOND_QUEUE )
    public void receiveSecondMessage(String message) throws InterruptedException {
        log.info("监听到第二交换机的消息: {}", message);

    }
}
