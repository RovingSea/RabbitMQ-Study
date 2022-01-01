package com.wu.springbootrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Haixin Wu
 * @date 2022/1/1 17:28
 * @since 1.0
 */
@Component
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")// 指定交换机
            )
    })
    public void receive1(String message){
        System.out.println("消费者1：message = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")// 指定交换机
            )
    })
    public void receive2(String message){
        System.out.println("消费者2：message = " + message);
    }
}

