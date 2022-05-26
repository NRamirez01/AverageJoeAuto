<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/splash.css">
		<title>All Posts</title>
	</head>
	<body>
	    <header>
	        <div>
	            <h1>Welcome <c:out value="${user.name}"/></h1>
	            <a href="/logout">Log out</a>
	        </div>
	        <div>
	            <p>Average Joe Auto Forum</p>
	            <a href="/addThread">Start a new Discussion</a>
	        </div>
	    </header>
	    <div>
         <table class="table table-bordered">
             <thead>
                 <tr>

                     <th scope="col">Discussion</th>
                     <th scope="col">Posted By</th>
                 </tr>
              
              </thead>
              <c:forEach var="thread" items="${threads}">
	              <tbody>
	                  <tr>
	                      <td><a href="/averageJoeAuto/forum/${thread.id}"><c:out value="${thread.title}"/></a>
							
	                      <td><a href="/averageJoeAuto/user/${thread.getUser().id}"><c:out value="${thread.getUser().name}"/></a>
	                      <c:if test ="${thread.getUser().id eq user.id}">
	                      <di class="flexbox">
	                    	  <a href="/edit/${thread.id}" class="options" >Edit thread</a>
	                          <form class="options" action="/delete/thread/${thread.id}" method="post">
	                              <input type="hidden" name="_method" value="delete">
	                              <input type="submit" value="Delete">
	                          </form>
	                          </div>
	                      </c:if>
	                      </td> 
	                  </tr>
	              </tbody>
              </c:forEach>
         </table>
     </div>
	</body>
</html>