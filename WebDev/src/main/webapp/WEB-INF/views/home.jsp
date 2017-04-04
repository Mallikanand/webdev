<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
    <title>Available Menu</title>
</head>
<body>

<p>Available Menu</p>
<form:form action="/orders/captureOrder" modelAttribute="order" method="POST">
<table>
    <tr>
    <th>Name</th>
    <th>Price</th>
    <th>Add to Basket</th>
    </tr>
    <c:forEach items="${order.items}" var="orderItem" varStatus="idx">
        <tr>
            <td><c:out value="${orderItem.itemName}"/>
                <form:hidden path="items[${idx.index}].itemId"/>
                <form:hidden path="items[${idx.index}].price"/>
            </td>
            <td><c:out value="${orderItem.price}"/>
            </td>
            <td><form:input id="" path="items[${idx.index}].quantity"/></td>
        </tr>
    </c:forEach>
</table>
    <form:button name="submit" value="submit">Press to Submit</form:button>
</form:form>
</body>
</html>