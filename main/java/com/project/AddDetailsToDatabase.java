package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AddDetailsToDatabase extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details?useSSL=false", "root", "Salman@2002");
            
            if(c != null) {
                System.out.println("Successfully connected to database");
            } else {
                System.out.println("Not connected");
            }

            Statement s = c.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30) NOT NULL, email VARCHAR(30) UNIQUE, gender VARCHAR(10) NOT NULL, DOB DATE NOT NULL, course VARCHAR(30) NOT NULL, phone VARCHAR(14) NOT NULL, address VARCHAR(100));");

            String studname = req.getParameter("name");
            String studemail = req.getParameter("email");
            String studgender = req.getParameter("gender");
            String studDOB = req.getParameter("DOB");
            String studcourse = req.getParameter("course");
            String studphone = req.getParameter("phone");
            String studaddress = req.getParameter("address");
            
            PreparedStatement ps = c.prepareStatement("INSERT INTO students(name,email,gender,dob,course,phone,address) VALUES(?,?,?,?,?,?,?);");
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
            
            // Correct redirection path (assuming servlet is mapped as /AddDetailsToDatabase)
//            res.sendRedirect("/AddDetailsToDatabase");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
