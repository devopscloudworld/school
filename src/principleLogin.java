import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/PrinLogin")
public class principleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement ps;
	ResultSet rs;
	Connection connection;
	
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		//System.out.println("username"+username);
		String pass=request.getParameter("pass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "vijay");
		            if(!connection.isClosed())
		                 System.out.println("Successfully connected");
		            ps=connection.prepareStatement("select username,pass from principal where username=? and pass=?");
					ps.setString(1,username);
					ps.setString(2,pass);
					
					
				//System.out.println("userid"+ rs.getString("user_id"));
					rs=ps.executeQuery();
					System.out.println("yoo");
					if(rs.next())
					{
						RequestDispatcher rd=request.getRequestDispatcher("P_dashboard.jsp"); 
						rd.forward(request, response);	
						//chance=0;
					}
					else
					{
					
						out.println("<script type=\"text/javascript\">");
						out.println("alert('user or password incorrect');");
						out.println("location='index.jsp");
						out.println("</script>");
					}
		          //  connection.close();
		        }catch(Exception ex){
		           System.out.println("Unable to connect to database"+ex);
		        }
		//PrintWriter out=response.getWriter();
		
//			try
//			{
			
			
			
	//}
//		catch(Exception e)
//		{
//			System.out.println("prepared Statement error");
//			
//		}
	}

}