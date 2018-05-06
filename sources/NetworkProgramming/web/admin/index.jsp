<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Kenny
  Date: 5/4/2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.servletContext.contextPath}/public/css/bootstrap-4.0.min.css">
    <title>Admin Home Page</title>
</head>
<body>
<%@include file="/includes/top_nav.jsp" %>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">This is Admin page</h1>
        <p class="lead">This Admin has the highest role, he can manage the Users.</p>
        <hr class="my-4">
    </div>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <form name="search-form" action="/admin/search" method="get">
                <div class="form-group">
                    <label>Search</label>
                    <input type="text" name="key-word" class="form-control" placeholder="Enter your key word">
                </div>
                <div class="form-group text-center">
                    <input type="submit" value="Search" class="btn btn-success">
                </div>
            </form>
            <hr class="my-4">

            <div class="form-group">
                <a class="btn btn-primary" href="/admin/add">Add new User</a>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Handle</th>
                </tr>
                </thead>
                <% ArrayList<User> users = (ArrayList) request.getAttribute("users"); %>
                <tbody>
                <% if (users != null) {
                    for (User u : users) { %>
                <tr>
                    <td><%=u.getUserId()%>
                    </td>
                    <td><%=u.getUsername()%>
                    </td>
                    <td><%=u.getFullName()%>
                    </td>
                    <td><%=u.getEmail()%>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="/admin/edit?userId=<%=u.getUserId()%>">Edit</a>
                        <a class="btn btn-danger" href="/admin/delete?userId=<%=u.getUserId()%>">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/js/bootstrap-4.1.min.js"></script>
</body>
</html>
