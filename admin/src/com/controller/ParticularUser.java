package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class ParticularUser extends HttpServlet{
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/livetrack";

	   static final String USER = "root";
	   static final String PASS = "aws$1234";
	   
	   
	public void init(ServletConfig config) {
        System.out.println("My servlet has been initialized");
    }
	private static final long serialVersionUID = 1L;
	 public ParticularUser() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	 Connection conn=null;
	 Statement stmt = null;
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			String detailes=request.getParameter("particular");
			
			
			System.out.print("Vehicle Number is......" + detailes);
			
			
		
			
			//String latitude = split[8];
			//String longitude = split[9];
			
			
		
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
				
			
				 String phonequery = "SELECT customerName,customerPhoneNo,customerEmailID ,customerLicenceNo,customerVehicleNo,customerVehicleType,customeriteneraryNo, customerType from customer WHERE customerVehicleNo = ?";
				 PreparedStatement ps=conn.prepareStatement(phonequery);
				 
		         ps.setString(1, detailes );
		         
		         ResultSet rs=ps.executeQuery();
		         while(rs.next()){
		        	 
		        	 String customerName = rs.getString("customerName");
		        	 String customerPhoneNo = rs.getString("customerPhoneNo");
		        	 String customerEmailID = rs.getString("customerEmailID");
		        	 String customerLicenceNo = rs.getString("customerLicenceNo");
		        	 String customerVehicleNo = rs.getString("customerVehicleNo");
		        	 String customerVehicleType = rs.getString("customerVehicleType");
		        	 String customeriteneraryNo = rs.getString("customeriteneraryNo");
		        	 Boolean customerType = rs.getBoolean("customerType");
		        	 
		        	
		        	
		        	 String success= "1" ;
		        	 
		        	 JSONObject jsonobj = new JSONObject();
		        	 jsonobj.put("customerName", customerName);
		        	 jsonobj.put("customerPhoneNo", customerPhoneNo);
		        	 jsonobj.put("customerEmailID", customerEmailID );
		        	 jsonobj.put("customerLicenceNo", customerLicenceNo );
		        	 jsonobj.put("customerVehicleNo", customerVehicleNo);
		        	 jsonobj.put("customerVehicleType", customerVehicleType);
		        	 jsonobj.put("customeriteneraryNo", customeriteneraryNo );
		        	 jsonobj.put("customerType", customerType );
		        	 
		        	 jsonobj.put("success from server", success);
		        	 
		        	 out.println(jsonobj);
		        	System.out.println(jsonobj);
		        	
		        	
		         }
		        
					 //System.out.println("Done");	
					 } catch (Exception e1) {  
						 
						 String e= "Error" ;
			        	 JSONObject jsonobj1 = new JSONObject();
			        	 try {
							jsonobj1.put("Error!!!", e);
						} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
							out.println(jsonobj1);
						
							
						 		
								 e1.printStackTrace();
							    throw new RuntimeException(e1);  
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
