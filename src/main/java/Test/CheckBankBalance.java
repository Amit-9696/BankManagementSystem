package Test;

import java.io.IOException;
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

@WebServlet("/check")
public class CheckBankBalance extends HttpServlet{
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountNumber = req.getParameter("accountNumber");
        
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        double balance = 0;
    	
		String query1 = "SELECT account_balance FROM bank_data WHERE account_number = ?";

    	try {
            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, accountNumber);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                balance = resultSet.getDouble("account_balance");
            }
    	} catch (SQLException e) {
    		e.printStackTrace();    	
          }
    	req.setAttribute("balance", balance);
        req.getRequestDispatcher("Checkbalance.jsp").forward(req, resp);
	}
	
}
