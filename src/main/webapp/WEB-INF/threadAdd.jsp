<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="/css/createThreadStyling.css">
		<title>Start a new Thread</title>
	</head>
	<body>
	<header>

            <h1>Create a Post</h1>
            <a href="/averageJoeAuto/forum">Back to the Forum</a>
</header>
	 <form:form action="/createThread" method="post" modelAttribute="thread">
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
	    <input type="submit" value="Submit"/>
     </form:form>
	</body>
</html>