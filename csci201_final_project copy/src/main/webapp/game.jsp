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

	
    <form class="form-group row justify-content-center" action="SinglePlayerGameDispatcher" method="POST">
        <div class="submit" style="text-align: center;">
              <input type="text" id="guess-name" name="guess-name">
			  <button type="submit"> Guess </button>
        </div>
    </form>
	
	<div style="text-align: center; color: red;"> ${error} </div>
    

    <br>
    <div class="row text-center">
        <div class ="col-12">
        	<c:if test="${not empty guessNumber}">
    			<h5 id="id-counter-not-empty">Guessed ${guessNumber} of 8</h5>
			</c:if>
			<c:if test="${empty guessNumber}">
    			<h5 id="id-counter-empty">Guessed 0 of 8</h5>
			</c:if>
            
        </div>  
    </div>
    
    
    

    <div class="container" style="text-align: center; color: black;">
        <div class="row">
          <div class="back-row1 col-sm bordered">
            Name
          </div>
          <div class="back-row1 col-sm bordered">
            Conference
          </div>
          <div class="back-row1 col-sm bordered">
            Division
          </div>
          <div class="back-row1 col-sm  bordered">
            Team
          </div>
          <div class="back-row1 col-sm bordered">
            Position
          </div>
          <div class="back-row1 col-sm bordered">
            Height
          </div>
          <div class="back-row1 col-sm bordered">
            Weight
          </div>
          <div class="back-row1 col-sm bordered">
            Age
          </div>
       	</div>
         

        <% 
        List<Map<String, ComparisonResult> > results = (List<Map<String, ComparisonResult> >) request.getAttribute("results");
        if (results != null){
        	
        for (int i = 0; i < results.size(); i++){
        	Map<String, ComparisonResult> result = results.get(i);
      		request.setAttribute("result", result);
	        %>
	        
	        <div class="row">
	          <div class="back-row1 col-sm bordered" style="display: table;">
	          	<%	
		          	
	          		if (result.get("name").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell; font-weight: bold;"> 
	          					${result.get("name").getValue()}
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell; font-weight: bold;"> 
	          					${result.get("name").getValue()}
	          				</div>
	         			<%
	          		}
		          		
		          
	          	%>
	            
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table;">
	          	<%	
	          		if (result.get("conference").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("conference").getValue()}
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;""> 
	          					${result.get("conference").getValue()}
	          				</div>
	         			<%
	          		}
		          		
		          	
	          	%>
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table;">
	          <%
		         
	          		if (result.get("division").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("division").getValue()}
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("division").getValue()}
	          				</div>
	         			<%
	          		}
		          		
		          	
	            %>
	          </div>
	          <div class="back-row1 col-sm  bordered" style="display: table;">
	           <%
	          		if (result.get("team").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("team").getValue()}
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("team").getValue()}
	          				</div>
	         			<%
	          		}
		          		
		          	
	            %>
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table;">
	 			<%
	          		if (result.get("position").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("position").getValue()}
	          				</div>
	          			<%
	          		} else if (result.get("position").getResult().compareTo("yellow") == 0) {
	          			%>
	          				<div style="background-color: #d0d26c; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("position").getValue()}
	          				</div>
	          			<%
	          			
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("position").getValue()}
	          				</div>
	         			<%
	          		}
		          		
		          	
	            %>
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table;">
	 			<%
	          		if (result.get("height").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("height").getValue()}
	          				</div>
	          			<%
	          		} else if (result.get("height").getResult().compareTo("target_higher") == 0) {
	          			%>
	          				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("height").getValue()}
	          					<div> actual is higher </div>
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("height").getValue()}
	          					<div> actual is lower </div>
	          				</div>
	         			<%
	          		}
	          		
		          	
	            %>
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table; ">
	 			<%
	          		if (result.get("weight").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("weight").getValue()}
	          				</div>
	          			<%
	          		} else if (result.get("weight").getResult().compareTo("target_higher") == 0) {
	          			%>
	          				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("weight").getValue()}
	          					<div> actual is higher </div>
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("weight").getValue()}
	          					<div> actual is lower </div>
	          				</div>
	         			<%
	          		}
		          		
		          	
	            %>
	          </div>
	          <div class="back-row1 col-sm bordered" style="display: table;">
	 			<%
	          		if (result.get("age").getResult().compareTo("green") == 0){
	          			%>
	          				<div style="background-color: #6da564; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("age").getValue()}
	          				</div>
	          			<%
	          		} else if (result.get("age").getResult().compareTo("target_higher") == 0) {
	          			%>
	          				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("age").getValue()}
	          					<div> actual is higher </div>
	          				</div>
	          			<%
	          		} else {
	          			%>
	         				<div style="background-color: #c8d1db; height: 75px; vertical-align: middle; display: table-cell;"> 
	          					${result.get("age").getValue()}
	          					<div> actual is lower </div>
	          				</div>
	         			<%
	          		}
	          		
		          	
	            %>
	          </div>
	        </div>
	        <%
        }
        }
        %>
        
         
         
     </div>
     
     
    <% if (request.getAttribute("gameOver") != null){
    	if (request.getAttribute("gameOver") == "won"){
    		%>
    			<h1> You have won the game, press Start Game to start over </h1>
    		<%
    	} else {
    		%>
    		    <h1> You are out of guesses, the answer was ${targetAthleteName}, press Start Game to start over. </h1>
    		<%
    	}
    }
    %>
     

</body>
</html>