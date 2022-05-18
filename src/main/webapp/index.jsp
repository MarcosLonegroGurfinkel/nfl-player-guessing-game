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

    <br>
    <br>
    <br>
    <br>
    <br>
        <div class = "text-center">
             <h5>You have 8 guesses to guess the correct NFL player.
                Press Start to began. Good luck!
            </h5>
        </div>
    <br>
    <form action="SinglePlayerGameDispatcher" method="GET" >
        <div style="text-align: center;">
            <button type="submit" class="btn btn-primary btn-lg round" >Start Game</button>
        </div>
    </form>

    <br>
    <br>
        <div class = "text-center">
             <h5>Instructions</h5>
             <p>1. Press Start to start the game. </p>
             <p>2. Guess the player. </p>
             <p>3. If you have guessed the category correctly, then the box will turn green.</p>
             <p>4. For the age, height, and weight category, it will tell you if the actual is higher or lower. </p>
            <br>
            <br>
            <h6>Multi-player: You can invite any User to play the game with you. </h6>
        </div>

</body>
</html>