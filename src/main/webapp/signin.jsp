<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>ADEN STORE</title>
<link rel="icon" type="image/x-icon" href="assets/imgs/store.ico" />
<!-- CDN-->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
<!-- Favicon-->
<!-- <link rel="icon" type="image/x-icon" href="assets/store1.ico" /> -->

<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet" />
</head>
<body>
	<div class="container">

		<form action="login" method="post">

			<h2 class="text-center">Please Sign in</h2>
			
			<input type="hidden" name="mode" value="SIGNIN">
			
			<c:if test="${loginFail}">
			
			<div class="mb-3">
				<span class="alert alert-danger">Your Email or Password is incorrect.Please Try again.</span>
			</div>
			</c:if>
			<div class="mb-3">
				<label for="email" class="form-label">Email
					address</label> <input type="email" name="email" class="form-control"
					id="eamil" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
				<input type="password" name="password" class="form-control"
					id="password">
			</div>
			
			
			
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

		<p>Doesn't have an account? <a href="signup.jsp">Sign Up</a> </p>

	</div>
	<!-- ./container -->
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

	<!-- Latest compiled and minified CSS 
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
		-->
	<!-- Latest compiled JavaScript 
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
		-->


</body>
</html>


