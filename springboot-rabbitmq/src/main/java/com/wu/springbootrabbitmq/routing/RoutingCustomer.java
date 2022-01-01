package com.wu.springbootrabbitmq.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Haixin Wu
 * @date 2022/1/1 17:35
 * @since 1.0
 */
@Component
public class RoutingCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建临时队列
                    exchange = @Exchange(value = "directs", type = "direct"),// 指定交换机
                    key = {"info","error","warn"}
            )
    })
    public void receive1(String message){
        System.out.println("消费者1：message = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建临时队列
                    exchange = @Exchange(value = "directs", type = "direct"),// 指定交换机
                    key = {"error"}
            )
    })
    public void receive2(String message){
        System.out.println("消费者2：message = " + message);
    }
}

