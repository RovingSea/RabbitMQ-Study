package com.wu.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitmqApplication.class)
@RunWith(SpringRunner.class)
class RabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // topics
    @Test
    void topicsTest(){
        rabbitTemplate.convertAndSend("topics","user.save","user.save 消息");
        rabbitTemplate.convertAndSend("topics","user.save.xx","user.save.xx 消息");
    }


    // routing
    @Test
    void routingTest(){
        rabbitTemplate.convertAndSend("directs","error","发送error的key的路由信息");
    }

    // fanout
    @Test
    void fanoutTest(){
        rabbitTemplate.convertAndSend("logs", "", "fanout模型发送消息");
    }

    // work
    @Test
    void workTest(){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("work","work模型");
        }
    }


    /**
     * hello world<br>
     * 我来解释一下，在springboot整合一中，小伙伴的疑问。<br>
     * 在helloword模式中，生产端，指定的routingKey:hello。<br>
     * 这里指定的是路由规则，并不是老师说的hello队列。<br>
     * 队列是在消费方生成的，所以按照老师的说法，好多小伙伴都很疑惑。<br>
     * 其实是这样的，在老师的生产端没有指定交换机只有routingKey和Object，也就是说这个消费方产生hello队列，放在默认的交换机(AMQP default)上。<br>
     * 而默认的交换机有一个特点，只要你的routerKey与这个交换机中有同名的队列，他就会自动路由上。<br>
     * 生产端routingKey 叫hello ，消费端生产hello队列。他们就路由上了<br>
     * <br>
     * <br>
     * <br>
     * 感谢提点，自己去测试了一下。<br>
     * 如果不指定交换机，生产者会将消息发布给AMQP default交换机；<br>
     * 而每一个队列，无论后天绑定了哪个交换机，先天会默认绑定AMQP default交换机（无法解绑，这个交换机也无法被删除）；<br>
     * 而这个交换机的匹配方式，是通过生产者的routingKey匹配队列的queue name；<br>
     * 这就解释了为什么不指定交换机时，会发送给名称为routingKey的队列。
     */
    @Test
    void helloWorldTest() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

}
