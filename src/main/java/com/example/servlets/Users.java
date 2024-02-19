package com.example.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDao;
import com.example.models.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/users")
public class Users extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        connection = (Connection) context.getAttribute("DBConnection");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<User> users = new UserDao(connection).getAllUsers();
        System.out.println(users);

        // Set the list of users as a request attribute
        request.setAttribute("users", users);

        // Forward the request to the showUsers.jsp page
        request.getRequestDispatcher("/home.jsp").forward(request, response);

    }
}
