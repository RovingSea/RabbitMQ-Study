package com.wu.helloworld;

import com.rabbitmq.client.*;
import com.wu.utils.RabbitMQUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Haixin Wu
 * @date 2021/12/25 21:20
 * @since 1.0
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接mq的连接工厂对象
        Connection connection = RabbitMQUtils.getConnection();
        //从连接中创建一个通道对象
        Channel channel = connection.createChannel();
        /**
         * 通道绑定对应的消息队列
         * 参数1：队列名称，如果队列不存在自动创建
         * 参数2：定义的队列是否持久化？持久化会将队列写入磁盘，重启后仍存在
         * 参数3：是否独占队列？独占表示一个队列只允许当前连接可用
         * 参数4：是否在消费完成后自动消除队列？
         * 参数5：附加参数
         */
        channel.queueDeclare("hello", false, false, false, null);
        /**
         * 消费消息
         * 参数1：队列名称
         * 参数2：开始消息时的自动确认机制
         * 参数3：消费时的回调接口
         */
        channel.basicConsume("hello", true, new DefaultConsumer(channel){
            @Override // 最后一个参数：消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });
        //不建议关闭，因为消费者要一直监听
    }
}

