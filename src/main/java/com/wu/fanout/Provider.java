package com.wu.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wu.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2021/12/30 20:34
 * @since 1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 让通道声明一个交换机
         * 参数1.交换机名称
         * 参数2.交换机类型
         */
        channel.exchangeDeclare("logs", "fanout");
        /**
         * 发送消息
         */
        channel.basicPublish("logs","",null,"faount type message".getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}

