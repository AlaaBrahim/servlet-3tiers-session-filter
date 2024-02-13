<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.models.User" %>

<%
    // Retrieve the list of users from the request attribute
    List<User> users = (List<User>) request.getAttribute("users");
%>

<html>
<head>
    <title>Show Users</title>
    <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
</head>
<body>
  <nav>
    <ul>
      <li><a role="button" href="/logout">Logout</a></li>
    </ul>
  </nav>
<main class="container">
    <h2>All Users</h2>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <% for (User user : users) { %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
