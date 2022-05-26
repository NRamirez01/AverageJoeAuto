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
		<link rel="stylesheet" type="text/css" href="/css/thread.css">
		<title>View Thread</title>
	</head>
	<body>
    	<header>
	        <div class="header">
	            <h1><c:out value="${thread.title}"/></h1>
	            <a href="/averageJoeAuto/forum">Back to the Forum</a>
	        </div>
	        <table class="table table-borderless">
		        <thead>
			        <tr>
			        </tr>
		        </thead>
			        <tbody>
				        <tr>
				        	<th scope="row">Posted By: <c:out value="${thread.getUser().name}"/></th>
				        	<td>Discussion: <c:out value="${thread.content}" /></td>
				        </tr>
			        </tbody>
	        	</table>
		        <c:if test="${thread.getUser().id eq user}">
					<a href="/edit/${thread.id}">Edit thread</a>
				</c:if>
				
				<table class="table table-bordered">
				<thead>
				<tr>
					<th>Discussion</th>
				</tr>
				</thead>
				<tbody>
								<c:forEach var="posts" items="${posts}">
				<tr>

				<td scope="row">Posted by: <a class="postedBy" href="/averageJoeAuto/user/${posts.getUser().id}"><c:out value="${posts.getUser().name}"/></a></td>
				<td><c:out value="${posts.postContent}"/></td>
				<td>							<c:if test = "${posts.getUser().id eq user}">
						
	                          <form action="/delete/${posts.getThread().id}/${posts.id}" method="post">
	                              <input type="hidden" name="_method" value="delete">
	                              <input type="submit" value="Delete">
	                          </form>
	                          <a href="/edit/post/${posts.id}">Edit Post</a>
	                      </c:if></td>
				
				</tr>
				
								
								</c:forEach>
				</tbody>
				</table>


					
					

				<form:form action="/averageJoeAuto/forum/newPost" method="post" modelAttribute="post">
					<p>
					   <form:label path="postContent">Post a reply</form:label>
					   <form:input path="postContent"/>
					</p>
					<form:input type="hidden" path="user" value="${user}"/>
					<form:input type="hidden" path="thread" value="${thread.id}"/>
					<input type="submit" value="Submit"/>
					</form:form>
        </header>
	</body>
</html>