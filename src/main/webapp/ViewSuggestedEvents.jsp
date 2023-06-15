<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${zone == 'OwnTimeline'}">
	<div class="w3-border w3-round-xlarge w3-border-red w3-margin-top w3-center">
	  <h3>Suggested Events!</h3>
	</div>
</c:if>
<div class="w3-row-padding">
  
  <%--<c:forEach var="u" items="${users}" varStatus="status">
    <div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-center w3-section w3-col m6">
      <p>Friend Suggestion</p>
      <img src="imgs/avatar6.png" alt="Avatar" style="width:50%"><br>
      <div>${u.name}</div>
      <button type="button" class="followUser w3-row w3-button w3-green w3-section"><i class="fa fa-user-plus"></i>&nbsp;Follow</button>
    </div>
    <c:if test="${status.index % 2 == 1 or status.last}">
      </div><div class="w3-row-padding">
    </c:if>
  </c:forEach> --%>
  <div class="w3-card w3-margin">
  <img src="https://upload.wikimedia.org/wikipedia/commons/4/49/UFC_131_Carwin_vs._JDS.jpg" alt="Event Image" style="width:100%">
  <div class="w3-container">
    <h4><b>Upcoming Event</b></h4>
    <p>Date: November 15, 2023</p>
    <p>Location: City Hall</p>
    <p>Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    <a href="#" class="w3-button  w3-green w3-margin-bottom">Register</a>
  </div>
</div>

</div>
