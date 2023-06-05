<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>Edit your profile</h4>
  <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <form action="EditProfile" method="POST">
    <p class="w3-left-align">
      <i class="fa fa-id-card fa-fw w3-margin-right"></i>
      <input type="text" name="name" value="${user.name}" required>
    </p>
    <p class="w3-left-align">
      <i class="fa fa-id-badge fa-fw w3-margin-right"></i>
      <input type="email" name="mail" value="${user.mail}" required>
    </p>
    <c:if test="${user != null && user.id != -1}">
      <button type="submit" class="w3-row w3-button w3-green w3-section">
        <i class="fa fa-save"></i>&nbsp;Save Changes
      </button>
    </c:if>
  </form>
</div>
<br>