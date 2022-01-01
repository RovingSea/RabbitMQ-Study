package com.wu.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wu.utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Haixin Wu
 * @date 2021/12/25 20:58
 * @since 1.0
 */
public class Provider {
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
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
         * 发布消息
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：传递消息额外设置
         * 参数4：消息的具体内容
         */
        channel.basicPublish("","hello", null,"hello rabbit".getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}

