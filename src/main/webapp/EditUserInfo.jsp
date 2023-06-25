<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<body>

<c:if test="${userAccesed.error['user']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given user name already exists in our database or it's over 50 characters. </p>
</div>
</c:if>

<c:if test="${userAccesed.error['mail']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given mail already exists in our database. </p>
</div>
</c:if>


<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>Edit your profile</h4>
  <p><img src="${user.picture}" id="avatarImage" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <form class="form2" action="EditProfile" method="post" enctype="multipart/form-data">
    <p class="w3-left-align">
      <i class="fa fa-user fa-fw w3-margin-right"></i>
      <input type="text" name="name" value="${user.name}" required>
    </p>
    <p class="w3-left-align">
      <i class="fa fa-envelope fa-fw w3-margin-right"></i>
      <input type="email" name="mail" value="${user.mail}" required>
    </p>
    
    <p class="w3-left-align">
      <i class="fa fa-key fa-fw w3-margin-right"></i>
      <input id="pwd" type="password" name="pwd" required pattern="^(?=.*[A-Z])(?=.*[!@#$&*\-])(?=.{8,})\S+$">
    </p>
    
    <div class="w3-left-align" style="margin-left:43px">
	<label class="w3-text-black"><b>Sport interests:</b></label><br>
	<div class="w3-left-align" style="margin-bottom: 10px;">
		<input type="checkbox" id="futbol" name="sport_interests" value="futbol">
		<label for="futbol">Futbol</label><br>
		<input type="checkbox" id="mma" name="sport_interests" value="mma">
		<label for="mma">MMA</label><br>
		<input type="checkbox" id="basket" name="sport_interests" value="basket">
		<label for="basket">Baloncesto</label><br>
		<input type="checkbox" id="tenis" name="sport_interests" value="tenis">
		<label for="tenis">Tenis</label><br>
	</div>
	</div>
    
    
    <p class="w3-left-align">
      <i class="fa fa-image fa-fw w3-margin-right"></i>
      <input type="file" id="picture" name="picture" accept="image/*" onchange="previewImage(event)">
    </p>
    <c:if test="${user != null && user.id != -1}">
      <button type="submit" class="w3-row w3-button w3-green w3-section" value="Upload">
        <i class="fa fa-save"></i>&nbsp;Save Changes
      </button>
    </c:if>
  </form>
</div>
 </body>
<script>
  function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
      var image = document.getElementById('avatarImage');
      image.src = reader.result;
    }
    reader.readAsDataURL(event.target.files[0]);
  }
</script>
</html>