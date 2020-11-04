<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head><title>View Order</title></head>
	
	<style>
		.container {
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
		}
	</style>
	<body>
	<jsp:include page="Header.jsp" />
	<div class="container">
	
	<h2>This is your order details</h2>
	
	<h4>Order details</h4>
    <table>
	<c:forEach items="${order.getLineItems()}" var="item" varStatus="loop">
		<tr>
			<td><c:out value="${item.getName()}"></c:out></td>
			<td><c:out value="$ ${item.getPrice()}"></c:out></td> 
			<td><c:out value="${item.getQuantity()}"></c:out></td> 
		</tr>
	</c:forEach>
    </table>
    
    <h4>Payment Information</h4>
    <table>
    	<tr><td>CreditCardNumber: ${paymentInfo.creditCardNumber}</td></tr>
    	<tr><td>ExpirationDate: ${paymentInfo.expirationDate}</td></tr>
    	<tr><td>CvvCode: ${paymentInfo.cvvCode}</td></tr>
    	<tr><td>CardHolderName: ${paymentInfo.cardHolderName}</td></tr>
    </table>
    
    <h4>Shipping details</h4>
    
    <table>
    	<tr><td>Name: ${shippingInfo.name}</td></tr>
	    <tr><td>addressLine1: ${shippingInfo.addressLine1}</td></tr>
	    <tr><td>addressLine2: ${shippingInfo.addressLine2}</td></tr>
	    <tr><td>city: ${shippingInfo.city}</td></tr>
	    <tr><td>state: ${shippingInfo.state} </td></tr>
	    <tr><td>zip: ${shippingInfo.zip}</td></tr>
    </table>
 <form:form method="post" action="confirmOrder">
     <table>
     	<tr>
     		<td colspan="2"><input type="submit" value="Confirm"></td>
     	</tr>
     </table>
</form:form>

</div>
	<jsp:include page="Footer.jsp" />
	</body>
</html>
