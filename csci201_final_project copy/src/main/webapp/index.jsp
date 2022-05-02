<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="csci201_final_project.util.ComparisonResult" %>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <title>NFL Player Guessing Game</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="index.css">
        
 
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
	
	
	<form action="SinglePlayerGameDispatcher" method="GET" >
		<div style="text-align: center;">
			<button type="submit">Start Game</button>
		</div>
	</form>

	

TODO: ADD INSTRUCTIONS

</body>
</html>