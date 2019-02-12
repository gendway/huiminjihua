package com.pinyougou.search.service.mq;

import com.pinyougou.model.Item;
import com.pinyougou.mq.MessageInfo;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 18:19
 *
 ****/
public class TopicMessageListener implements MessageListener {

    @Autowired
    private ItemSearchService itemSearchService;


    @Override
    public void onMessage(Message message) {
        //解析数据
        if(message instanceof ObjectMessage){
            //强转成ObjectMessage
            ObjectMessage objectMessage = (ObjectMessage) message;

            try {
                //获取消息内容，强转成MessageInfo
                MessageInfo messageInfo = (MessageInfo) objectMessage.getObject();

                //如果是修改，则增加索引
                if(messageInfo.getMethod()==MessageInfo.METHOD_UPDATE){
                    //获取传输的数据
                    List<Item> items = (List<Item>) messageInfo.getContext();
                    itemSearchService.importList(items);
                }else if(messageInfo.getMethod()==MessageInfo.METHOD_DELETE){
                    List<Long> ids = (List<Long>) messageInfo.getContext();
                    //如果是删除，则删除索引
                    itemSearchService.deleteByGoodsIds(ids);   //ids
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }


        }
    }
}
