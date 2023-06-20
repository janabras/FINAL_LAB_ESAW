<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Following</h1>
<c:forEach var="u" items="${followingUsers}">       
 <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <h4> ${u.name} </h4><br>
   <hr class="w3-clear">
   <a id="${u.name}" type="button" class="userInfo w3-button w3-theme w3-margin-bottom"><i class="fa fa-info-circle"></i> &nbsp;Info</a>
   <button type="button" class="unfollowUser w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Unfollow</button> 
 </div>
</c:forEach>
<h1>Followers</h1>
<c:forEach var="u" items="${followersUsers}">       
 <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <h4> ${u.name} </h4><br>
   <hr class="w3-clear">
   <a id="${u.name}" type="button" class="userInfo w3-button w3-theme w3-margin-bottom"><i class="fa fa-info-circle"></i> &nbsp;Info</a> 
 </div>
</c:forEach>