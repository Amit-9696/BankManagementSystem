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
@WebServlet("/deposit")
public class DepositBalance extends HttpServlet{
	Connection con=null;
	
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
		String accountNumber = req.getParameter("accountNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        
        PreparedStatement pstmt=null;
    	
		String query1 = "UPDATE bank_data set account_balance = account_balance + ? WHERE account_number = ?";

    	try {
            pstmt = con.prepareStatement(query1);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            
            req.setAttribute("successMessage", "Deposit successful!");

    	} catch (SQLException e) {
    		e.printStackTrace();
    		req.setAttribute("errorMessage", "Deposit failed. Please try again later.");
    	
          }
    	req.getRequestDispatcher("/depositConfirmation.jsp").forward(req, resp);
	}

}
