<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-black w3-padding w3-card" style="letter-spacing:4px;">
    <a class="w3-bar-item w3-button" href="MainController"> Home </a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetOwnTimeline" href=#> MyPosts </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedTweets" href=#> FriendsTweets </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetNotFollowedUsers" href=#> FindFriends </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href=#> FollowedUsers </a>
      <a class="menu w3-bar-item w3-button w3-hide-small w3-right" id="LogoutController" href=#> <i class="fa fa-sign-out"></i> </a>
	  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
    </div>
  </div>
</div>

<div id="stack" class="w3-bar w3-black w3-padding w3-card" style="letter-spacing:4px;">
	<a class="w3-bar-item w3-button" href="MainController"> Home </a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetOwnTimeline" href=#> MyPosts </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedTweets" href=#> FriendsTweets </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetNotFollowedUsers" href=#> FindFriends </a>
      <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href=#> FollowedUsers </a>
      <a class="menu w3-bar-item w3-button w3-hide-small w3-right" id="LogoutController" href=#> <i class="fa fa-sign-out"></i> </a>
	  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
	</div>
</div>
