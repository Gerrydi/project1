<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019.4.20
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传案例</title>
</head>
<body>
<h1>springmvc框架选择文件上传</h1>
<form action="/user/uploadFile" method="post" enctype="multipart/form-data">
    选择文件: <input type="file" name="upload"><br>
    <input type="submit" value="点击上传">
</form>
<hr>
<h1>原始方式选择文件上传</h1>
<form action="/user/uploadFile2" method="post" enctype="multipart/form-data">
    选择文件: <input type="file" name="upload"><br>
    <input type="submit" value="点击上传">
</form>
<hr>
</body>
</html>
