<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cas-Demo</title>
</head>
<body>
    欢迎  <%=request.getRemoteUser()%> 来到品优购！ port:28084<br/>

    <a href="http://localhost:28083/cas/logout?service=http://www.itheima.com">退出</a>
</body>
</html>
