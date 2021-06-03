package com.Service;

import com.alibaba.fastjson.JSONObject;

public interface ProducerService {
    void sendMail(String queue, String key, JSONObject jsonObject);
}
