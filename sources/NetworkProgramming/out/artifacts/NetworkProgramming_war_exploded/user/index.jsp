<%--
  Created by IntelliJ IDEA.
  User: Kenny
  Date: 5/5/2018
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="public/css/bootstrap-4.0.min.css">
    <title>Authenticated User Page</title>
</head>
<body>
<%@include file="/includes/top_nav.jsp"%>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Hello <%=user.getFullName()%></h1>
        <p class="lead">Register Successfully.</p>
        <hr class="my-4">
        <p>This is your page.</p>
        <a class="btn btn-primary btn-lg" href="#" role="button">About me</a>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>

<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="public/js/bootstrap-4.1.min.js"></script>
</body>
</html>
