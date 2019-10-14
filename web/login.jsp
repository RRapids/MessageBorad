<%--
  Created by IntelliJ IDEA.
  User: 79876
  Date: 2019/10/13
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xml:base="login.jsp">
<head>
    <title>留言板登陆界面</title>
</head>
<body bgcolor="#ffe4c4">
<center>
    <h3>JSP学习论坛留言板——前台登录</h3>
    <form action="LoginServlet" method="get">
        账号：<input type="text" name="userCount"><br>
        密码：<input type="text" name="userPassword"><br>
        系统验证码：<input type="" name="verificationCode"><br>
        输入验证码：<input type="text" name="writeVerification"><br>

        <script language="javascript" type="text/javascript">
            var code; //在全局 定义验证码
            function createCode() {
                code = "";
                var codeLength = 6;//验证码的长度
                var checkCode = document.getElementById("checkCode");
                var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');

                for (var i = 0; i < codeLength; i++) {
                    var charIndex = Math.floor(Math.random() * 36);
                    code += selectChar[charIndex];
                }
                //alert(code);
                if (checkCode) {
                    checkCode.className = "code";
                    checkCode.value = code;
                }
            }

            function validate() {
                var inputCode = document.getElementById("input1").value;
                if (inputCode.length <= 0) {
                    alert("请输入验证码！");
                } else if (inputCode != code) {
                    alert("验证码输入错误！");
                    createCode();//刷新验证码
                } else {
                    alert("^-^ OK");
                }
            }
        </script>
        <input type="submit" value="确定">
        <input type="reset" value="重置">
    </form>
    <a href="registration.jsp">还没注册？请点击注册！</a>
</center>

</body>
</html>
