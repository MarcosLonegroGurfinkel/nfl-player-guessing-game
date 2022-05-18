<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta content="998059926896-fm54274ast09d5uoj5idlebcsb50e1f7.apps.googleusercontent.com"
          name="google-signin-client_id" >
    <title>Login / Register - NFL Player Guessing Game</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script crossorigin="anonymous"
            src="https://kit.fontawesome.com/3204349982.js"></script>
    <script async defer src="https://apis.google.com/js/platform.js"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto"
          rel="stylesheet" type="text/css">
    <script src="https://apis.google.com/js/api:client.js"></script>
    
     <script
	    src="https://code.jquery.com/jquery-3.3.1.js"
	    integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	    crossorigin="anonymous">
	</script>
	<script> 
		$(function(){
		  $("#header").load("header.jsp"); 
		});
	</script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	
</head>
<body>
	

	<div id="auth-error"> ${error} </div>
	
	
	<div id="header"></div>
	
	
	<div class="auth-container">
		<div class="auth" id="auth-login">
			<form action="LoginDispatcher" method="GET">
				<h3>Login</h3>
				
				<div class="auth-form">
					<label for="login-email"> Email </label> <br>
					<input type="email" class="auth-input" name="login-email" required>
				</div>
				
				<div class="auth-form">
					<label for="login-password"> Password </label> <br>
					<input type="password" class="auth-input" name="login-password" required>
				</div>
				
				<button type="submit" class="auth-button" id="auth-sign-in"> <i class="fa-solid fa-arrow-right-to-bracket auth-button-icon"></i> Sign in </button>
				
			</form>			
		</div>
		
		<div class="auth" id="auth-register">
			<form action="RegisterDispatcher" method="POST">
				<h3> Register </h3>
	
				<div class="auth-form">
					<label for="register-email"> Email </label> <br>
					<input type="email" name="register-email" id="register-email" class="auth-input" required="required">
				</div>
				
				<div class="auth-form">
					<label for="register-name"> Username </label> <br>
					<input type="text" name="register-username" id="register-username" class="auth-input" required="required">
				</div>
				
				<div class="auth-form">
					<label for="register-password"> Password </label> <br>
					<input type="password" name="register-password" id="register-password" class="auth-input" required="required">
				</div>
				
				<div class="auth-form">
					<label for="register-confirm-password"> Confirm Password </label> <br>
					<input type="password" name="register-confirm-password" id="register-confirm-password" class="auth-input" required="required">
				</div>
				
				<div class="auth-form">
					<input type="radio" name="accept-terms" id="accept-terms" required="required">
					<label for="accept-terms"> I have read and accept all terms and conditions of NFL Player Guessing Game</label>
				</div>
				
				<button type="submit" class="auth-button" id="auth-create-account"> <i class="fa-solid fa-user-plus auth-button-icon"></i> Create Account </button>
				
			</form>
		</div>
	
	</div>

</body>
</html>