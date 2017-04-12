package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.FeedObjects;


public class Project {
	
	
	public ArrayList<FeedObjects> GetFeeds(Connection connection) throws Exception
	{
		ArrayList<FeedObjects> feedData = new ArrayList<FeedObjects>();
		try
		{
			//String uname = request.getParameter("uname");
			PreparedStatement ps = connection.prepareStatement("select * from customer ");
		
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				FeedObjects feedObject = new FeedObjects();
				feedObject.setCustomerEmailID(rs.getString("customerEmailID"));
				feedObject.setCustomeriteneraryNo(rs.getString("customeriteneraryNo"));
				feedObject.setCustomerLicenceNo(rs.getString("customerLicenceNo"));
				feedObject.setCustomerName(rs.getString("customerName"));
				feedObject.setCustomerPhoneNo(rs.getString("customerPhoneNo"));
				feedObject.setCustomerVehicleNo(rs.getString("customerVehicleNo"));
				feedObject.setCustomerType(rs.getBoolean("customerType"));
				feedObject.setCustomerVehicleType(rs.getString("customerVehicleType"));
				

				feedData.add(feedObject);
			}
			return feedData;
			
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
