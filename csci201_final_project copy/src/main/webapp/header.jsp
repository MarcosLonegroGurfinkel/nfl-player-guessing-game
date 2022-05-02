<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">
	<meta content="998059926896-fm54274ast09d5uoj5idlebcsb50e1f7.apps.googleusercontent.com"
          name="google-signin-client_id" >
	<title>Home</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	

	<link rel="stylesheet" href="index.css">
	<script src="https://kit.fontawesome.com/1b1359a299.js" crossorigin="anonymous"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
    rel="stylesheet">
		
</head>

<body>

<header id="header">
	<nav class="navbar navbar-light bg-light">
		<div id="header-left">
			<a class="navbar-brand" href="index.jsp" id="header-brand"> NFL Player Guessing Game </a>
			
			<% 	
			
				Cookie[] cookies = request.getCookies();
				String userName = null;
				if (cookies != null){
					for (Cookie cookie : cookies){
						if (cookie.getName().compareTo("userName") == 0){
							userName = cookie.getValue();
						}
					}
				}
				if (userName != null){
					
			%>
				<span> Hello, <%= userName.replaceAll("_", " ")  %> </span>
			<%}
			%>
	
		</div>
		<div id="header-right">
	 		<a href="index.jsp" class="header-option"> Home </a>
	 		<% 	
				if (userName != null){
			%>
	 			<a href="IndividualStatsDispatcher" class="header-option"> Individual Stats </a>
	 			<a href="InvitationsDispatcher" class="header-option"> Invitations </a>
	 			<a href="LogoutDispatcher" class="header-option"> Logout </a>
	 			
			<%} else {
			%>
	 			<a href="auth.jsp" class="header-option" >Login/Register </a>
	 		
	 		<%} 
	 		%>
			
		</div>
	</nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>