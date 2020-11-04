<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Order</title>
	</head>
	<body>
		<jsp:include page="Header.jsp" />
		<h2>Please complete the order</h2>
		
		<form:form id="orderForm" modelAttribute="order" method="post" action="purchase/submitItems">
			
		    <table>
		    <tr>
		    	<th class="itemContainer">Name</th>
		    	
		    	<th class="itemContainer">Price</th>
		    	
		    	<th class="itemContainer" >Select</th>
		    	<th class="itemContainer">Quantity</th>
		    	<th class="itemContainer">Image</th>
		    </tr>
			<c:forEach items="${inventory.items}" var="item" varStatus="loop">
				<tr>
					<form:input type="hidden" path="lineItems[${loop.index}].itemNumber" value="${item.itemNumber}"/>
					<td class="text-center"><c:out value="${item.name}"></c:out></td>
					<form:input type="hidden" path="lineItems[${loop.index}].name" value="${item.name}"/>
					<td class="text-center"><c:out value="$${item.price}"></c:out></td>
					<form:input type="hidden" path="lineItems[${loop.index}].price" value="${item.price}"/>
					<td class="text-center"><input type="checkbox" id="checkboxEvent-${loop.index}"></td>
					<td class="text-center"><form:input id="${loop.index}-show" type="number" min="0" step="1" 
					pattern="[0-9]*"  onchange="checkQuantity(event)"
					path="lineItems[${loop.index}].quantity" readOnly="true"/>
					<p id='alert-${loop.index}' style="display: none">Input must be a positive integer</p>
					<p id='alert--${loop.index}' style="display: none"></p></td>
					<td class="text-center"><img class="image" alt="basketball" src="${item.imgUrl}" /></td>
 				</tr>
			</c:forEach>
		
			  <tr>
				<td colspan="2"><input type="button" value="Proceed to checkout" onClick="checkform()"></td>
			  </tr>
			
		
		    </table>
		</form:form>
		<jsp:include page="Footer.jsp" />
	</body>

</html>
