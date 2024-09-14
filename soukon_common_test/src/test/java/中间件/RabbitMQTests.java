package 中间件;

import com.soukon.Application;
import com.soukon.config.rabbitmq.RabbitmqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = Application.class)
public class RabbitMQTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.exchange, RabbitmqConfig.routingKey, "hello world");
    }
    @Test
    public void testSendHeader() {
        // 设置消息头
        Map<String, Object> headers = new HashMap<>();
        headers.put("second", "ok");

        // 创建消息并附带头部属性
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(headers);
        Message message = new Message("hello world".getBytes(), messageProperties);

        // 发送消息到 HeadersExchange
        rabbitTemplate.send(RabbitmqConfig.exchange, RabbitmqConfig.routingKey, message);
    }
}
