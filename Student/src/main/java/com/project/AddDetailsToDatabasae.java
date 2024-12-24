package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddDetailsToDatabasae extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details","root","Salman@2002");
			
			Statement s = c.createStatement();
			s.execute("create table IF NOT EXISTS students(id int primary key auto_increment,name varchar(30) not null,email varchar(30) unique,gender varchar(10) not null,DOB date not null,course varchar(30)not null,phone varchar(14) not null,address varchar(100))");
			String studname=req.getParameter("Uname");
			String studemail=req.getParameter("email");
			String studgender=req.getParameter("gender");
			String studDOB=req.getParameter("DOB");
			String studcourse=req.getParameter("Course");
			String studphone=req.getParameter("phone");
			String studaddress=req.getParameter("address");
			
			PreparedStatement ps =c.prepareStatement("insert into students(name,email,gender,dob,course,phone,address)values(?,?,?,?,?,?,?)");
			ps.setString(1, studname);
			ps.setString(2, studemail);
			ps.setString(3, studgender);
			ps.setString(4, studDOB);
			ps.setString(5, studcourse);
			ps.setString(6, studphone);
			ps.setString(7, studaddress);
			
			int count = ps.executeUpdate();
			if (count > 0) {
				PrintWriter p = res.getWriter();
				p.println("Your details are saved successfully....");
			}
			System.out.println("Inside ");
			
			RequestDispatcher rd= req.getRequestDispatcher("/login.html");
			rd.forward(req, res);
		} catch (Exception e) {
		System.out.println("Exception is"+ e.getMessage());
		}
	
		System.out.println("calling");
	}
}

