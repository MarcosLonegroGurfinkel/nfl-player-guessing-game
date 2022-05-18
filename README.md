NFL Wordle

IN PROGRESS: Multiplayer games

Brief Description:

The NFL Wordle Game is a player guessing game. You have eight guesses to get the correct player. After every guess, the game will tell you how the Conference, Division, Team, Position, Height, Weight and Age of the guessed player compares to that of the target player, and you can use that information to arrive at the correct answer. The functionalities you are able to access (such as individual and multiplayer games) depend on whether you are a guest or registered user.


Technical Specifications:

Server used: Tomcat 9
Database used: MYSQL database managed through MYSQL Workbench

database url, username and password set in Constant.java. Their values are "jdbc:mysql://localhost/PROJECT", "root", "root". To set up the database modify these values in the file to match your local machine's set up the run the script in project.sql, located inside the project root.


Jar files are included in WEB-INF/lib/ folder:

gson-2.9.0.jar to read json using gson
jstl-1.2.jar to use jsp standard library tags in various jsp files
mysql-connector-java.8.0.28.jar to connect java code to mysql database
