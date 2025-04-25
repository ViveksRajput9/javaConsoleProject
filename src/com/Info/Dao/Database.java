package com.Info.Dao;

import java.sql.*;

import com.Info.services.Input;

public class Database {
	private static final Database instance = new Database();
	private   String databaseName;
	private  String tableName;
    private String Password;
    private String userName;
    private Connection database;
    private Database() {
    	
    }
    public static Database database() {return instance;}
    
	public  String getDatabaseName() {
		if (!databaseName.isBlank())
			return databaseName;
		else {
			String localdatabaseName = Input.setDatabaseName();
			while (!localdatabaseName.isBlank()) {
				databaseName = Input.setDatabaseName();
			}
			return databaseName;
		}
	}
	public  String getTableName() {
		return tableName;
	}

	public  Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, userName,Password);

	}
    public ResultSet getDatabases() throws SQLException {
		return database.createStatement().executeQuery("SHOW DATABASES");

    }
    public ResultSet getTables() throws SQLException{
    	return getConnection().createStatement().executeQuery("SHOW TABLES");
    }
    public String getTableFirstColumnName() throws SQLException {
        
    	return  getConnection().prepareStatement("SELECT * FROM "+getTableName()+ " LIMIT 1").executeQuery().getMetaData().getColumnName(1);
    }
    public ResultSet getTableAllColumnNames() throws SQLException{
    	return getConnection().prepareStatement("SELECT * FROM "+getTableName()+" LIMIT 1").executeQuery();
    }
    private boolean isTableExist(String tableName) throws SQLException {
        // Use a prepared statement to prevent SQL injection
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
        PreparedStatement   preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, databaseName);
        preparedStatement.setString(2, tableName);
 	   
        if(databaseName.isBlank()) {
        	System.out.println("Database name is null");
        }
        ResultSet rs = preparedStatement.executeQuery();
        // Check if the result set has data
        
        if (rs.next()) {
            int count = rs.getInt(1);
            if(count>0) {
            	return true;
            }
            else return false;
        }

	    return false;
    }

	public  boolean selectTable(String tableName) {
	    try {
	        // Check if the result set has data
	    	    boolean flag = isTableExist(tableName);

	            if (flag) {
	            	System.out.println("Table selected: " + tableName);
	            	this.tableName = tableName;
	                return true;
	            }
	            System.err.println("Table does not exist.");
	            return false;
	            
	        
	    } catch (SQLException e) {
	        // Handle SQL exceptions specifically
	        System.err.println("SQL error: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any other exceptions
	        System.err.println("Error: " + e.getMessage());
	    }
		return false; 
	}
	public boolean selectDatabase(String DatabaseName) throws SQLException {
			try {
				
				DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DatabaseName, userName,Password);
				databaseName = DatabaseName;
				return true;
			} catch (SQLException e) {
				if (e.getErrorCode() == 1045) { // Access denied for user

					System.err.println("Error: Access denied Please check your username and password.");

				} else if (e.getErrorCode() == 1049) { // Unknown database

					System.err.println("Error: Unknown database '" + databaseName + "'. Please check the database name.");

				} else if (e.getErrorCode() == 0) { // General connection error

					System.err.println("Error: Unable to connect to the database. Please check your connection settings.");

				} else {
					System.err.println("SQL Error: " + e.getMessage());
				}
				return false;
			} catch (Exception e) {

				System.err.println("An unexpected error occurred: " + e.getMessage());
				return false;
			}
    }
	
	public boolean connectDatabase() {
		System.out.println();
		System.out.println("Enter Database Details...");
		userName = Input.getUsername();
		Password = Input.getPassword();
         
		try {
			database= DriverManager.getConnection("jdbc:mysql://localhost:3306", userName,
					Password);
			return true;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1045) { // Access denied for user

				System.err.println("Error: Access denied for user '" + userName + "'. Please check your username and password.");

			} else if (e.getErrorCode() == 1049) { // Unknown database

				System.err.println("Error: Unknown database '" + databaseName + "'. Please check the database name.");

			} else if (e.getErrorCode() == 0) { // General connection error

				System.err.println("Error: Unable to connect to the database. Please check your connection settings.");

			} else {
				System.err.println("SQL Error: " + e.getMessage());
			}
			e.printStackTrace();
			return false;
		} catch (Exception e) {

			System.err.println("An unexpected error occurred: " + e.getMessage());
			return false;
		}
	}
//	public boolean createStudentTable() {
//		String query = "CREATE TABLE IF NOT EXISTS +"
//	}
}
