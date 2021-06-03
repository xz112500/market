package com.util;



import lombok.Getter;

@Getter
public class RabbitmqUtils {
    public static final String DIRECT_EXCHANGES = "direct_message";
    public static final String DIRECT_QUEUE1= "email";
    public static final String DIRECT_QUEUE2= "order";
    public static final String DIRECT_QUEUE1_KEY= "email_direct";
    public static final String DIRECT_QUEUE2_KEY= "order_direct";
    public static final String FANOUT_EXCHANGES = "fanout_order_exchange";
    public static final String SMS_QUEUE = "sms.fanout.queue";
    public static final String DUANXIN_QUEUE= "duanxin.fanout.queue";
    public static final String EMAIL_QUEUE= "email.fanout.queue";
}
