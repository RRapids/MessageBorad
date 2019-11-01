<%@ page import="com.sun.org.apache.xerces.internal.xs.XSTerm" %><%--
  Created by IntelliJ IDEA.
  User: 79876
  Date: 2019/10/20
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xml:base="login_success.jsp">
<head>
    <title>登录成功</title>
</head>
<%
    String str_count = (String) application.getAttribute("counter");
    if (str_count != null) {
        int counter = Integer.parseInt(str_count);
        counter++;
        application.setAttribute("counter", counter + "");
    } else {
        application.setAttribute("counter", "1");
    }
%>
<body>

<%
    String u = (String) session.getAttribute("name");
    //如果用户没有登录
    if (u == null) {
        //用户非法登录，返回登录界面
        response.sendRedirect("login.html");
    }
%>
<center>
    <h5>JSP学习论坛留言板</h5><br>
    <%
        if (session.getAttribute("name") != null) {
            //用户已登录
    %><font color="green">
    <h2>登录成功!
        欢迎<font size="12" color="green">
            <%=session.getAttribute("name")%>
        </font>光临留言板
    </h2>
    <h3>
        你是第<%=application.getAttribute("counter")%>个用户
    </h3>
    <a href="">用户管理</a>
    <a href="list_note.jsp">进入留言板</a>

    <%}
        else {
           //用户未登录
    	response.setHeader("refresh", "2;URL=login.html");
        }%>

</center>

</body>
</html>
