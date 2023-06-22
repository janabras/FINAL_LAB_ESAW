<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-black w3-padding w3-card" style="letter-spacing:4px;">
    <a class="w3-bar-item w3-button w3-text-white" id="LogoutController" href="MainController"> Home </a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a class="menu w3-bar-item w3-button w3-hide-small w3-text-white" id="RegisterController" href=#> Registration </a>
      <a class="menu w3-bar-item w3-button w3-hide-small w3-text-white" id="LoginController" href=#> Login </a> 
      <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
    </div>
  </div>
</div>

<div id="stack" class="w3-bar w3-black w3-padding w3-card" style="letter-spacing:4px;">
	<a class="w3-bar-item w3-button w3-text-white" id="LogoutController" href="MainController"> Home </a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a class="menu w3-bar-item w3-button w3-hide-small w3-text-white" id="RegisterController" href=#> Registration </a>
      <a class="menu w3-bar-item w3-button w3-hide-small w3-text-white" id="LoginController" href=#> Login </a> 
      <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
	</div>
</div>
