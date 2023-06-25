<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${zone == 'OwnTimeline'}">
	<div class="w3-border w3-round-xlarge w3-border-red w3-margin-top w3-center">
	  <h3>People with same interests!</h3>
	</div>
</c:if>
<div class="w3-row-padding">
  <c:forEach var="u" items="${users}" varStatus="status">
    <div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-center w3-section w3-col m6">
      <p>Friend Suggestion</p>
      <img src="imgs/avatar6.png" alt="Avatar" style="width:50%"><br>
      <a class="profile w3-border w3-border-red w3-button">${u.name}</a>
      <button type="button" class="followUser w3-row w3-button w3-green w3-section"><i class="fa fa-user-plus"></i>&nbsp;Follow</button>
    </div>
    <c:if test="${status.index % 2 == 1 or status.last}">
      </div><div class="w3-row-padding">
    </c:if>
  </c:forEach>
</div>

<c:if test="${zone == 'OwnTimeline'}">
	<jsp:include page="ViewSuggestedEvents.jsp" />
</c:if>