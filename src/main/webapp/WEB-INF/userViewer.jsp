<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="/css/viewShow.css">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/userViewer.css">
		<title>View Thread</title>
	</head>
	<body>
    	<header>
	        <div class="header">
	            <h1><c:out value="${user.name}"/>'s posts</h1>
	            <a href="/averageJoeAuto/forum">Back to the Forum</a>
	        </div>

	        		<c:forEach var="post" items="${posts}">
						<div>
							<p class="postedBy"><c:out value="${post.postContent}"/></p>
							<p class="content"><a href="/averageJoeAuto/forum/${post.thread.id}"><c:out value="${post.thread.title}"/></a></p>
								<c:if test = "${post.getUser().id eq user.id}">
	                          <form action="/delete/${post.getThread().id}/${post.id}" method="post">
	                              <input type="hidden" name="_method" value="delete">
	                              <input type="submit" value="Delete">
	                          </form>
	                      </c:if>
						</div>
					</c:forEach>
						<c:if test="${posts.isEmpty()}"><h3 class="noPost">This user hasn't made any posts yet!</h3></c:if>	
        </header>
	</body>
</html>