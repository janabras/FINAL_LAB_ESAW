<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>Welcome to ${userAccesed.name} profile!</h4>
  <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <p class="w3-left-align"> <i class="fa fa-id-card fa-fw w3-margin-right"></i> ${userAccesed.name} </p>
  <p class="w3-left-align"> <i class="fa fa-id-badge fa-fw w3-margin-right"></i> ${userAccesed.mail} </p>
  <c:if test="${access}">
  	<a id="${userAccesed.name}" type="button" class="editUser w3-row w3-button w3-green w3-section" href="">
  		<i class="fa fa-user-plus"></i> &nbsp;Edit
  	</a>
  </c:if> 
 </div>
<br>
<script>
	$(document).on("click",".editUser", function(event) {
		$("#content").load("UserEditAdmin", {name: $(this).attr("id")});
		// $("#lcolumn").load("GetNotFollowedUsers");
		event.preventDefault();
  	});
</script>