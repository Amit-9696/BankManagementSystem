<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Balance</title>
</head>
<style>
body{
	background-color:#ffd1b3;
	text-align: center;
	font-family:Bold;
 	font-size:40px;
}
</style>
<body>
	    <p>Your current bank balance is: <%= request.getAttribute("balance") %></p>
	
</body>
</html>