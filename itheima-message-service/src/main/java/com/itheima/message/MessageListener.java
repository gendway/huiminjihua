package com.itheima.message;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 16:04
 *
 ****/
@Component
public class MessageListener {


    @Autowired
    private MessageSender messageSender;

    /****
     * 接收各大平台的短信
     * dataMap:
     *          签名、
     *          模板、
     *          发送的手机号、
     *          模板对应的参数
     */
    @JmsListener(destination = "message-list")
    public void readMessage(Map<String,String> dataMap) throws ClientException {
        //调用阿里大鱼实现短信发送
        SendSmsResponse response = messageSender.sendSms(dataMap.get("signName"),
                                dataMap.get("templateCode"),
                                dataMap.get("mobile"),
                                dataMap.get("param"));

        System.out.println("发送消息的结果数据："+response.getMessage());
    }

}
