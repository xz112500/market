package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectExChanges {
    @Bean
    public DirectExchange DirectExchange(){
        return new DirectExchange("direct_message",true,false);
    }
    @Bean
    public Queue directQueue1(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",120000);
        return new Queue("email",true,false,false,args);
    }
    @Bean
    public Queue directQueue2(){
        return new Queue("order",true);
    }
    @Bean
    public Binding LoginBinding(){
        return BindingBuilder.bind(directQueue1()).to(DirectExchange()).with("email_direct");
    }
    @Bean
    public Binding OrderBinding(){
        return BindingBuilder.bind(directQueue2()).to(DirectExchange()).with("order_direct");
    }
}
