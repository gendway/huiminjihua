package com.itheima.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 16:28
 *
 ****/
@Component
public class messageClient {
    @Autowired
    private MessageSender messageSender;

    /***
     * 监听message短信
     * @param map
     */
    @JmsListener(destination = "message")
    public void readMapMessage(Map<String, String> map) throws Exception {
        String mobile = map.get("mobile");
        String templateCode = map.get("templateCode");
        String signName = map.get("signName");
        String param = map.get("param");

        //消息发送
        messageSender.sendSms(mobile,signName,templateCode,param);
    }

}
