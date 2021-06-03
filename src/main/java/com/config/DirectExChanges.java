package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExChanges {
    @Bean
    public DirectExchange DirectExchange(){
        return new DirectExchange("direct_message",true,false);
    }
    @Bean
    public Queue directQueue1(){
        return new Queue("email",true);
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
