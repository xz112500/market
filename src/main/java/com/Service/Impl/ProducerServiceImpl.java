package com.Service.Impl;

import com.Service.ProducerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("Producer")
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public void sendMail(String queue, String key, JSONObject jsonObject) {
         rabbitTemplate.convertAndSend(queue,key,jsonObject);
    }
}
