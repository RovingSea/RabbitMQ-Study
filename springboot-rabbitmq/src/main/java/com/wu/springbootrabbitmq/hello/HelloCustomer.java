package com.wu.springbootrabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 默认是持久化、非独占、不是自动删除
 * @author Haixin Wu
 * @date 2022/1/1 14:47
 * @since 1.0
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "false", autoDelete = "true"))
public class HelloCustomer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("message = " + message);
    }
}