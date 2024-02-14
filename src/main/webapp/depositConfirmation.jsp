<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit Confirmation</title>
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

    <%-- Check if there is an error message and display it --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("errorMessage") %>
        </div>
    <% } %>
</body>
</html>