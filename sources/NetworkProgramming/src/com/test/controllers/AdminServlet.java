package com.test.controllers;

import com.test.models.beans.User;
import com.test.models.bos.UserBO;
import com.test.models.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    private UserBO userBO;

    public AdminServlet() {
        this.userBO = new UserBO(new UserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LoggedInUser");
        if (user != null && user.getRoleId() == 1) {
            switch (uri) {
                case "/admin/add":
                    doAdminAdd(request, response, true);
                    break;
                case "/admin/edit":
                    doAdminEdit(request, response, true);
                    break;
            }
        } else if (user != null){
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LoggedInUser");
        if (user != null && user.getRoleId() == 1) {

            String uri = request.getRequestURI();

            switch (uri) {
                case "/admin":
                    doAdminHomePage(request, response, user.getRoleId());
                    break;
                case "/admin/search":
                    doAdminSearch(request, response);
                    break;
                case "/admin/edit":
                    doAdminEdit(request, response, false);
                    break;
                case "/admin/delete":
                    doAdminDelete(request, response);
                    break;
                case "/admin/add":
                    doAdminAdd(request, response, false);
                    break;
            }
        } else if (user != null){
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/login");
        }

    }



    private void doAdminAdd(HttpServletRequest request, HttpServletResponse response, boolean isPostMethod) throws ServletException, IOException {

        if (isPostMethod) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String email = request.getParameter("email");

            if (!password.equals(confirmPassword)) {
                String error = "Your Confirm Password isn't match the Password";
                request.setAttribute("error", error);
                response.sendRedirect("/admin/add");
            }else {
                // By Default, The registered user's role will be set in User, not admin
                User user = new User(0, username, password, fullname, email, 2);
                userBO.insert(user); // Insert
                response.sendRedirect("/admin");
            }

        } else {
            AuthenticationServlet.forward(request, response, "../user/add.jsp");
        }
    }

    private long getUserId(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("userId"));
        return userId;
    }

    private void doAdminDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = getUserId(request);
        userBO.delete(userId);

        response.sendRedirect("/admin");
    }

    private void doAdminEdit(HttpServletRequest request, HttpServletResponse response, boolean isPostMethod) throws IOException, ServletException {

        if (isPostMethod) {
            User userToUpdate = new User(
                    Long.parseLong(request.getParameter("userId")),
                    request.getParameter("username"),
                    "",
                    request.getParameter("fullname"),
                    request.getParameter("email"),
                    2
            );

            userBO.update(userToUpdate);
            response.sendRedirect("/admin");
            return;
        } else {
            long userId = getUserId(request);

            User user = userBO.getUserById(userId);
            request.setAttribute("userToEdit", user);
            AuthenticationServlet.forward(request, response, "../user/edit.jsp");
        }

    }

    private void doAdminSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String keyWord = request.getParameter("key-word");
        ArrayList<User> users = userBO.search(keyWord);

        request.setAttribute("users", users);
        AuthenticationServlet.forward(request, response, "../admin/index.jsp");
    }

    private void doAdminHomePage(HttpServletRequest request, HttpServletResponse response, long userId) throws ServletException, IOException {
        ArrayList<User> users = userBO.getUsers(userId);
        request.setAttribute("users", users);

        AuthenticationServlet.forward(request, response, "admin/index.jsp");
    }

}
