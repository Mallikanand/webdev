<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
    <title>Available Menu</title>
</head>
<body>

<p>Available Menu</p>
<table>
<tr>
<th>Name</th>
<th>Price</th>
</tr>
<c:forEach items="${menuItemsByMenuType.value}" var="menuItems">
<c:forEach items="${menuItems.value}" var="menuItem">
<tr>
<td><c:out value="${menuItem.itemName }"/></td>
<td><c:out value="${menuItem.price }"/></td>
</tr>
</c:forEach>
</c:forEach>
</table>

</body>
</html>