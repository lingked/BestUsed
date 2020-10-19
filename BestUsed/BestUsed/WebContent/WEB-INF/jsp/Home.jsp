<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<jsp:include page="Header.jsp" />
	<div class="slogen text-center">
		<h4>Our Vision & Mission:</h4>
		<p>Provide the best service for second-hand goods retailing</p>
		<p>Ensure the quality of each products</p>
		<p>Keep the price as cheap as possible</p>
		
		<h4>Products and Services offered</h4>
		<p>Comprehensive second-hand items</p>
		<p>Free deliver service if purchased more than 50$</p>
		<p>Free return within 30 days if there are quality issues</p>
	</div>
	
	<div class="img-gallary">
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/basketball.png" /></div>
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/chair.jpg" /></div>
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/desk.jpg" /></div>
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/iphone.jpg" /></div>
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/lamp.jpg" /></div>
		<div class="img-goods"><img class="img-goods-size" src="BestUsed/images/mac.jpg" /></div>
	</div>
	

<jsp:include page="Footer.jsp"/>
</body>
</html>