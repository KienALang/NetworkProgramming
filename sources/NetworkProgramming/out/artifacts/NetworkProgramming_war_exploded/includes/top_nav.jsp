<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.models.beans.User" %>
<% User user = (User) session.getAttribute("LoggedInUser"); %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">JSP MVC</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <% if (user == null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <% } %>
                <% if (user != null) { %>
                <li class="nav-item dropdown justify-content-end">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <%=user.getFullName()%>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/logout">Logout</a>
                        <% if (user.getRoleId() == 1) {%>
                        <a class="dropdown-item" href="/admin">User Accounts</a>
                        <%}%>
                    </div>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
