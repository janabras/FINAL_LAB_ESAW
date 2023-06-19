<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${zone == 'OwnTimeline'}">
	<div class="w3-border w3-round-xlarge w3-border-red w3-margin-top w3-center">
	  <h3>Suggested Events!</h3>
	</div>
</c:if>
<div class="w3-row-padding">
  
  <c:forEach var="e" items="${events}" varStatus="status">
    <div class="w3-card w3-margin">
	  <img src="${e.image}" alt="Event Image" style="width:100%">
	  <div class="w3-container">
	    <h4><b>${e.eventName}</b></h4>
	    <p>Date: ${e.eventDate}</p>
	    <p>Location: ${e.location}</p>
	    <p>Description: ${e.description}</p>
	  </div>
	</div>
  </c:forEach>
  

</div>
