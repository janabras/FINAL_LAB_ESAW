$(document).ready(function() {

	function getDate() {
		const now = new Date();

		const year = now.getFullYear();
		const month = String(now.getMonth() + 1).padStart(2, '0');
		const day = String(now.getDate()).padStart(2, '0');
		const hours = String(now.getHours()).padStart(2, '0');
		const minutes = String(now.getMinutes()).padStart(2, '0');
		const seconds = String(now.getSeconds()).padStart(2, '0');

		return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.0`;
	}

	$('.likeTweet').click(function() {
		//tweet likes
		var id_tweet = $(this).parent().attr("id");
		var likes = $("#likes_" + id_tweet).text();
		if ($("#button_" + id_tweet).hasClass('w3-light-grey')) {
			$("#button_" + id_tweet).removeClass('w3-light-grey w3-hover-red').addClass('w3-theme w3-hover-light-grey');
			$("#likes_" + id_tweet).text(parseInt(likes) + 1);
		} else {
			$("#button_" + id_tweet).removeClass('w3-theme w3-hover-light-grey').addClass('w3-light-grey w3-hover-red');
			$("#likes_" + id_tweet).text(parseInt(likes) - 1);
		}
	});

	$('.editTweet').click(function() {
		//tweet edit, front change icons, add class for index handling and change content to input/text
		var id_tweet = $(this).parent().attr("id");
		if ($("#edit_" + id_tweet).hasClass('fa-pencil')) {
			$(this).html("<i id='edit_" + id_tweet + "' class='fa fa-check'></i> &nbsp;Save");
			$(this).removeClass("saveEditTweet");
			//Change p text to input
			var contentText = $("#content_" + id_tweet).text();
			$("#content_div_" + id_tweet).empty().append('<p id="content_' + id_tweet + '" contenteditable="true" class="w3-border w3-margin-bottom">' + contentText + '</p>')

		} else {
			$("#date_" + id_tweet).text(getDate());
			$(this).html("<i id='edit_" + id_tweet + "' class='fa fa-pencil'></i> &nbsp;Edit");
			//Change input to p text
			$(this).addClass("saveEditTweet");
			var contentText = $("#content_" + id_tweet).text();
			$("#content_div_" + id_tweet).empty().append('<p id="content_' + id_tweet + '" class="w3-margin-bottom">' + contentText + '</p>')
		}

	});

});