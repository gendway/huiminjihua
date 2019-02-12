package com.pinyougou.page.service.mq;

import com.pinyougou.model.Item;
import com.pinyougou.mq.MessageInfo;
import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 18:28
 *
 ****/
public class TopicMessageListener implements MessageListener {

    @Autowired
    private ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {
        //消息类型判断
        if(message instanceof ObjectMessage){
            //强转ObjectMessage
            ObjectMessage objectMessage = (ObjectMessage) message;

            try {
                //获取内容强转成MessageInfo
                MessageInfo messageInfo = (MessageInfo) objectMessage.getObject();
                //判断操作类型
                if(messageInfo.getMethod()==MessageInfo.METHOD_UPDATE){
                    //审核通过,获取内容，实现静态页创建
                    List<Item> items = (List<Item>) messageInfo.getContext();

                    //获取所有GoodsId,并去除重复
                    Set<Long> ids = getGoodsIds(items);

                    //循环创建静态页
                    for (Long id : ids) {
                        //生成静态页
                        itemPageService.buildHtml(id);
                    }
                }else if(messageInfo.getMethod()==MessageInfo.METHOD_DELETE){
                    //删除，获取商品ID，循环删除
                    //获取商品ID
                    List<Long> ids = (List<Long>) messageInfo.getContext();

                    //删除静态页
                    for (Long id : ids) {
                        itemPageService.deleteHtml(id);
                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /***
     * 去除List<Item>中GoodsId的重复数据
     * @param items
     * @return
     */
    public Set<Long> getGoodsIds(List<Item> items){
        //定义一个Set用于去除重复
        Set<Long> ids = new HashSet<Long>();

        for (Item item : items) {
            ids.add(item.getGoodsId());
        }
        return ids;
    }
}
