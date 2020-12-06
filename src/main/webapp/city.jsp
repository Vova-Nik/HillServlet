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
<%--    <script src="https://vova-nik.github.io/get.borders/getborders.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Cities</title>
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

    <div class="form-container">
        <div class="inp inp-text">id to delete:</div>
        <form class="inp form" id="city-form" action="/myApp/city" method="get">
            <input class="inp inpinp" type="number" min="0" max="6000" id="cityid" name="id" value="6000"/>
        </form>
        <button form="city-form" class="inp inp-btn">DELETE</button>
    </div>
    <div class="form-container">
        ${service_message}
    </div>

    <div class="content">${cities}</div>
</div>
$END$
</body>
</html>
