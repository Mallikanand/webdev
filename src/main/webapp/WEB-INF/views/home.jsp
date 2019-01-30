<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
    <title>Available Menu</title>
</head>
<body>

<table>
<tr>
<td>
<p>Available Menu</p>
<form:form action="/orders/captureOrder" modelAttribute="order" method="POST">
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
</td>
<td align="right" >
<div>
<a href="/login">Sign In</a>
</div>
<div>
<a href="/signout">Sign Out</a>
</div>
</td>
</tr>
</table>
</body>
</html>