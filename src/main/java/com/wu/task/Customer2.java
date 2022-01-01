package com.wu.task;

import com.rabbitmq.client.*;
import com.wu.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2021/12/26 16:45
 * @since 1.0
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",false,false,false,null);
        channel.basicConsume("work",false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 模拟耗时较短的消息
                System.out.println("Customer2: " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}

