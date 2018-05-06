<%--
  Created by IntelliJ IDEA.
  User: Kenny
  Date: 5/5/2018
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="/public/css/bootstrap-4.0.min.css">
    <title>Edit Page</title>
</head>
<body>
<%@include file="/includes/top_nav.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="display-4 text-center">Edit Account</h1>
            <%
                String error = (String) request.getAttribute("error");
                User userToEdit = (User) request.getAttribute("userToEdit");
                if (error != null) { %>
            <div class="alert alert-danger">
                <%=error%>
            </div>
            <% } %>

            <% if (userToEdit != null) {%>
            <form name="register" action="/admin/edit" method="post">
                <div class="form-group text-hide">
                    <label>User Id: </label>
                    <input type="text" name="userId" readonly value='<%=userToEdit.getUserId()%>' class="form-control">
                </div>
                <div class="form-group">
                    <label>Fullname: </label>
                    <input type="text" name="fullname" required value='<%=userToEdit.getFullName()%>' class="form-control">
                </div>

                <div class="form-group">
                    <label>Username: </label>
                    <input type="text" name="username" required value='<%=userToEdit.getUsername()%>' class="form-control">
                </div>

                <div class="form-group">
                    <label>Email: </label>
                    <input type="email" name="email" required value='<%=userToEdit.getEmail()%>' class="form-control">
                </div>

                <div class="form-group align-items-center">
                    <input type="submit" name="submit" value="Edit" class="btn btn-success">
                </div>
            </form>
            <%}%>

        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>

<script type="text/javascript" src="/public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/public/js/bootstrap-4.1.min.js"></script>
</body>
</html>
