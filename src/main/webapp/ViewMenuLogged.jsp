<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<div class="w3-bar">
	<a class="w3-bar-item w3-button" href="MainController"> Home </a> 
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetOwnTimeline" href=#> MyPosts </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedTweets" href=#> FriendsTweets </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetNotFollowedUsers" href=#> FindFriends </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href=#> FollowedFriends </a>
	
	<a class="menu w3-bar-item w3-button w3-hide-small w3-right" id="LogoutController" href=#> <i class="fa fa-sign-out"></i> </a>
	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
</div>

<div id="stack" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium">
	<a class="menu w3-bar-item w3-button" id="GetOwnTimeline" href=#> MyProfile </a>
	<a class="menu w3-bar-item w3-button" id="GetFollowedTweets" href=#> FriendsTweets </a>
	<a class="menu w3-bar-item w3-button" id="GetNotFollowedUsers" href=#> FindFriends </a>
	<a class="menu w3-bar-item w3-button" id="GetFollowedUsers" href=#> FollowedFriends </a>
	
	
	<a class="menu w3-bar-item w3-button" id="LogoutController" href=#> Logout </a>
</div>
