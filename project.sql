/*
 * This sql file will contain the code needed to set up the database
*/
DROP DATABASE IF EXISTS `final_project`;
CREATE SCHEMA `final_project` ;
CREATE TABLE `final_project`.Athletes (
  athleteId INT NOT NULL auto_increment PRIMARY KEY,
  fullName VARCHAR(50),
  conference VARCHAR(50),
  division VARCHAR(50),
  team VARCHAR(50),
  position VARCHAR(50),
  height VARCHAR(50),
  weight INT,
  age INT
);
CREATE TABLE `final_project`.User (
userId INT auto_increment PRIMARY KEY,
email VARCHAR(50),
username VARCHAR(50),
encryptedPassword VARCHAR(50)
);
CREATE TABLE `final_project`.UserInvitations (
userInvitationId INT PRIMARY KEY AUTO_INCREMENT,
targetUserId INT,
receivingUserId INT,
status VARCHAR(50),
FOREIGN KEY (targetUserId) REFERENCES User(userId),
FOREIGN KEY (receivingUserId) REFERENCES User(userId)
);
CREATE TABLE `final_project`.UserVsUserStats (
battleId INT PRIMARY KEY AUTO_INCREMENT,
user1Id INT,
user2Id INT,
user1Wins INT,
user2Wins INT,
gamesPlayed INT,
FOREIGN KEY (user1Id) REFERENCES User(userId),
FOREIGN KEY (user2Id) REFERENCES User(userId)
);
CREATE TABLE `final_project`.UserStats (
userStatsId INT PRIMARY KEY AUTO_INCREMENT,
userId INT,
gamesPlayed INT,
gamesWon INT,
currentStreak INT,
maxStreak INT,
winsIn1st INT,
winsIn2nd INT,
winsIn3rd INT,
winsIn4th INT,
winsIn5th INT,
winsIn6th INT,
FOREIGN KEY (userId) REFERENCES User(userId)
);
