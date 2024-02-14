package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/withdraw")
public class withdrawBalance extends HttpServlet{
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
        ResultSet resultSet = null;
        
        String query = "SELECT account_balance FROM bank_data WHERE account_number = ?";

		String query1 = "UPDATE bank_data set account_balance = account_balance - ? WHERE account_number = ?";

    	try {
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, accountNumber);
            resultSet = pstmt.executeQuery();
            
            double currentBalance = 0; 
            if (resultSet.next()) {
                currentBalance = resultSet.getDouble("account_balance");
            }

            if (amount > 10000) {
                req.setAttribute("errorMessage", "Maximum withdrawal limit is 10,000");
                req.getRequestDispatcher("withdrawError.jsp").forward(req, resp);
                return;
            }

            if (amount > currentBalance) {
                req.setAttribute("errorMessage", "Insufficient balance!");
                req.getRequestDispatcher("withdrawError.jsp").forward(req, resp);
                return;
            }
            pstmt = con.prepareStatement(query1);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            
            req.setAttribute("successMessage", "Withdraw successful!");

    	} catch (SQLException e) {
    		e.printStackTrace();
          }
    	req.getRequestDispatcher("/WithdrawConfirmation.jsp").forward(req, resp);
	}
}
