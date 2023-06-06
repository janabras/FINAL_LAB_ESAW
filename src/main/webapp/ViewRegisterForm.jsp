<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
	window.jQuery || document.write('<script src="js2/vendor/jquery-1.11.2.js"><\/script>')
</script>
<script src="js/form.js"></script>
<form action="RegisterController" method="POST">
	<p>      
		<label class="w3-text-red"><b> Full Name:</b></label><br>
		<input class="w3-input w3-border w3-light-grey" type="text" name="name" value="${model.name}" required pattern="^.{1,50}$"><br>
	</p>
	<p>      
		<label class="w3-text-red"><b> Username:</b></label><br>
		<input class="w3-input w3-border w3-light-grey" type="text" name="username" value="${model.username}" required pattern="^.{1,50}$"><br>
	</p>
	<p>      
		<label class="w3-text-red"><b> Email:</b></label>
		<input class="w3-input w3-border w3-light-grey" type="email" name="email" value="${model.email}" required>
	</p>
	<p>      
		<label class="w3-text-red"><b> Date of birth:</b></label>
		<input class="w3-input w3-border w3-light-grey" type="date" name="dob" id="dob" value="${model.dob}" required>
	</p>
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
	<p>      
			<label class="w3-text-red"><b> Password:</b></label>
		<input class="w3-input w3-border w3-light-grey" type="password" name="pwd1" id="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[A-Z])(?=.*[!@#$&*-])(?=.{8,})\S+$">
	</p>
	<p>      
			<label class="w3-text-red"><b> Confirm Password:</b></label>
		<input class="w3-input w3-border w3-light-grey" type="password" name="pwd2" id="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required pattern="^(?=.*[A-Z])(?=.*[!@#$&*-])(?=.{8,})\S+$">
	</p>
	<label id="match" class="password-match-error"></label>
	<p>
		<input class="w3-btn w3-red" type="submit" value="Submit">
	</p>
</form>
<ul class="w3-ul w3-card w3-red">
	<c:if test="${model.error[0]}">
		<li>Username already in use.</li>
	</c:if>
	<c:if test="${model.error[1]}">
		<li>The username has to be less than 50 characters.</li>
	</c:if>
	<c:if test="${model.error[2]}">
		<li>The name has to be less than 50 characters.</li>
	</c:if>
	<c:if test="${model.error[3]}">
		<li>Email already in use.</li>
	</c:if>
	<c:if test="${model.error[4]}">
		<li>The password has to be .</li>
	</c:if>
	<c:if test="${model.error[5]}">
		<li>The password must have between 8 and 24 characters, have a number, an uppercase and a lowercase letter.</li>
	</c:if>
	<c:if test="${model.error[6]}">
		<li>Passwords do not match.</li>
	</c:if>
	<c:if test="${model.error[7]}">
		<li>You have to be at least 13 years old to register.</li>
	</c:if>
</ul>
