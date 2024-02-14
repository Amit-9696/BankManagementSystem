<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.ResultSet" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Details</title>
</head>
<style>
body{
	background-color:#ffd1b3;
	text-align: center;
	font-family:Bold;
 	font-size:18px;
}
</style>
<body>
	 <h1>Bank Details</h1>
	 <% 
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
        } else {
            int id = (int) request.getAttribute("id");
            int account_number = (int) request.getAttribute("account_number");
            String name = (String) request.getAttribute("name");
            String father_name = (String) request.getAttribute("father_name");
            String birth_date = (String) request.getAttribute("birth_date");
            String gender = (String) request.getAttribute("gender");
            String email = (String) request.getAttribute("email");
            String marital_status = (String) request.getAttribute("marital_status");
            String address = (String) request.getAttribute("address");
            String city = (String) request.getAttribute("city");
            int pin_code = (int) request.getAttribute("pin_code");
            String state = (String) request.getAttribute("state");
            double balance = (double) request.getAttribute("account_balance");
    %>
    <table border="1">
    	<thead>
            <tr>
                <th>ID</th>
                <th>Account Number</th>
                <th>Account Holder Name</th>
                <th>Father Name</th>
                <th>Birth Date</th>
                <th>Gender</th>
                <th>Mail</th>
                <th>Marital Status</th>
                <th>Address</th>
                <th>City</th>
                <th>PinCode</th>
                <th>State</th>
                <th>Account Balance</th>
            </tr>
        </thead>
        <tbody>
        
             <tr>
                	<td><%= id %></td>
                	<td><%= account_number %></td>
                	<td><%= name %></td>
                	<td><%=father_name %></td>
                	<td><%= birth_date%></td>
                	<td><%= gender %></td>
                	<td><%= email %></td>
                	<td><%= marital_status %></td>
                	<td><%= address %></td>
                	<td><%= city %></td>
                	<td><%= pin_code %></td>
                	<td><%= state %></td>
                    <td><%= balance %></td>
                   
                </tr>
        </tbody>
    </table>
      <%
        }
    %>
</body>
</html>