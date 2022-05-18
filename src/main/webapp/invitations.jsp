<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invitations</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="index.css">
        <link rel="stylesheet" href=
    "https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css%22%3E">
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
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
		<link rel="stylesheet" href=
    "https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	
	<div id="header"></div>
	<hr>
	
	<div class="text-center">
		<h3>Invitations</h3>
	</div>

	<c:forEach items="${invitations}" var="invitation">
		<div style="text-align: center; margin: 50px 0px;">
			<h5> Invitation from: ${invitation} </h5>
			<h6> <a href="ConnectionDispatcher" style="color: green;"> Accept Invitation and Connect to Game </a>  </h6>
		</div>
	</c:forEach>
	
	<div class="text-center">
	<h4> Invite User to Game </h4>
	<form action="InvitationsDispatcher" method="POST">
		<input type="text" name="invitation-name" id="invitation-name">
		<p style="color: red;"> ${error} </p>
		<button type="submit"> Invite </button> 
	</form>
	</div>

</body>
</html>