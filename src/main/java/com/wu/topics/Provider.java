package com.wu.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wu.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2022/1/1 14:00
 * @since 1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String routeKey = "user.service.save";
        channel.basicPublish("topics",routeKey,null,("topic模型,routeKey: "+routeKey).getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}

