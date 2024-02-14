package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updateaccount")
public class UpdateBankAccount extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=sql123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int accountNumber=Integer.parseInt(req.getParameter("accountNumber"));
	String name=req.getParameter("name");
	String Fname=req.getParameter("Fname");
	String BirthDate=req.getParameter("birthDate");
	String mail=req.getParameter("emailId");
	String marital=req.getParameter("maritalStatus");
	String addr=req.getParameter("address");
	String city=req.getParameter("city");
	int pincode=Integer.parseInt(req.getParameter("pin"));
	String state=req.getParameter("state");
	
	PreparedStatement pstmt=null;
	
	String query="update bank_data set name=?,father_name=?,birth_date=?,email=?,marital_status=?,address=?,city=?,pin_code=?,state=? where account_number=?";
	
	try {
		pstmt=con.prepareStatement(query);
		pstmt.setInt(10,accountNumber);
		pstmt.setString(1, name);
		pstmt.setString(2, Fname);
		pstmt.setString(3, BirthDate);
		pstmt.setString(4, mail);
		pstmt.setString(5, marital);
		pstmt.setString(6, addr);
		pstmt.setString(7, city);
		pstmt.setInt(8,pincode);
		pstmt.setString(9, state);
		int count=pstmt.executeUpdate();
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+"RECORD INSERTED SUCCESSFULLY </h1>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}
