<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
 });
 setTimeout(function() {
	  window.location.href = "MainController"; // Replace with your desired URL
	}, 2000); // 3000 milliseconds = 3 seconds
</script>

 <div id="content" style="margin-top: 30px;">
    <h2 style="color: black; text-align: center; font-size: 62px; font-family: 'Your Custom Font', Verdana;">Sports Twitter</h2>
    <h2 style="color: black; text-align: center; font-size: 62px; font-family: 'Your Custom Font', Verdana;">LOGGIN SUCCESFULLY!</h2>
    <h2 style="color: black; text-align: center; font-size: 62px; font-family: 'Your Custom Font', Verdana;">~ Redirecting ~</h2>
    	<div style="display: flex; justify-content: center; flex-direction: column; align-items: center;">
      		<img src="imgs/logo.png" alt="Sports Twitter Logo" style="width: 130px; height: 130px;margin-top: 30px; margin-bottom: 60px;">
    	</div>
</div>

	