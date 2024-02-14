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
@WebServlet("/displaybank")
public class DisplayBankDetail extends HttpServlet{
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
		String accountNumber = req.getParameter("accountNumber");
		String message = "";
		
		PreparedStatement pstmt=null;
		ResultSet resultSet = null;
		
        String query = "SELECT id,account_number,name,father_name,birth_date,gender,email,marital_status,address,city,pin_code,state,account_balance FROM bank_data WHERE account_number = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, accountNumber);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
            	req.setAttribute("id", resultSet.getInt("id"));
            	req.setAttribute("account_number", resultSet.getInt("account_number"));
                req.setAttribute("name", resultSet.getString("name"));
                req.setAttribute("father_name", resultSet.getString("father_name"));
                req.setAttribute("birth_date", resultSet.getString("birth_date"));
                req.setAttribute("gender", resultSet.getString("gender"));
                req.setAttribute("email", resultSet.getString("email"));
                req.setAttribute("marital_status", resultSet.getString("marital_status"));
                req.setAttribute("address", resultSet.getString("address"));
                req.setAttribute("city", resultSet.getString("city"));
            	req.setAttribute("pin_code", resultSet.getInt("pin_code"));
                req.setAttribute("state", resultSet.getString("state"));
                req.setAttribute("account_balance", resultSet.getDouble("account_balance"));
            } else {
                message = "No bank details found for account number: " + accountNumber;
            }
		} catch (SQLException e) {
			e.printStackTrace();
            message = "An error occurred while fetching bank details.";
		}

	        req.getRequestDispatcher("bankDetails.jsp").forward(req, resp);
		
	}
}
