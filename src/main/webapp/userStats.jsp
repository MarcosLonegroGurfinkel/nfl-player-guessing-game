<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Stats</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="index.css">
        <link rel="stylesheet" href="stats.css">
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
	<br>
	<br>
	<div class="text-center">
		<h3>Individual Stats</h3>
	</div>
	<br>
	<br>
	<div class="container">
        <div class="row">
          <div class="back-row1 col-sm bordered">
            Games Played
          </div>
          <div class="back-row1 col-sm bordered">
            Games Won
          </div>
          <div class="back-row1 col-sm bordered">
            Current Streak
          </div>
          <div class="back-row1 col-sm bordered">
            Max Streak
          </div>
    	</div>
    	 <div class="row">
           <div class="info2 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("gamesPlayed")}</h1>
					</div>				
				</div>
           </div>
           <div class="info2 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("gamesWon")}</h1>
					</div>				
				</div>
           </div>
           <div class="info2 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("currentStreak")}</h1>
					</div>				
				</div>
           </div>
           <div class="info2 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("maxStreak")}</h1>
					</div>				
				</div>
           </div>
        </div>
    </div>
    <br>
    <br>
    <br>
	<div class="container">
		<div class="text-center">
			<h3> Game Winning Guess - Distribution </h3>
		</div>
        <div class="row">
          <div class="back-row1 col-sm bordered">
            First
          </div>
          <div class="back-row1 col-sm bordered">
            Second
          </div>
          <div class="back-row1 col-sm bordered">
            Third
          </div>
          <div class="back-row1 col-sm bordered">
            Fourth
          </div>
        </div>
        <div class="row">
           <div class="info1 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInFirst")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInSecond")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInThird")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInFourth")}</h1>
					</div>				
				</div>
           </div>
        </div>
        <div class="row">
          <div class="back-row1 col-sm bordered">
            Fifth
          </div>
          <div class="back-row1 col-sm bordered">
            Sixth
          </div>
          <div class="back-row1 col-sm bordered">
            Seventh
          </div>
          <div class="back-row1 col-sm bordered">
            Eighth
          </div>
        </div>
        <div class="row">
           <div class="info1 col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInFifth")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInSixth")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInSeventh")}</h1>
					</div>				
				</div>
           </div>
           <div class="col-sm bordered">
				<div class="text-center">
					<div class="info1">
						<h1 class="testing">${stats.get("winsInEighth")}</h1>
					</div>				
				</div>
           </div>
        </div>
     </div>
</body>
</html>