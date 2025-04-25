package com.Info.Dao;
import java.sql.*;

import com.Info.services.Input;

public class CreateOperation {
	private static final CreateOperation instance = new CreateOperation();
	private  CreateOperation() {}
	public static CreateOperation create() { return instance;}
	
	public boolean createTablewithQuery() throws SQLException {
		System.out.println();
		if (Input.getConfirmation()) {
			System.out.println("Creating a new table...");
			
			System.out.println("write query....");
			String query = Input.getEntrie();
			boolean flag = false;
			try {
				flag = Database.database().getConnection().createStatement().execute(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!flag) {System.out.println("not created ");return false;} 
			System.out.println("Table Created successfully");
			
			return true;
		} else
			return true;
	}
   public  boolean createTablewithFixedfield() {
		    System.out.println();
		    if (Input.getConfirmation()) {
		        System.out.println("Creating a new table...");
		        
		        // Get the table name from user input
		        String tableName = Input.getTableName();
		        
		        // Validate and sanitize the table name
		        if (!isValidTableName(tableName)) {
		            System.out.println("Invalid table name.");
		            return false;
		        }

		        // Construct the SQL statement
		        String createTableSQL = "CREATE TABLE `" + tableName + "` (" // Use backticks to escape the table name
		                + "id INT PRIMARY KEY AUTO_INCREMENT, "
		                + "firstName VARCHAR(30), "
		                + "lastName VARCHAR(30), "
		                + "MobileNumber VARCHAR(15))";

		        try (Connection conn = Database.database().getConnection();
		             PreparedStatement pst = conn.prepareStatement(createTableSQL)) {
		            
		            // Execute the SQL statement
		            pst.executeUpdate();
		            System.out.println("Table created successfully");
		            return true;
		        } catch (SQLException e) {
		            if (e.getErrorCode() == 1050) {
		                System.out.println("Table already exists");
		                return false; // Return false to indicate the table was not created
		            } else {
		                System.out.println("An error occurred while creating the table: " + e.getMessage());
		                return false; // Return false to indicate an error occurred
		            }
		        }
		    } else {
		        return false; // Return false if the user did not confirm
		    }
		}

		// Method to validate the table name
		private boolean isValidTableName(String tableName) {
		    // Implement validation logic (e.g., check for null, empty, or invalid characters)
		    return tableName != null && !tableName.trim().isEmpty() && tableName.matches("[a-zA-Z0-9_]+");
		}
   }
