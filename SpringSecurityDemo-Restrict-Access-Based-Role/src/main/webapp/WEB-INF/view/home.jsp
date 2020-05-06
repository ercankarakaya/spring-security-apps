<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>home</title>
</head>
<body>

	<!-- Display user name and role -->
	<p>
		User:
		<security:authentication property="principal.username" />
		<br /> <br /> Role(s):
		<security:authentication property="principal.authorities" />
	</p>

	Ana sayfa

	<!-- Add a link to point to /leaders..this is for the managers -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting (for Manager)</a>
		</p>
	</security:authorize>
	<!-- Add a link to point to /systems..this is for the admin -->
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems
				Meeting (for Admin)</a>
		</p>
	</security:authorize>

	<f:form action="logout" method="POST">

		<input type="submit" value="Logout" />

	</f:form>

</body>
</html>