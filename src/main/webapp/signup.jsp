<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ADEN STORE USER Form</title>
         <!-- CDN-->
         <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
         <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
         <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
         <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/store1.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/custom.css" rel="stylesheet" />
    </head>
    <body> 
	<div class="container">
    <form action="user" method="post" class="form-horizontal" role="form">
        <h2 class="text-center">User Registration</h2>
        
        <input type="hidden" name="mode" value="SIGNUP" />
        
        <div class="form-group">
            <label for="username" class="col-sm-3 control-label">User Name</label>
            <div class="col-sm-9">
                <input type="text" id="username" name="username" placeholder="UserName" class="form-control" autofocus>
            </div>
        </div>
        
       
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input type="email" id="email" name="email" placeholder="Email" class="form-control" autofocus>
            </div>
        </div>
        
        
        
        <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password </label>
            <div class="col-sm-9">
                <input type="password" id="password" name="password" placeholder="Password" class="form-control">
            </div>
        </div>
         
         <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block">*Required fields</span>
            </div>
        </div>
        
        <div class="mb-3">
        	
        	<label for="role" class="form-label">Role</label>
        	
        	<select name="role">
        	
        	 	<option value="admin">Admin</option>
        	 	<option value="user">User</option>
        	
        	
        	</select>
        
        </div>
        
        <button type="submit" class="btn btn-primary btn-block">Submit</button>
        <button type="submit" class="btn btn-danger btn-block">Reset</button>
        
    </form>
    
    <p>Already have an account? <a href="signin-form.html">Please Sign in</a> </p>
    
    
    
</div> <!-- ./container -->
		<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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


