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

import org.json.JSONArray;
import org.json.JSONObject;




public class Adminparticular extends HttpServlet {
	
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL ="jdbc:mysql://localhost:3306/livetrack";

	   static final String USER = "root";
	   static final String PASS = "aws$1234";
	   
	public void init(ServletConfig config) {
        System.out.println("My servlet has been initialized");
    }
	private static final long serialVersionUID = 1L;
	 public Adminparticular() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	 Connection conn=null;
	 Statement stmt = null;
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			String detailes=request.getParameter("adminparticular");
			
			System.out.println("Vehicle Number is......" + detailes);
			
			
		
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
			
				 String phonequery = "SELECT latitude,longitude,distance,timeduration,speed FROM location WHERE customerVehicleNo = ? ";
				 PreparedStatement ps=conn.prepareStatement(phonequery);
				 
		         ps.setString(1, detailes );
		         
		         ResultSet rSet=ps.executeQuery();
		         
		         JSONArray jarray = new JSONArray();
		         while(rSet.next())
		         {
		        	 Double latitude = rSet.getDouble("latitude");
		        	 Double longitude = rSet.getDouble("longitude");
		        	 Double distance = rSet.getDouble("distance");
		        	 Double timeduration = rSet.getDouble("timeduration");
		        	 Double speed = rSet.getDouble("speed");
		        	 
		        	 JSONObject jobj=new JSONObject();
		        	 jobj.put("latitude" , latitude);
		        	 jobj.put("longitude" , longitude);
		        	 jobj.put("distance" , distance);
		        	 jobj.put("timeduration" , timeduration);
		        	 jobj.put("speed" , speed);
		        	 jarray.put(jobj);	 
		         }
		         JSONObject jobj1= new JSONObject();
		         jobj1.put("values", jarray);
		         
		         out.println(jobj1);
		        
					 //System.out.println("Done");	
					 } catch (Exception e1) {  
						 
						 		
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
