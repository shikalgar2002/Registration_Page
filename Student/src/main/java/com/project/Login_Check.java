package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login_Check extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String myemail=req.getParameter("email");
		String mypassword=req.getParameter("password");
		
		PrintWriter out=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details", "root", "Salman@2002");
			
			PreparedStatement ps=c.prepareStatement("Select * from students where email=?");
			ps.setString(1, myemail);
			
			ResultSet rs = ps.executeQuery("Select * from students");
			if(rs.next()) {
				if(mypassword.equals(myemail)) 
					out.println("<html><body>"+"<h1> LoginSuccessfully....."+"</body></html>");
				
				else 
					out.println("<html><body>"+"<h1>Login Failed....."+"</body></html>");
				
			}
			
			else {
				out.println("<html><body>"+"<h1>Invalid Username....."+"</body></html>");	
			}
			
		} catch (Exception e) {
			System.out.println("Exception is:"+e.getMessage());
		}
	
	}

}
