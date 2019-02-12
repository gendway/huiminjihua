<html>
<head>
    <title>模板技术</title>

<#--
    #assign :定义一个简单的值类型
            :可以定义一个对象[JSON]
-->
    <#assign linkman="张三" />
    <#assign userinfo={"mobile":"1367000000","address":"中南海"} />
</head>
<body>
<div>
    <h4>include:用于包含一个其他文件</h4>
    <div>
        <#include "head.ftl">
    </div>
</div>
<pre>
    尊敬的领导，${username}，我告辞了！
    公司很好，福利好，待遇好，就是钱少！

                        署名:${username}
                        时间:${now}
    </pre>

<div>
    <h4>#assign</h4>
    <div>
        我叫 ${linkman}! 手机号是${userinfo.mobile}, 在${userinfo.address}扛把子！
    </div>
</div>

<div>
    <h4>#if条件判断</h4>
    <div>
        <#if success==true>
            恭喜你你的信息审核通过~！
        <#else>
            恭喜你你的信息审核未通过~！
        </#if>
    </div>
</div>

<div>
    <h3>List集合输出</h3>
    <div>
        <#list users as user>
            <div>
                ${user.id}---${user.username}---${user.address}
            </div>
        </#list>
    </div>
</div>

<div>
    <h3>内建函数:变量?函数名字</h3>
    users中公有 ${users?size} 条记录
</div>

<div>
    将字符转JSON
    <#assign text="{'bank':'工商银行','account':'1234567890'}" />
    <#assign data=text?eval />

    输出JSON数据:${data.bank}在深圳市有100家分点！我在${data.bank}的账号是${data.account}!
</div>

<div>
    <h3>时间类型输出</h3>
    ${nowtime?date}<br/>
    ${nowtime?time}<br/>
    ${nowtime?datetime}<br/>
    ${nowtime?string('yyyy年MM月dd日')}

</div>
<div>

    <h3>数字转字符</h3>

    包含千分位：${point}<br/>

    不包含千分位：${point?c}

</div>

<div>
    <h3>判断对象是否存在</h3>
    <#if abc??>
        对象存在！
    <#else>
        对象不存在！
    </#if>
</div>

<div>
    <h3>缺省值</h3>

    ${abc!"小红思密达"}

</div>
</pre>
</body>
</html>
