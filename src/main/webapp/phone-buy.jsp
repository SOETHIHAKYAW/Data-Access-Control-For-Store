<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ADEN STORE</title>
         <link rel="icon" type="image/x-icon" href="assets/imgs/store.ico" />
         <!-- CDN-->
         <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
         <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
         <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
         <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/custom.css" rel="stylesheet" />
    </head>
    <body> 
<div class="container">
    <form action="phone" method="post" class="form-horizontal" role="form">
        <h2 class="text-center">Order Detail</h2>
        
        <h3>ID : ${phone.goodsid } </h3>
<%--         <h3>Goods ID : ${laptop.goodsid}</h3> --%>
<%--         <h3>Name : ${laptop.name}</h3> --%>
<%--         <h3>Price : ${laptop.price}</h3> --%>
        
        <input type="hidden" name="mode" value="BUY" />
         <input type="hidden" name="goodsid" value="${phone.goodsid}" />
        
        <div class="md-3">
            <label for="goodsid" class="form-label">Goods ID</label>
                 <input type="text" value="${phone.goodsid}" id="goodsid" name="goodsid" readonly="readonly" placeholder="Goods ID" class="form-control" autofocus>
            
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <div class="col-sm-9">
                <input type="text" value="${phone.name}" id="name" name="name" readonly="readonly" placeholder="FullName" class="form-control" autofocus>
            </div>
        </div>
        
        <div class="mb-3">
                <label for="price" class="form-label">Price </label>
            <div class="col-sm-9">
                <input type="text" value="${phone.price}" id="price" name="price" readonly="readonly" placeholder="Price" class="form-control">
            </div>
        </div>
        <div class="mb-3">
                <label for="quantity" class="form-label">Phone Quantity in Store : ${phone.quantity} </label>
            <div class="col-sm-9">
                <input type="number" value="" id="quantity" name="quantity" min="1" max="${phone.quantity }" placeholder="Enter Quantity that wants to buy" class="form-control">
            </div>
        </div>
        
        <button type="submit" id="buy" class="btn btn-primary btn-block">Buy</button>
        <button type="submit" class="btn btn-danger btn-block">Reset</button>
        
    </form> <!-- /form -->
</div> <!-- ./container -->

		<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Use JQuary to show -->
        <script> 
         $(document).ready(function(){
        	
            $("#buy").click(function(){ 
               
                 alert("Thank You So Much for Your Participation."); 
                
             }); 
         });
       </script>
</body>
</html>
