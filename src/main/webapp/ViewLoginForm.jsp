<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="LoginController" method="POST">
	<p>      
		<label class="w3-text-red"><b> Username </b></label>
		<input class="w3-input w3-border w3-light-grey" type="text" name="username" value="${login.username}" required >
	</p>
	<p>      
		<label class="w3-text-red"><b> Password </b></label>
		<input class="w3-input w3-border w3-light-grey" type="password" value="${login.pwd}" name="pwd" required>
	</p>
	<p>
		<input class="w3-btn w3-red" type="submit" name="submit" value="Submit">
	</p>
</form>
<ul class="w3-ul w3-card w3-red">
	<c:if test="${login.error[0]}">
		<li>Username is not registered.</li>
	</c:if>
	<c:if test="${login.error[1]}">
		<li>Incorrect password.</li>
	</c:if>
</ul>