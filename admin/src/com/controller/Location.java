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

public class Location extends HttpServlet{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/livetrack";

	   static final String USER = "root";
	   static final String PASS = "aws$1234";
	   
	public void init(ServletConfig config) {
        System.out.println("My servlet has been initialized");
    }
	
	private static final long serialVersionUID = 1L;
   
    public Location() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    Connection conn=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String detailes=request.getParameter("locdetailes");
		System.out.println("Location details....." + detailes );
		String [] split=detailes.split(",");
		
		String latitude = split[0];
		String longitude = split[1];
		String distance = split[2] ;
		String timeduration = split[3] ;
		String speed = split[4] ;
		String customerVehicleNo = split[5] ;
		
		
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(distance);
		System.out.println(timeduration);
		System.out.println(speed);
		System.out.println(customerVehicleNo);
		
		
		
		Double value1 = Double.parseDouble(latitude);
		Double value2 = Double.parseDouble(longitude);
		Double value3 = Double.parseDouble(distance);
		Double value4 = Double.parseDouble(timeduration);
		Double value5 = Double.parseDouble(speed);
		
	
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
			String insertpin="INSERT INTO LOCATION(latitude,longitude,distance,timeduration,speed,customerVehicleNo)VALUES(?,?,?,?,?,?)";
        	PreparedStatement ps=conn.prepareStatement(insertpin);
        	ps.setDouble(1, value1 );
        	ps.setDouble(2, value2);
        	ps.setDouble(3, value3);
        	ps.setDouble(4, value4);
        	ps.setDouble(5, value5);
        	ps.setString(6, customerVehicleNo );
        	
        	
        	
			int rowinsert = ps.executeUpdate();
			if(rowinsert>0){
				
				 
				 JSONObject jsonobj = new JSONObject();
	        	 
	        	 jsonobj.put("latitude" , value1);
	        	 jsonobj.put("longitude", value2 );
	        	 jsonobj.put("distance", value3);
	        	 jsonobj.put("timeduration", value4);
	        	 jsonobj.put("speed", value5);
	        	 jsonobj.put("customerVehicleNo",customerVehicleNo);
	        	
	        	 out.println(jsonobj);
				
				System.out.println("Values are inserted successfully..." + detailes);
			}
			
			else{
				
				
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
