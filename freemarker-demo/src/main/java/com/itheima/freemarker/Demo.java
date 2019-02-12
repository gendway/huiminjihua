package com.itheima.freemarker;

import com.itheima.domain.User;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/17 15:02
 *
 ****/
public class Demo {

    public static void main(String[] args) throws Exception{
        //准备数据模型   可以是任何java数据类型 也可是JavaBean,推荐使用Map
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("username","小红");
        dataMap.put("now","2018年9月17日15:04:01");

        //boolean值
        dataMap.put("success",false);

        //构建一个集合
        List<User> users = new ArrayList<User>();
        users.add(new User(998,"小红","深圳"));
        users.add(new User(666,"小黑","武汉"));
        users.add(new User(888,"小黄","天津"));
        dataMap.put("users",users);

        //创建一个日期
        dataMap.put("nowtime",new Date());


        //存入一个数字类型
        dataMap.put("point",324567);

        //随意创建一个对象
        dataMap.put("abc",null);

        //配置Freemarker
        //创建Configuration,freemarker初始化相关配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        //设置模板路径
        configuration.setDirectoryForTemplateLoading(new File("D:/project/workspace40/pinyougou/freemarker-demo/src/main/resources"));

        //设置模板内容编码
        configuration.setDefaultEncoding("UTF-8");

        //创建模板对象
        Template template = configuration.getTemplate("test.ftl");

        //指定输出文件
        Writer writer= new FileWriter("D:/1.html");

        //合成输出
        template.process(dataMap,writer);

        //关闭资源
        writer.flush();
        writer.close();
    }

}
