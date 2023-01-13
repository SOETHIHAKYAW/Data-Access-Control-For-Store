<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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

<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet" />

<style type="text/css">
div {
	padding: 5px;
}

img {
	object-fit: cover;
}
</style>


</head>
<body>
	<div class="container">
		<form action="admin" method="post" class="form-horizontal" role="form"
			enctype="multipart/form-data">
			<h2 class="text-center">Upgrade</h2>


			<h3>ID : ${items.id }</h3>


			<input type="hidden" name="mode" value="UPDATE" /> <input
				type="hidden" name="id" value="${items.id}" />



			<div class="md-3">
				<label for="goodsid" class="form-label">Goods ID</label> <input
					type="text" value="${items.goodsid}" id="goodsid" name="goodsid"
					placeholder="Goods ID" class="form-control" autofocus>

			</div>

			<div class="mb-3">
				<label for="name" class="form-label">Name</label>
				<div class="col-sm-9">
					<input type="text" value="${items.name}" id="name" name="name"
						placeholder="FullName" class="form-control" autofocus>
				</div>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Price </label>
				<div class="col-sm-9">
					<input type="text" value="${items.price}" id="price" name="price"
						placeholder="Price" class="form-control">
				</div>
			</div>

			<div class="mb-3">
				<label for="quantity" class="form-label">Quantity </label>
				<div class="col-sm-9">
					<input type="number" value="${items.quantity}" id="quantity"
						name="quantity" placeholder="Quantity" class="form-control">
				</div>
			</div>

			<div class="mb-3">
				<label for="stockin" class="form-label">Stock In </label>
				<div class="col-sm-9">
					<input type="date" value="${items.stockin}" id="stockin"
						name="stockin" placeholder="Stock In" class="form-control">
				</div>
			</div>
			<div class="mb-3">
				<label for="imgname" class="form-label">Image Name </label>
				<div class="col-sm-9">
					You need to choose Image again!
					<input type="file" value="${items.imgname}" id="file" name="file"
						placeholder="Image Name" class="form-control" required="required">
				</div>
			</div>


			<div align="left">
				<img id="img" alt="img" src="${items.imgname }" width="100px"
					height="100px">
<%-- 					<input type="text" value="${items.imgname}" id="imgname" name="imgname" --%>
<!-- 						placeholder="Imgname" class="form-control" autofocus disabled="disabled"> -->
			</div>

			<div>
				<button type="submit" class="btn btn-primary btn-block">Submit</button>
				<button type="submit" class="btn btn-danger btn-block">Reset</button>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!-- ./container -->

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

</body>
</html>
