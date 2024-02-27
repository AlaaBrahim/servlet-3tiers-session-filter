<%@ page import="com.example.models.User" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Alaa</title>
		<link
			rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
		<style>
			.center {
				text-align: center;
			}
			nav {
				margin: 25px;
			}
			.fit-content {
				width: fit-content;
			}
		</style>
	</head>
	<body>
		<nav>
      <% if (session.getAttribute("user") == null) { %>
        <li><a role="button" href="/login">Login</a></li>
        <li><a role="button" href="/register">Register</a></li>
    <% } else { %>
        <li><a role="button" href="/users">Users</a></li>
    <% } %>
		</nav>
    <main class="container">
      <article>
        <% if (session.getAttribute("user") == null) { %>
          <h2>Welcome to Alaa</h2>
      <% } else { %>
				<jsp:useBean id = "user" class = "com.example.models.User" scope="request"/>
						
			<h2>Welcome <jsp:getProperty name="user" property="name" /></h2>
      <% } %>

        <p>
          This is a simple web application built with Java, Servlets, and
          Postgres.
        </p>
        <% if (session.getAttribute("user") == null) { %>
        <p>
          Login or register to get started.
        </p>
        <% } %>
      </article>
		<footer class="container-fluid center">
			<small
				>Built By
				<a href="https://github.com/AlaaBrahim/" class="secondary"
					>Alaa Brahim</a
				>
			</small>
		</footer>
	</body>
</html>
