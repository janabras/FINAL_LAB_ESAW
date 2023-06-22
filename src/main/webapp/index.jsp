<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/upf.jpg">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 4 template </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">


<script type="text/javascript">
$(document).ready(function(){
	// $('#lcolumn').load('GetNotFollowedUsers');
	// $('#rcolumn').load('GetUserInfo');
	
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu",function(event) {
		$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	
	$(document).on("submit","#editOther", function(event) {
		$('#content').load("EditOtherProfile", {id: $('#id').prop("content"), name: $('#name').val(), mail: $('#mail').val()});
		event.preventDefault();
	});
	
	$(document).on("submit",".form2", function(event) {
		var form = this;
		var data = new FormData(form);
		$.ajax({
			type: 'POST',
			enctype: 'multipart/form-data',
			url: $(form).attr('action'),
			data: data,
			processData: false,
			contentType: false
		}).done(function(html){
			$("#content").html(html);
		});
		event.preventDefault();
	});
	
	
	$(document).on("submit",".form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
	    event.preventDefault();
	});
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		$.post( "AddTweet", { content: $("#tweetContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
		event.preventDefault();
	});
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		console.log($(this).parent().attr("id"))
		$.post( "DelTweet", { id: $(this).parent().attr("id") } , function(event) {
			//$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: $(this).parent().attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* Add Like */
    $(document).on("click", ".likeTweet", function (event) {
        var tweet = $(this).parent();
        $.post("AddTweetLike", {id: $(this).parent().attr("id")});
        event.preventDefault();
    });
	
	
    //Edit Tweet 
    $(document).on("click", ".saveEditTweet", function (event) {
    	var tweet = $(this).parent();
    	$.post( "EditTweet", { id: tweet.attr("id"), content: $("#content_" + tweet.attr("id")).text()}, function(event) {
    	});
		event.preventDefault();
    });
    
    $(document).on("click", ".profile", function (event) {
    	
    	$('#content').load("AccessProfile", {name: $(this).text()});
        event.preventDefault();
    });
    
	$(document).on("click", ".userInfo", function (event) {
    	$('#content').load("AccessProfile", {name: $(this).attr("id")});
        event.preventDefault();
    });
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-theme" id="navigation">
    	<jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
 	<!-- Begin Content -->
	<div class="w3-row-padding">
 	<!-- Left Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="rcolumn">
			<p></p>
		</div>
	</div>
	<!-- Middle Column -->
	<div class="e3-container w3-col m6">
  		<div id="content" style="margin-top: 30px;">
    		<h2 style="color: black; text-align: center; font-size: 62px; font-family: 'Your Custom Font', Verdana;">Sports Twitter</h2>
    		<div style="display: flex; justify-content: center; flex-direction: column; align-items: center;">
      			<img src="imgs/logo.png" alt="Sports Twitter Logo" style="width: 130px; height: 130px;margin-top: 30px; margin-bottom: 60px;">
    		</div>
    		<jsp:include page="${content}" />
  		</div>
	</div>
	
	<!-- Right Column -->
	<div class="w3-container w3-col m3 w3-hide-small" style="float:right;">
		<div id="lcolumn">
			<p></p>
		</div>
	</div>
	</div>
	<!-- End Content -->
	<!-- Footer -->
	<footer class="w3-container w3-black w3-padding-8">
  		<p style="font-style: italic; text-align: center; font-size: 17px;">"If you don't take the shot... you'll never know whether it would have gone in or not"</p>
  		<p style="font-style: italic; font-family: cursive; text-align: center; font-size: 15px;"> Mike Lupica</p>
	</footer>
	
	
	
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>