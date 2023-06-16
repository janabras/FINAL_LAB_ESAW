<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>

	$('.likeTweet').click(function () {
	//tweet likes
	var id_tweet = $(this).parent().attr("id");
	var likes = $("#likes_" + id_tweet).text();
	if($("#button_" + id_tweet).hasClass('w3-light-grey')){
		$("#button_" + id_tweet).removeClass('w3-light-grey w3-hover-red').addClass('w3-theme w3-hover-light-grey');
		$("#likes_" + id_tweet).text(parseInt(likes) + 1);
	} else {
		$("#button_" + id_tweet).removeClass('w3-theme w3-hover-light-grey').addClass('w3-light-grey w3-hover-red');
		$("#likes_" + id_tweet).text(parseInt(likes) - 1);
	}
	});
	
	$('.editTweet').click(function () {
		//tweet edit, front change icons, add class for index handling and change content to input/text
		var id_tweet = $(this).parent().attr("id");
		if($("#edit_" + id_tweet).hasClass('fa-pencil')){
			$(this).html("<i id='edit_"+id_tweet+"' class='fa fa-check'></i> &nbsp;Save");
			$(this).removeClass("saveEditTweet");
			//Change p text to input
			var contentText = $("#content_"+id_tweet).text();
			$("#content_div_"+id_tweet).empty().append('<p id="content_' + id_tweet + '" contenteditable="true" class="w3-border w3-margin-bottom">' + contentText + '</p>')
		
		} else {
			$(this).html("<i id='edit_"+id_tweet+"' class='fa fa-pencil'></i> &nbsp;Edit");
			//Change input to p text
			$(this).addClass("saveEditTweet");
			var contentText = $("#content_"+id_tweet).text();
			$("#content_div_"+id_tweet).empty().append('<p id="content_' + id_tweet + '" class="w3-margin-bottom">' + contentText + '</p>')
		}
		
	});
</script>
<c:if test="${isHome != null}">
	<h2 class="w3-border w3-round-xlarge w3-center w3-border-red">Sports Twitter</h2>
</c:if>
<c:forEach var="t" items="${tweets}"> 
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span id="date_${t.id}" class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> <a class="menu w3-bar-item w3-button" href="?uname=${t.uname}">${t.uname}</a></h4><br>
   <div id="content_div_${t.id}">
   <p id="content_${t.id}" class="w3-margin-bottom">${t.content}</p>
   </div>
   <c:if test="${user != null}">
	   <c:if test="${!t.isLiked}">
	   	<button id="button_${t.id}" type="button" class="likeTweet w3-button w3-light-grey w3-hover-red w3-margin-bottom"><i class="fa fa-heart"></i> &nbsp;Like</button>
	   </c:if>
	   <c:if test="${t.isLiked}">
	   	<button id="button_${t.id}" type="button" class="likeTweet w3-button w3-theme w3-hover-light-grey w3-margin-bottom"><i class="fa fa-heart"></i> &nbsp;Like</button>
	   </c:if>
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
   	<button type="button" class="editTweet w3-button w3-red w3-margin-bottom"><i id="edit_${t.id}" class="fa fa-pencil"></i> &nbsp;Edit</button> 
   </c:if>
   <div class="w3-right w3-badge w3-red w3-margin-top"><i class="fa fa-thumbs-up"></i><a id="likes_${t.id}">${t.likes}</a></div>
 </div>
</c:forEach>
<c:if test="${isHome != null}">
	<div class="w3-container w3-leftbar w3-sand w3-margin-bottom">
	  <p><i>"If you don't take a shot... you'll never know whether it would have gone in or not"</i></p>
	  <h4>~ Mike Lupica</h4>
	</div>
</c:if>