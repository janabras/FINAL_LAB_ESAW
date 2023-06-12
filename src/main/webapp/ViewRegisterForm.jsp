<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test = "${user.error['user']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given user name already exist on our database or it's over 50 characters. </p>
</div>
</c:if>

<c:if test = "${user.error['mail']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given mail already exist on our database. </p>
</div>
</c:if>

<c:if test = "${user.error['age']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> The age is incorrect! </h3>
  <p> You have to be at least 13 years old. </p>
</div>
</c:if>

<form action="RegisterController" id="regform" method="POST">
  <p>
  <label for="user" class="w3-text-theme"> User name:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="text" id="name" name="name" placeholder="Username" value="${user.name}" required autocomplete="username"><br>
  <label for="mail" class="w3-text-theme"> Mail  </label><br>
  <input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" placeholder="Mail" value="${user.mail}" required autocomplete="email"><br>
  <label for="user" class="w3-text-theme"> Date of Birth:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="date" name="dob" id="dob" value="${user.dob}" required autocomplete="dob"><br>
  <p>      
	<label class="w3-text-red"><b> Sport interests:</b></label><br>
	<input type="checkbox" id="futbol" name="sport_interests" value="futbol">
	<label for="futbol">Futbol</label><br>
	<input type="checkbox" id="mma" name="sport_interests" value="mma">
	<label for="mma">MMA</label><br>
	<input type="checkbox" id="basket" name="sport_interests" value="basket">
	<label for="basket">Baloncesto</label><br>
	<input type="checkbox" id="tenis" name="sport_interests" value="tenis">
	<label for="tenis">Tenis</label><br>
  </p>
  
  <label for="pwd1" class="w3-text-theme"> Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd" placeholder="Password" value="${user.pwd}" required pattern="^(?=.*[A-Z])(?=.*[!@#$&*\-])(?=.{8,})\S+$" autocomplete="new-password"><br>
  <label for="pwd2" class="w3-text-theme"> Confirm Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd2" placeholder="Confirm Password" value="${user.pwd}" required autocomplete="new-password"><br><br>
  <input class="w3-btn w3-theme" type="submit" name="sumbit" value="Submit">
</form>

<script>
var regform = document.getElementById("regform");
var email = document.getElementById("mail");
var pwd1 = document.getElementById("pwd1");
var pwd2 = document.getElementById("pwd2");

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
}
pwd2.addEventListener("input", checkPasswordValidity, false);

regform.addEventListener("submit", function (event) {
	checkPasswordValidity();
	if (!this.checkValidity()) {
		this.reportValidity();
		event.preventDefault();
	} 
}, false);
</script>