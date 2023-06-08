<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/tweets.js"></script>

<c:forEach var="t" items="${tweets}"> 
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> <a class="menu w3-bar-item w3-button" href="?uname=${t.uname}">${t.uname}</a></h4><br>
   <hr class="w3-clear">
   <p> ${t.content} </p>
   <c:if test="${user != null}">
   	<button type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-heart"></i> &nbsp;Like</button>
   </c:if>
   <c:if test="${user == null}">
   	<button disabled type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-heart"></i> &nbsp;Like</button>
   </c:if>
   
   <%--<c:if test="${user != null}">
   	<button type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-retweet"></i> &nbsp;Repost</button>
   </c:if>
   <c:if test="${user == null}">
   	<button disabled type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-retweet"></i> &nbsp;Repost</button>
   </c:if>--%>
   
   
   <c:if test="${user != null && user.equals(t.uname)}">
   	<button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button> 
   </c:if>
 </div>
</c:forEach>
