<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<link
			rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	</head>
	<body>
		<main class="container">
			<article>
				<h2>Login</h2>
				<form action="login" method="post">
					<fieldset role="group">
						<input
							name="email"
							type="email"
							placeholder="Email"
							autocomplete="email" />
						<input name="password" type="password" placeholder="Password" />
						<input type="submit" value="Log in" />
					</fieldset>
				</form>
			</article>
		</main>
	</body>
</html>
