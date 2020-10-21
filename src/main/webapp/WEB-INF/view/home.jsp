<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Company Home Page</title>
	</head>
	<body>
		<h2>Company Home Page</h2>
		<p>All logged in, bud</p>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
		
	</body>
</html>