package com.wu.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Haixin Wu
 * @date 2021/12/26 15:37
 * @since 1.0
 */
public class RabbitMQUtils {
    private static final ConnectionFactory CONNECTION_FACTORY = new ConnectionFactory();
    static {
        CONNECTION_FACTORY.setHost("180.76.136.123");
        //TCP通讯接口
        CONNECTION_FACTORY.setPort(13399);
        CONNECTION_FACTORY.setVirtualHost("/wu_rabbitmq_study");
        CONNECTION_FACTORY.setUsername("wu");
        CONNECTION_FACTORY.setPassword("123");
    }

    public static Connection getConnection(){
        try {
            //从工厂获取连接对象
            return CONNECTION_FACTORY.newConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel, Connection connection){
        try {
            if (channel != null){
                channel.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}

