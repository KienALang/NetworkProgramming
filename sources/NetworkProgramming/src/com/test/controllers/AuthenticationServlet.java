package com.test.controllers;

import com.test.models.beans.User;
import com.test.models.bos.UserBO;
import com.test.models.daos.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
    private UserBO userBO;

    public AuthenticationServlet() {
        this.userBO = new UserBO(new UserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        switch (uri) {
            case "/login":
                doLogin(request, response, true);
                break;
            case "/register":
                doRegister(request, response, true);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        switch (uri) {
            case "/login":
                doLogin(request, response, false);
                break;
            case "/register":
                doRegister(request, response, false);
                break;
            case "/logout":
                doLogout(request, response);
                break;
        }

    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("LoggedInUser");
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response, boolean isPostMethod) throws IOException, ServletException {
        if (isPostMethod) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userBO.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LoggedInUser", user);
                if (user.getRoleId() == 1) {
                    response.sendRedirect("/admin");
                }else {
                    response.sendRedirect("/user");
                }

            } else {
                String error = "Invalid username or password";
                request.setAttribute("error", error);
                forward(request, response, "login.jsp");
            }
        } else {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LoggedInUser");

            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
                redirectToAuthenticatedPage(response, user.getRoleId());
            }
        }
    }

    public void redirectToAuthenticatedPage(HttpServletResponse response, long roleId) throws IOException {
        String uri = (roleId == 1) ? "/admin" : "/user";
        response.sendRedirect(uri);
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String jspLocation) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspLocation);
        dispatcher.forward(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response, boolean isPostMethod) throws IOException {
        if (isPostMethod) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String email = request.getParameter("email");

            if (!password.equals(confirmPassword)) {
                String error = "Your Confirm Password isn't match the Password";
                request.setAttribute("error", error);
                response.sendRedirect("/register");
            }else {
                // By Default, The registered user's role will be set in User, not admin
                User user = new User(0, username, password, fullname, email, 2);
                userBO.insert(user); // Insert
                user = userBO.getUserByUsernameAndPassword(username, password); // Get it back

                HttpSession session = request.getSession();
                session.setAttribute("LoggedInUser", user);
                response.sendRedirect("/admin");
            }
        } else {
            response.sendRedirect("register.jsp");
        }
    }
}
