<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<style>
#logout-message {
    font-size: 24px; /* Increase font size */
}
</style>

<script type="text/javascript">
$(document).ready(function(){
    $('#navigation').load('MenuController');
    $('#lcolumn').html("<p></p>");
    $('#rcolumn').html("<p></p>");
});
</script>

<p id="logout-message">Logout done!</p>