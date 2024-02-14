<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Confirmation</title>
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
		 <%-- Check if there is a success message and display it --%>
    <% if (request.getAttribute("successMessage") != null) { %>
        <div style="color: green;">
            <%= request.getAttribute("successMessage") %>
        </div>
    <% } %>

</body>
</html>