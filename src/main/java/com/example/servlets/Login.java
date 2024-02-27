package com.example.servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDao;
import com.example.models.User;

@WebServlet("/login")
public class Login extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        connection = (Connection) context.getAttribute("DBConnection");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao(connection);
        User user = userDao.validateUser(email, password);
        if (user != null) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            // generate a new session
            HttpSession newSession = request.getSession(true);

            // setting session to expiry in 5 mins
            newSession.setMaxInactiveInterval(5 * 60);
            newSession.setAttribute("user", user.getName());

            request.setAttribute("user", user);

            request.getRequestDispatcher("/").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
