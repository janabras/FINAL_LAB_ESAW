$( document ).ready(function() {
	
	
	
	//Sets dob max to curr date
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	
	if (dd < 10) {
	   dd = '0' + dd;
	}
	
	if (mm < 10) {
	   mm = '0' + mm;
	} 
	    
	today = yyyy + '-' + mm + '-' + dd;
	document.getElementById("dob").setAttribute("max", today);

	
	
	var pw2 = document.getElementById("pwd2");
	var pw1 = document.getElementById("pwd1");
	
	$("#pwd2").focusout(function() {
		if (pw2.value !== pw1.value) {
			document.getElementById("match").innerHTML = 'Passwords do not match!';
		}
		else {
			document.getElementById("match").innerHTML = '';
		}
	
	});
	$("#pwd2").keyup(function() {
		if (pw2.value == pw1.value) {
			document.getElementById("match").innerHTML = '';
		}
	
	});

});