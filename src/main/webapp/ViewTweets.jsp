<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/tweets.js?3"></script>
<c:if test="${tweets.size() == 0}">
<h2 style="color: black; text-align: center; font-size: 62px; font-family: 'Your Custom Font', Verdana;">Start Following People or Write Tweets!</h2>
</c:if>
<c:forEach var="t" items="${tweets}">
	<div id="${t.id}"
		class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity">
		<br> <img src="imgs/avatar2.png" alt="Avatar"
			class="w3-left w3-circle w3-margin-right" style="width: 60px">
		<span id="date_${t.id}" class="w3-right w3-opacity">${t.postDateTime}</span>
		<h4>
			<a class="profile w3-bar-item w3-button" href="#">${t.uname}</a>
		</h4>
		<br>
		<div id="content_div_${t.id}">
			<p id="content_${t.id}" class="w3-margin-bottom">${t.content}</p>
		</div>

		<c:if test="${user != null}">
			<c:if test="${!t.isLiked}">
				<button id="button_${t.id}" type="button"
					class="likeTweet w3-button w3-light-grey w3-hover-red w3-margin-bottom">
					<i class="fa fa-heart"></i> &nbsp;Like
				</button>
			</c:if>
			<c:if test="${t.isLiked}">
				<button id="button_${t.id}" type="button"
					class="likeTweet w3-button w3-theme w3-hover-light-grey w3-margin-bottom">
					<i class="fa fa-heart"></i> &nbsp;Like
				</button>
			</c:if>
		</c:if>

		<c:if test="${user == null}">
			<button disabled type="button"
				class="likeTweet w3-button w3-theme w3-margin-bottom">
				<i class="fa fa-heart"></i> &nbsp;Like
			</button>
		</c:if>

		<c:if
			test="${user != null && fn:toLowerCase(user.name) == fn:toLowerCase(t.uname) || user.isAdmin}">
			<button type="button"
				class="delTweet w3-button w3-red w3-margin-bottom">
				<i class="fa fa-trash"></i> &nbsp;Delete
			</button>
			<button type="button"
				class="editTweet w3-button w3-red w3-margin-bottom">
				<i id="edit_${t.id}" class="fa fa-pencil"></i> &nbsp;Edit
			</button>
		</c:if>

		<div class="w3-right w3-badge w3-red w3-margin-top">
			<i class="fa fa-thumbs-up"></i><a id="likes_${t.id}">${t.likes}</a>
		</div>
		<c:if test="${user != null}">
			<!-- Add comment form -->
			<h5>Write a comment!</h5>
			<input type="text" name="tid" value="${t.id}" style="display: none;">
			<input id="add_comment_${t.id}"
				class="w3-input w3-border w3-light-grey w3-margin-bottom"
				type="text" name="content" placeholder="Add a comment...">
			<button class="w3-button w3-red w3-margin-bottom addComment"
				type="submit">Submit</button>
		</c:if>

		<!-- Comment section -->
		<h3>Comment section</h3>
		<div class="w3-container comment-section w3-margin-bottom">
			<c:set var="commentCount" value="0" />
			<c:forEach var="c" items="${comments}">
				<c:if test="${c.tid == t.id}">
					<c:if test="${commentCount < 5}">
						<div class="w3-card-4 w3-margin-bottom">
							<header class="w3-container w3-light-grey">
								<h3 id="${c.id}" class="${c.id}">
									<a class="profile w3-button">${c.username}</a>

									<c:if
										test="${user != null && (fn:toLowerCase(user.name) == fn:toLowerCase(c.username) || user.isAdmin)}">
										<button type="button"
											class="delComment w3-button w3-red w3-margin-bottom w3-right">
											<i id="delete_comment_${c.id}" class="fa fa-trash"></i>
											&nbsp;Delete
										</button>
									</c:if>



									<c:if test="${user != null}">
										<c:if test="${!c.isLiked}">
											<button id="button_comment_${c.id}" type="button"
												class="likeComment w3-button w3-light-grey w3-hover-red w3-margin-bottom w3-right"
												style="margin-right: 10px;">
												<i class="fa fa-heart"></i> &nbsp;Like
											</button>
										</c:if>
										<c:if test="${c.isLiked}">
											<button id="button_comment_${c.id}" type="button"
												class="likeComment w3-button w3-red w3-hover-light-grey w3-margin-bottom w3-right"
												style="margin-right: 10px;">
												<i class="fa fa-heart"></i> &nbsp;Like
											</button>
										</c:if>
									</c:if>

									<c:if test="${user == null}">
										<button disabled type="button"
											class="likeComment w3-button w3-red w3-margin-bottom w3-right"
											style="margin-right: 10px;">
											<i class="fa fa-heart"></i> &nbsp;Like
										</button>
									</c:if>

									<div class="w3-right w3-badge w3-red w3-margin-top" style="margin-right: 10px;">
										<i class="fa fa-thumbs-up "></i><a id="likesc_${c.id}">${c.likes}</a>
									</div>


								</h3>
							</header>
							<div class="w3-container">
								<p>${c.content}</p>
								<hr>
								<p>${c.postDateTime}</p>
							</div>
						</div>
						<c:set var="commentCount" value="${commentCount + 1}" />
					</c:if>
				</c:if>
			</c:forEach>
		</div>


	</div>
</c:forEach>
