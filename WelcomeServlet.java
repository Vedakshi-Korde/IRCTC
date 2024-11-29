

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/IRCTC?useSSL=false","root","Root@123");
			PreparedStatement stat=con.prepareStatement("insert into account values(?,?,?,?)");
			stat.setString(1, request.getParameter("t1"));
			stat.setString(2, request.getParameter("t2"));
			stat.setString(3, request.getParameter("t3"));
			stat.setString(4, request.getParameter("t4"));
			stat.executeUpdate();
			out.println("<h1>Congratulations, your user profile is now complete.</h1>");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		out.println("</body></html>");
		out.close();
	}
}


