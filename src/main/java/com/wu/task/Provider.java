package com.wu.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wu.utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Haixin Wu
 * @date 2021/12/26 16:44
 * @since 1.0
 */
public class Provider {
    @Test
    public void taskProviderTest() throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",false,false,false,null);
        for (int i = 0; i < 200; i++) {
            channel.basicPublish("","work",null,(i + " hello task").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}

