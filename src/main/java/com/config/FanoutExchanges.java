package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchanges {
    /**
     * description:
     * @author: xz
     * @time: 2021/6/1
     */
    //声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchangeConfig(){
        return  new FanoutExchange("fanout_order_exchange",true,false);
    }

    //声明队列sms.fanout.queue email.fanout.queue duanxin.fanout.queue
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true);
    }

    @Bean
    public Queue duanxinQueue(){
        return  new Queue("duanxin.fanout.queue",true);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true);
    }

    //完成关系绑定(队列和交换机完成绑定关系)
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchangeConfig());
    }

    @Bean
    public Binding duanxinBinding(){
        return BindingBuilder.bind(duanxinQueue()).to(fanoutExchangeConfig());
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchangeConfig());
    }
}

