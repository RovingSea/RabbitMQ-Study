package com.wu.direct;

import com.rabbitmq.client.*;
import com.wu.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2021/12/31 16:26
 * @since 1.0
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道声明交换机和交换机的类型
        channel.exchangeDeclare("logs_direct", "direct");
        // 创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        // 基于route key绑定队列和交换机
        channel.queueBind(queue, "logs_direct", "error");
        // 获取队列中的消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}

