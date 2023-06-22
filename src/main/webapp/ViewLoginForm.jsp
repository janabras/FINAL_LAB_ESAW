<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${error}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Identification error! </h3>
  <p> Provided credentials do not match our database. </p>
</div>
</c:if>

<form action="LoginController" class="form" method="POST">
	<p>      
    <label for="user" class="w3-text-black"><b>User name:</b></label><br>
    <input class="w3-input w3-border w3-light-grey" type="text" name="name" placeholder="Username" value="${user.name}" required autocomplete="username"><br>
    <label for="pwd1" class="w3-text-black"><b>Password:</b></label><br>
  	<input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd" placeholder="Password" value="${user.pwd}" required autocomplete="current-password"><br><br>
    <input class="w3-btn w3-black w3-text-white" type="submit" name="submit" value="Submit" style="margin-bottom: 10px;"></p>
</form>
