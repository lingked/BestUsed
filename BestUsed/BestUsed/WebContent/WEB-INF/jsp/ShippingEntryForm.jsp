<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

	<head><title>Shipping</title></head>

	<body>
	<jsp:include page="Header.jsp" />
	<h2>Please enter shipping information</h2>

	<form:form id="shipInfoForm" modelAttribute="shippingInfo" method="post" action="submitShipping">
		<table>
			<tr>
				<td>Name: <form:input path="name" /></td>
				<td>AddressLine1: <form:input path="addressLine1" /></td>
				<td>AddressLine2: <form:input path="addressLine2" /></td>
				<td>City: <form:input path="city" /></td>
			</tr>
			<tr>
				<td>State: <form:input path="state" /></td>
				<td>Country: <form:input path="country" /></td>
				<td>Zip: <form:input path="zip" /></td>
				<td>Email: <form:input path="email" /></td>
			</tr>
	
		  <tr>
		  	<p id="shipAlert" style="display: none">Please fill in all information except for Add. 2</p>
			<td colspan="2"><input type="button" value="Submit shippingInfo" onClick="checkShippingInfo()"></td>
		  </tr>
	    </table>
	</form:form>
	
	<jsp:include page="Footer.jsp" />
	</body>

</html>