package com.Info.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Info.services.Input;

public class UpdateOperation {
	private static final UpdateOperation instance=new UpdateOperation();
	private UpdateOperation() {}
	public static UpdateOperation update() { return instance;}
	public boolean updateData(String query,int id,String data){
		    try {
				PreparedStatement pst = Database.database().getConnection().prepareStatement(query);
				pst.setString(1, data);
				pst.setInt(2, id);
				return true;
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return false;
			}
	}
	public boolean updateData(String query,int id,int data){
	    try {
			PreparedStatement pst = Database.database().getConnection().prepareStatement(query);
			pst.setInt(1, data);
			pst.setInt(2, id);
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
    }
	public boolean updateData(String query,int id,double data){
	    try {
			PreparedStatement pst = Database.database().getConnection().prepareStatement(query);
			pst.setDouble(1, data);
			pst.setInt(2, id);
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
    }
	public boolean updateFirstNameById(int id ,String firstName,String tableName) {
		    String query = "UPDATE "+tableName+" SET FirstName =  ? WHERE ID = ?";
            return updateData(query, id, firstName);
	}
	
	public boolean updateLastNameById(int id , String lastName,String tableName) {
	     	String query = "UPDATE "+tableName+" SET LastName = ? WHERE ID = ?";
	     	return updateData(query, id, lastName);
	     	
	}
	
	public boolean updateAgeById(int id , int Age,String tableName) {
		String query = "UPDATE "+tableName+" SET Age = ? WHERE ID = ?";
     	return updateData(query, id, Age);

	}

	public boolean updateMobileNumberById(int id,String MobileNumber,String tableName) {
		String query = "UPDATE "+tableName+" SET MobileNumber = ? WHERE ID = ?";
	     	return updateData(query, id, MobileNumber);

	}
	public boolean updateAttendenceById(int id , String attendance,String tableName) {
		 String query = "UPDATE "+tableName+" SET Attendence = ? WHERE ID = ?";
		   
	     return updateData(query, id, attendance);

	}
	public boolean UpdateMarksById(int id,double marks,String tableName) {
		 String query = "UPDATE "+tableName+" SET Marks = ? WHERE ID = ?";
	     return updateData(query, id, marks);

	}
	public boolean UpdateLeaveById(int id,String leave,String tableName) {
		String query = "UPDATE "+ tableName+"SET Leave = ? WHERE ID = ?";
	     return updateData(query, id, leave);
		  
	}
	public boolean UpdateAssignmentStatusById(int id , String status,String tableName) {
		    String query = "UPDATE "+tableName+" SET Assignment = ? WHERE ID = ?";
		     return updateData(query, id, status);
	}
	public boolean UpdatePlacementStatusById(int id , String status,String tableName) {
		  String query = "UPDATE "+tableName+" SET PlacementStatus = ? WHERE ID = ?";
		  return updateData(query, id, status);
	}
	public  boolean insertData(Connection connection,String tableName) throws SQLException {
		System.out.println();
		ResultSet rs = null;
		try {
			rs = connection.prepareStatement("select * from " + tableName+" LIMIT 2").executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			StringBuilder query = new StringBuilder("insert into " + tableName + "(");
			StringBuilder values = new StringBuilder("VALUES(");
			System.out.println("Enter data: ");
			for (int i = 1; i <= columnCount; i++) {
				
				String columnName = rs.getMetaData().getColumnName(i).toLowerCase();
				if(columnName.equals("id")||columnName.startsWith("id")||columnName.endsWith("id")) {
					continue;
				}
				String columnType = rs.getMetaData().getColumnTypeName(i);
				
				query.append(columnName);
	            System.out.print("Enter data for " + columnName + " (" + columnType + "): ");

	            String entry = Input.getEntrie();

	           
	            if (columnType.equalsIgnoreCase("VARCHAR") || columnType.equalsIgnoreCase("CHAR") || 

	                columnType.equalsIgnoreCase("TEXT") || columnType.equalsIgnoreCase("DATE")) {

	                values.append("'" + entry + "'");

	            } else if (columnType.equalsIgnoreCase("INT") || columnType.equalsIgnoreCase("INTEGER")) {

	                values.append(entry); 

	            } else if (columnType.equalsIgnoreCase("FLOAT") || columnType.equalsIgnoreCase("DOUBLE")) {

	                values.append(entry); 
	            } else if (columnType.equalsIgnoreCase("BOOLEAN")) {

	                values.append(entry.equalsIgnoreCase("true") ? "1" : "0");

	            } else if (columnType.equalsIgnoreCase("Tinyint")) {
	            	values.append(entry);
	            }

				if (i != columnCount)
					query.append(',');
				if (i != columnCount)
					values.append(',');
			}
			query.append(")");
			values.append(")");
			System.out.println(query + " " + values);
			if(Input.getConfirmation()) {
				int r = connection.prepareStatement(query + " " + values).executeUpdate();
				System.out.println("--> " + r + " row effected data inserted successfully");
				System.out.println();
			}
			return true;
		} catch (Exception e) {

			e.getMessage();
			return false;
			// TODO: handle exception
		} finally {
			if (rs != null)
				rs.close();
		}
	}
}