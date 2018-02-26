<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
    <title>Your Order</title>
</head>
<body>

<p>Dear ${order.user.userId}, you have placed the below Order</p>
<table border = 1>
    <tr>
    <th>Item Name</th>
    <th>Price</th>
    <th>Quantity</th>
    </tr>
    <c:forEach items="${order.items}" var="orderItem" varStatus="idx">
        <tr>
            <td><c:out value="${orderItem.item.itemName}"/>
            </td>
            <td><c:out value="${orderItem.price}"/>
            </td>
            <td><c:out value="${orderItem.quantity}"/>
            </td>
        </tr>
    </c:forEach>
        <tr><td>Order Total Value is: </td><td>${order.value} </td></tr>
</table>
</body>
</html>