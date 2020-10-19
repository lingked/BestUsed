<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Confirmation</title>
	</head>
	<jsp:include page="Header.jsp" />
	
	<h2>Confirmation of your order</h2>
	
	<p>Thank you for completing the order! Your confirmation code is: ${confirmationNum}</p>
	
	
	<jsp:include page="Footer.jsp" />
</html>