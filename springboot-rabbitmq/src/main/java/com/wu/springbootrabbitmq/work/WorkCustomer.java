package com.wu.springbootrabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 任务模型中，Spring AMQP实现的默认方式是公平调度
 * @author Haixin Wu
 * @date 2022/1/1 17:15
 * @since 1.0
 */
@Component
public class WorkCustomer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("消费者1：message = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("消费者2：message = " + message);
    }

}

