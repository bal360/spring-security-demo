<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Company Home Page</title>
	</head>
	<body>
		<h2>Company Home Page</h2>
		<p>All logged in, bud</p>
		
		<hr>
		<!-- display username and role -->
		<p>
			User: <security:authentication property="principal.username" />
			<br>
			Role(s): <security:authentication property="principal.authorities" />
		</p>
		<hr>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
		
	</body>
</html>