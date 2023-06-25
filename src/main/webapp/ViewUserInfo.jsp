<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta id="id" name="id" content="${userAccesed.id}">
</head>

<c:if test="${error}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <p> Provided credentials are already in use! </p>
</div>
</c:if>


<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>Welcome to ${userAccesed.name} profile!</h4>
  <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <p class="w3-left-align">
		<i class="fa fa-user fa-fw w3-margin-right"></i> ${userAccesed.name} </p>
  <p class="w3-left-align">
		<i class="fa fa-envelope fa-fw w3-margin-right"></i> ${userAccesed.mail} </p>
  <c:if test="${access}">
  	<a id="${userAccesed.name}" type="button" class="editUser w3-row w3-button w3-green w3-section" href="">
  		<i class="fa fa-user-plus"></i> &nbsp;Edit
  	</a>
  	<c:if test="${userAccesed.name != user.name}">
  	<a id="delete_${userAccesed.name}" type="button" class="deleteUser w3-row w3-button w3-red w3-section" href="">
  		<i class="fa fa-trash"></i> &nbsp;Delete User
  	</a>
  	</c:if> 
  </c:if> 
 </div>
<br>

<div class="sub_div" style="position: relative;">
                <jsp:include page="ViewTweets.jsp" />
            </div>
<script>
	$(document).on("click",".editUser", function(event) {
		$("#content").load("UserEditAdmin", {name: $(this).attr("id")});
		// $("#lcolumn").load("GetNotFollowedUsers");
		event.preventDefault();
  	});
	
	$(document).on("click",".deleteUser", function(event) {
		$.post( "DeleteUser", { id: $('#id').prop("content")}, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
  	});
</script>
</html>