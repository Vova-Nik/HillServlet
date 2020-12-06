<%--
  Created by IntelliJ IDEA.
  User: Vovchik
  Date: 01.12.2020
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=\"UTF-8\">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Countries</title>
</head>
<body>
<div class="content-wrapper">
<div class="btn-container">
    <div class="button">
        <a class="ref" href="/myApp/index">Main page</a>
    </div>
    <div class="button">
        <a class="ref" href="/myApp/city">Cities</a>
    </div>
    <div class="button">
        <a class="ref" href="/myApp/country">Countries</a>
    </div>
</div>

<div class="content">${cities}</div>
</div>
$END$
</body>
</html>
