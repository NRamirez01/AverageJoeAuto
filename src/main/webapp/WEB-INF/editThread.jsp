<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="/css/changesThread.css">
		<title>Edit your post</title>
	</head>
	<body>
    	<header>
        <div>
            <h1>Edit your Post</h1>
            <a href="/averageJoeAuto/forum">Back to the Forum</a>
        </div>
     </header>
	 <form:form action="/edit/submit/{thread.id}" method="post" modelAttribute="thread">
	 	<input type="hidden" name="_method" value="put"/>
	 	<p><form:errors path="title"/> <br>
	 	<form:errors path="content"/> <br>
	 	<p>
	        <form:label path="title">Title</form:label>
	        <form:input path="title"/>
	    </p>
	    <p>
		    <form:label path="content">Post Content</form:label>
			<form:input path="content"/>
        </p>
	    <form:input type="hidden" path="user" value="${user}"/>
	    <form:input type="hidden" path="id" value="${thread.id}"/>
	    <input type="submit" value="Submit"/>
     </form:form>
	</body>
</html>