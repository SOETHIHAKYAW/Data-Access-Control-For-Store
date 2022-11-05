<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

 <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>

<meta charset="UTF-8">
<title>Phone Collection</title>

 <link rel="icon" type="image/x-icon" href="assets/imgs/store.ico" />
  <!-- Bootstrap + Dorang main styles -->
	<link rel="stylesheet" href="assets/css/dorang.css">

<style type="text/css">

	#div{
		padding: 15px;
		
	}
	
</style>

	
</head>
<body class="bg-dark">
		<h3 align="center">This is Phone Series that are most comfortable to buy and most cheapest in here</h3>
		
		<div class="row md-3">
		<c:forEach var="phone" items="${phoneList}">
		
	
			<div id="div" class="col-md-3">
						
				<c:url var="buyLink" value="phone">
					<c:param name="mode" value="LOAD"></c:param>
			 		<c:param name="goodsid" value="${phone.goodsid }"></c:param>
		 		</c:url>

					<img src="assets/imgs/phone/iphonex.jpg" id="img" width="100px" height="60px">
						<h5> ID :  <c:out value="${phone.goodsid}"></c:out> </h5>
						 <h5>Name : <c:out value="${phone.name}"></c:out> </h5> 
						 <h5>Price : <c:out value="${phone.price}"></c:out> Ks</h5>
						 <h5>Quantity : <c:out value="${phone.quantity}"></c:out> </h5>
						<a class="btn btn-primary" href="${buyLink}"> Buy </a>
			 </div>
							 
      </c:forEach>
      </div>
</body>

</html>

