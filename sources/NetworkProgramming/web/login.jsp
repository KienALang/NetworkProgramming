<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="public/css/bootstrap-4.0.min.css">
    <title>Login</title>
</head>
<body>
<%@include file="includes/top_nav.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <h1 class="display-4 text-center">Login</h1>
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) { %>
            <div class="alert alert-danger">
                <%=error%>
            </div>
            <a class="d-block text-center" href="/register">Register new Account here.</a>
            <% } %>
            <form name="login" action="/login" method="post">
                <div class="form-group">
                    <label>Username: </label>
                    <input type="text" name="username" required class="form-control">
                </div>
                <div class="form-group">
                    <label>Password: </label>
                    <input type="password" name="password" required class="form-control">
                </div>
                <div class="form-group text-center">
                    <input type="submit" name="submit" value="Login" class="btn btn-success btn-lg">
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>

<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="public/js/bootstrap-4.1.min.js"></script>
</body>
</html>
