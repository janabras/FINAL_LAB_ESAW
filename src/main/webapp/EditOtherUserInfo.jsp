<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta id="id" name="id" content="${userAccesed.id}">
</head>
<body>
<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>Edit ${userAccesed.name} profile</h4>
  
  <p><img src="${userAccesed.picture}" id="avatarImage" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <form id="editOther" action="EditOtherProfile" method="post" enctype="multipart/form-data">
    <p class="w3-left-align">
      <i class="fa fa-id-card fa-fw w3-margin-right"></i>
      <input id="name" type="text" name="name" value="${userAccesed.name}" required>
    </p>
    <p class="w3-left-align">
      <i class="fa fa-id-badge fa-fw w3-margin-right"></i>
      <input id="mail" type="email" name="mail" value="${userAccesed.mail}" required>
    </p>
    <p class="w3-left-align">
      <i class="fa fa-image fa-fw w3-margin-right"></i>
      <input type="file" id="picture" name="picture" accept="image/*" onchange="previewImage(event)">
    </p>
    <button type="submit" class="w3-row w3-button w3-green w3-section" value="Upload">
        <i class="fa fa-save"></i>&nbsp;Save Changes
      </button>
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