<div xmlns="http://www.springframework.org/tags" 
xmlns:jsp="http://java.sun.com/JSP/page"/>
<html xmlns="">
<head>
<meta charset="ISO-8859-1"/>
<title>Login</title>
</head>
<body>

<form id="logout" method="POST" action="/logout" enctype="application/x-www-form-urlencoded">
	<table>
		<tr><td>
			<input type="submit" value="Sign Out"></input>
		</td></tr>
	</table>
	
	  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>
</body>
</html>
</div>