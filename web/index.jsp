<%--
  Created by IntelliJ IDEA.
  User: password
  Date: 16-7-6
  Time: 下午6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pffair.SendMessage" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>monitorWebClient</h1>
<%--<button type="button" onclick="sendMessage()">send</button>--%>
<%
    SendMessage sendMessage=new SendMessage();

//    //根据regID，发送消息到指定设备上
//    out.print("result: "+sendMessage.sendMessage());

    //通过指定alias（别名）发送推送 方式一
    out.print("result: "+sendMessage.sendMessageToAlias());

//    //通过指定alias（别名）发送推送 方式二
//    out.print("result: "+sendMessage.sendMessages());
//
//    //通过指定一组alias(别名)发送推送
//    out.print("result: "+sendMessage.sendMessageToAliases());
//
//    //根据topic，发送消息到指定一组设备上
//    out.print("result: "+sendMessage.sendBroadcast());
%>
</body>
</html>
