<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="public/css/bootstrap-4.0.min.css">
    <title>Home Page</title>
</head>
<body>
<%@include file="includes/top_nav.jsp"%>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Hello, world!</h1>
        <p class="lead">This is the JSP MVC Project.<br><i>Started date: 04/05/2018</i><br><i>Completed date: 06/05/2018</i></p>
        <hr class="my-4">
        <p>This project was programmed by Kenny.</p>
        <p>To Login with User Role: use username: kien, and password: 123456</p>
        <p>To Login with Admin Role: use username: kenny, and password: 123456</p>
    </div>
</div>
<%@include file="includes/footer.jsp"%>

<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="public/js/bootstrap-4.1.min.js"></script>
</body>
</html>
