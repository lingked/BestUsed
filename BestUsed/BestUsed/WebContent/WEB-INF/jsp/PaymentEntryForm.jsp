<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Payment</title>
	</head>
	
	<body>
	
	<jsp:include page="Header.jsp" />
	<h2>Please enter payment information</h2>
	
	<form:form id="payInfoForm" modelAttribute="paymentInfo" method="post" action="submitPayment">
		<table>
			<tr>
				<td>Credit Card Number: <form:input path="creditCardNumber" required /></td>
				<td>Expiration Date: <form:input path="expirationDate" required /></td>
				<td>Cvv Code: <form:input path="cvvCode" required /></td>
				<td>Card Holder Name: <form:input path="cardHolderName" required /></td>
			</tr>
	
		  <tr>
		   	<p id="payAlert" style="display: none">Please fill in all information</p>
			<td colspan="2"><input type="button" value="Finish Payment" onClick="checkPaymentInfo()"></td>
		  </tr>
		
	
	    </table>
	</form:form>
	<jsp:include page="Footer.jsp" />
	</body>
</html>
