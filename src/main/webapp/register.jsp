<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Registration</title>
		<link
			rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	</head>
	<body>
		<main class="container">
			<article>
				<h2>User Registration</h2>
				<form action="register" method="post">
					<label for="name">Name:</label>
					<input type="text" id="name" name="name" required />

					<label for="email">Email:</label>
					<input type="email" id="email" name="email" required />

					<label for="password">Password:</label>
					<input type="password" id="password" name="password" required />

					<button type="submit" class="btn">Register</button>
				</form>
			</article>
		</main>
	</body>
</html>
