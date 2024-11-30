package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class login extends HttpServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//		to get the details 
		String Email = req.getParameter("email");
		String password = req.getParameter("password");

		PrintWriter out = res.getWriter();
//		check whether username is there or not in database  
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdetails", "root", "root");
			PreparedStatement ps = con.prepareStatement("Select * from student where email=?");
			ps.setString(1, Email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// User name exists check for password
				if (password.equals(Email))
					out.println("<html><body>" + "<h1>Login Successfully.....</h1>" + "</body></html>");
				else
					out.println("<html><body>" + "<h1>Faild to Login!!!</h1>" + "</body></html>");
			}
			else
				out.println("<html><body>" + "<h1>Invalid Username</h1>" + "</body></html>");
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}