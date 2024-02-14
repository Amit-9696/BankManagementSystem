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
@WebServlet("/addaccount")
public class AddBankAccount extends HttpServlet{
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
	String gender=req.getParameter("gender");
	String mail=req.getParameter("emailId");
	String marital=req.getParameter("maritalStatus");
	String addr=req.getParameter("address");
	String city=req.getParameter("city");
	int pincode=Integer.parseInt(req.getParameter("pin"));
	String state=req.getParameter("state");
	double amm=Double.parseDouble(req.getParameter("deposit"));
	
	PreparedStatement pstmt=null;
	
	String query="insert into bank_data values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	try {
		pstmt=con.prepareStatement(query);
		pstmt.setInt(1, 0);
		pstmt.setInt(2,accountNumber);
		pstmt.setString(3, name);
		pstmt.setString(4, Fname);
		pstmt.setString(5, BirthDate);
		pstmt.setString(6, gender);
		pstmt.setString(7, mail);
		pstmt.setString(8, marital);
		pstmt.setString(9, addr);
		pstmt.setString(10, city);
		pstmt.setInt(11,pincode);
		pstmt.setString(12, state);
		pstmt.setDouble(13, amm);
		int count=pstmt.executeUpdate();
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+"RECORD INSERTED SUCCESSFULLY </h1>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}
