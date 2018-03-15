<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
    <title>Available Menu</title>
</head>
<body>

<p>Available Menu</p>
<form:form action="/orders/captureOrder" modelAttribute="order" method="GET">
	<table>
	    <tr>
	    <th>Name</th>
	    <th>Price</th>
	    </tr>
	    <c:forEach items="${order.items}" var="orderItem" varStatus="idx">
	       <tr>
	           <td><c:out value="${orderItem.itemName}"/></td>
	           <td><c:out value="${orderItem.price}"/></td>
	       </tr>
	   </c:forEach>
	</table>
    <form:button name="submit" value="submit">Place an Order</form:button>
</form:form>
</body>
</html>