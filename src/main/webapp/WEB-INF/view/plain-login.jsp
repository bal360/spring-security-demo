<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<title>User Login Page</title>
	</head>
	<body>
		<form:form action="{pageContext.request.contextPath}/authenticateTheUser" method="POST">
		</form:form>
	</body>
</html>