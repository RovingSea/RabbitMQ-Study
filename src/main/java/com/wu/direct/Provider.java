package com.wu.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wu.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2021/12/31 16:20
 * @since 1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通过通道声明交换机  参数1：交换机名称  参数2：路由模式
        channel.exchangeDeclare("logs_direct", "direct");
        // 发送消息
        String routingKey = "error";
        channel.basicPublish("logs_direct", routingKey, null, ("这是direct模型发布的基于route key：["+routingKey+"]发送的消息").getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}