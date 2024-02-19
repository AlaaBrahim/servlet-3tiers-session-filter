package com.example.listners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.database.DB;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        Connection connection = null;
        try {
            connection = DB.connect();

            System.out.println("Connected to the PostgreSQL database.");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        ServletContext context = sce.getServletContext();
        context.setAttribute("DBConnection", connection);
    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("DBConnection");
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println("ServletContextListener destroyed");
    }

}
