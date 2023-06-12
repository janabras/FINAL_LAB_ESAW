$('.likeTweet').click(function () {
	//id tweet icon
        var id_like = $(this).attr("id");
        //id tweet
        var id_tweet = $(this).parent().attr("id");
        //value likes tweet
        var valueLike = $("#numlike_" + id_tweet).text();
        // alert(parseInt(valueLike) + 1);
        //if exist class
        if ($("#" + id_like).hasClass('far')) {

            $("#" + id_like).removeClass('far').addClass('fas');
            $("#" + id_like).css("color", "red");
            //add 1
            $("#numlike_" + id_tweet).text(parseInt(valueLike) + 1);
        } else {

            $("#" + id_like).removeClass('fas').addClass('far');
            $("#" + id_like).css("color", "grey");

            //del 1
            $("#numlike_" + id_tweet).text(parseInt(valueLike) - 1);
        }
	

	
});