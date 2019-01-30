<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Available Menu</title>
<link href="/css/custom.css" rel="stylesheet"></link>
</head>
<body>

	<div id="total">

		<div id="availableMenu">
			<p>
				<u>Available Menu</u>
			</p>
			<form:form action="/orders/saveOrder" modelAttribute="order" method="POST" >
				<table>
					<tr>
						<th>Name</th>
						<th>Price</th>
						<th>Add to Basket</th>
					</tr>
					<c:forEach items="${order.items}" var="orderItem" varStatus="idx">
						<tr>
							<td><c:out value="${orderItem.itemName}" /> <form:hidden
									path="items[${idx.index}].itemName" /> <form:hidden
									path="items[${idx.index}].itemId" /> <form:hidden
									path="items[${idx.index}].price" /></td>
							<td><c:out value="${orderItem.price}" /></td>
							<td><form:input id="itemQuantity" path="items[${idx.index}].quantity" onChange="validateAndRefreshOrderSummary();"/></td>
						</tr>
					</c:forEach>
				</table>
				<form:button name="submit" value="submit">Press to Submit</form:button>
			</form:form>
		</div>
		<div id="orderSummary" align="left">
			<p>
				<u>Your Order Summary</u>
				<table id="orderSummaryTable" border="0px">
				</table>
			</p>
		</div>
	</div>
	
	<script type="text/javascript">
		function validateAndRefreshOrderSummary(){
			validateEntry();
			var orderSummaryTable = document.getElementById('orderSummaryTable');
			orderSummaryTable.setAttribute("border", "2px");
		}
		function validateEntry(){
			var itemQuantity = document.getElementBy
		}
	</script>
</body>
</html>