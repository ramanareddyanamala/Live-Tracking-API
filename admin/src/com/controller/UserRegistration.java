package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class UserRegistration extends HttpServlet  {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/livetrack";

	   static final String USER = "root";
	   static final String PASS = "aws$1234";
	   
	
	public void init(ServletConfig config) {
        System.out.println("My servlet has been initialized");
    }
	
	private static final long serialVersionUID = 1L;
   
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    Connection conn=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String detailes=request.getParameter("regdetailes");
		System.out.print("Registration details....." + detailes);
		String [] split=detailes.split(",");
		
		String customerName = split[0];
		String customerPhoneNo = split[1];
		String customerEmailID = split[2];
		String customerLicenceNo = split[3];
		String customerVehicleNo = split[4] ;
		String customerVehicleType = split[5];
		String customerIteneraryNo = split[6];
		String customerType = split[7];
		
		
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			String insertpin="INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,?)";
        	PreparedStatement ps=conn.prepareStatement(insertpin);
        	ps.setString(1, customerName );
        	ps.setString(2, customerPhoneNo);
        	ps.setString(3, customerEmailID );
        	ps.setString(4,  customerLicenceNo);
        	ps.setString(5, customerVehicleNo );
        	ps.setString(6, customerVehicleType );
        	ps.setString(7, customerIteneraryNo);
        	ps.setString(8, customerType);
        	
			int rowinsert = ps.executeUpdate();
			if(rowinsert>0){
				
				 String success= "1" ;
				 JSONObject jsonobj = new JSONObject();
	        	 jsonobj.put("customerName:", customerName);
	        	 jsonobj.put("customerPhoneNo:", customerPhoneNo);
	        	 jsonobj.put("customerEmailID:", customerEmailID );
	        	 jsonobj.put("customerLicenceNo:", customerLicenceNo );
	        	 jsonobj.put("customerVehicleNo:", customerVehicleNo);
	        	 jsonobj.put("customerVehicleType:", customerVehicleType);
	        	 jsonobj.put("customeriteneraryNo:", customerIteneraryNo );
	        	 jsonobj.put("Success response from server: ", success);
	        	 
	        	 out.println(jsonobj);
				
				}
			
			else{
				String e= "Error" ;
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("InValid!!!", e);
				out.println(jsonobj);
				
				System.out.println("Values are not inserted....");
			}
			
						 //System.out.println("Done");	
				 } catch (Exception e) {  
							 e.printStackTrace();
						    throw new RuntimeException(e);  
						 }

            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        
		
		}
	
			
	}


