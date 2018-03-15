<div xmlns="http://www.springframework.org/tags" 
xmlns:jsp="http://java.sun.com/JSP/page"/>
<html xmlns="">
<head>
<meta charset="ISO-8859-1"/>
<title>Login</title>
</head>
<body>

<form id="login" method="POST" action="/login" enctype="application/x-www-form-urlencoded">
	<table>
		<tr><td>
			<i>username</i><input type="type" name="username"></input>
		</td></tr>
		<tr><td>
			<i>password</i><input type="password" name="password"></input>
		</td></tr>
		<tr><td>
			<input type="submit" value="submit"></input>
		</td></tr>
	</table>
	
	  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>
</body>
</html>
</div>