<%--
  Created by IntelliJ IDEA.
  User: Vovchik
  Date: 10.12.2020
  Time: 08:35
  To change this template use File | Settings | File Templates.
--%>
<%--@ page contentType="text/html;charset=UTF-8" language="java" --%>
<html>
<head>
    <meta charset=\"UTF-8\">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>World DB</title>
</head>
<body>
<div class="content-wrapper">
    <div class="btn-container">
        <div class="button countries-btn">
            Countries
        </div>
        <div class="button cities-btn">
            Cities
        </div>
        <div class="button stat-btn">
            Statistics
        </div>

    </div>
    <div class="download-btn">
        Download JSON
    </div>

    <div class="content"></div>
    <div class="message1">Welcome to World databese site</div>
    <div class="message2">Chose what to do</div>

    <div class="copyright">By Volodymyr Nikolenko. Java Elementary. HW26 Odessa Hillel 2020</div>
    <a class="download" href="data:application/xml;charset=utf-8" download="worldData.json"></a>
</div>
</body>
<script src="world.js"></script>
</html>
