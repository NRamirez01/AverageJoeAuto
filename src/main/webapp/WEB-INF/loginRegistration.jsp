<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Average Joe Auto</title>
		<link rel="stylesheet" type="text/css" href="/css/loginRegistration.css">
	</head>
	<body>
    	<h1>Average Joe Auto</h1>
    	<h2>For car people, by car people</h2>
    	<div class="column">
			<!-- <h3 class="flash">${message}</h3> -->
    		<div class="columns left">
    		<form:form action="/registerUser" method="post" modelAttribute="user"> <!-- Register -->
    				<form:errors path="name"/>
    	        	<form:errors path="email"/>
    		      	<form:errors path="password"/>
	            	<form:label path="name">Name</form:label>
	           		<form:input path="name"/>
	           		<form:label path="email">Email</form:label>
	            	<form:input path="email"/>
	            	<form:label path="password">Password</form:label>
	            	<form:input type="password" path="password"/>
	            	<form:label path="confirm">Confirm Password</form:label>
	            	<form:input type="password" path="confirm"/>
	            	<input type="submit" value="Register"/>
	        	</form:form>
        	</div>
        	<div class="columns right">
	        	<form:form action="/loginUser" method="post" modelAttribute="userLogin"><!-- Login -->
	            	<form:errors path="email"/>
	            	<form:label path="email">Email</form:label>
	            	<form:input path="email"/>
	            	<form:label path="password">Password</form:label>
	            	<form:input type="password" path="password"/>
	            	<input type="submit" value="Login"/>
	        	</form:form>
        	</div> 
    	</div>
	</body>
</html>