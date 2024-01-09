<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>


<body>

<div id="loginDiv" style="height: 350px">
    <form action="/brand-case/loginServlet" id="form" method="post">
        <h1 id="loginMsg">欢迎登录</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <p>用户名:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>
        <p>密&nbsp&nbsp&nbsp码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>记住用户:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" align="center" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" class="button" align="center"  value="重置">&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？点击注册</a>
        </div>
    </form>
</div>

</body>
</html>