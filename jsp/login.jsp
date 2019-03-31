<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3><font color="red">${requestScope.msg}</font></h3>
    <form action="userLogin" method="post">
        用户名:<input type="text" name="username" /><br/>
        密码:<input type="password" name="userpwd"/><br/>
        <input type="submit" value="OK"/>
    </form>
</body>
</html>
