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
<title>SALE TABLE</title>
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
<link rel="icon" type="image/x-icon" href="assets/store1.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet" />
</head>
<body id="body">

	<c:if test="${user.role == 'user'}">
	 
		<c:redirect url="home-page.html"> <a href=""></a> </c:redirect>
	
	</c:if>

	<div class="container"></div>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#"><img id="logo"
				src="assets/shops.jpg" alt="logo" /> ADEN STORE</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="admin">Items Table</a></li>
					<li class="nav-item dropdown">
					
						<a class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false"> <c:out value="${user.userName }"></c:out> </a>
							<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="home-page.html">Products</a></li>
							<li><a class="dropdown-item" href="admin">Factory</a></li>
							<li><a class="dropdown-item" href="signup-form.html">Sign Up</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="login?mode=LOGOUT">Exit</a></li>
									
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- Page content-->
	<div class="container mt-5">
		<table id="saleItems" class="table table-striped" style="width: 100%">
			<h3 align="center"> Sale Table</h3>
			<thead>
				<tr>
					<th>ID</th>
					<th>GoodsID</th>
					<th>Goods Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
<%-- 					<c:if test="${user.role == 'admin'}"> --%>
					<th>Action</th>
<%-- 					</c:if> --%>
				</tr>
			</thead>
			
			<tbody>
				
				<c:forEach var="sale" items="${saleItemsList }">
				
				<c:url var="update" value="sale">				
					<c:param name="mode" value="LOAD"></c:param>
					<c:param name="id" value="${sale.id}"></c:param>
				
				</c:url>
				
				<c:url var="delete" value="sale">				
					<c:param name="mode" value="DELETE"></c:param>
					<c:param name="id" value="${sale.id}"></c:param>
				
				</c:url>
				
					<tr>
						<td> <c:out value="${sale.id}"></c:out> </td>
						<td> <c:out value="${sale.goodsid}"></c:out> </td>
						<td> <c:out value="${sale.name}"></c:out> </td>
						<td> <c:out value="${sale.price}"></c:out> </td>
						<td> <c:out value="${sale.quantity}"></c:out> </td>
						<td> <c:out value="${sale.price * sale.quantity}"></c:out> </td>
<%-- 						<c:if test="${user.role == 'admin'}">					 --%>
						<td> 
							<a class="btn btn-primary" href="${update}"> Edit </a>
							<a class="btn btn-danger" id="delete" href="${delete}"> Delete </a>
						</td>
<%-- 						</c:if> --%>
					</tr>
									
				</c:forEach>
				
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>GoodsID</th>
					<th>Goods Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
<%-- 					<c:if test="${user.role == 'admin'}"> --%>
					<th>Action</th>
<%-- 					</c:if> --%>
				</tr>
			</tfoot>
		</table>

	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#saleItems').DataTable();
			
			$("#delete").click(function(){
				return confirm("Are You Sure Want to Delete?");
			});
				
		});
	</script>
</body>
</html>
