package com.Info.Dao;

import java.sql.*;

import com.Info.services.Input;

public class DeleteOperation {
	private static final DeleteOperation instance = new DeleteOperation();
	private DeleteOperation() {}
	public static DeleteOperation delete() {
		return instance;
	}
    public boolean warning() {
        System.err.println("Warning : After delete data you can't rollback ");
    	return Input.getConfirmation();
    }
    public boolean dropTable(){
    	String table_name = Input.getTableName();
    	try {
    		if(warning()) {
    			int rs= Database.database().getConnection().createStatement().executeUpdate("drop table "+table_name);
    			return true;    			
    		}
    		else {
    			System.out.println("Execution declined");
    			return false;
    		}
    	}
    	catch(SQLException e) {
             e.getMessage();
             return false;
    	}
    }
    
    public boolean truncateTable(){
    	String table_name = Input.getTableName();
    	try {
    		if(warning()) {
    			int s = Database.database().getConnection().createStatement().executeUpdate("truncate table "+table_name);
    			return true;    			
    		}
    		else {
    			System.out.println("Execution declined");
    			return false;
    		}
    	}
    	catch(SQLException e) {
             e.getMessage();
             return false;
    	}
    }
    public boolean dropColumn(){
    	String column_name = Input.getColumnName();
    	try {
    		if(warning()) {
    			Database.database().getConnection().createStatement().executeUpdate("ALTER TABLE "+Database.database().getTableName()+" DROP COLUMN "+column_name);
    			return true;    			
    		}
    		else {
    			System.out.println("Execution declined");
    			return false;
    		}
    	}
    	catch(SQLException e) {
             e.getMessage();
             return false;
    	}
	}
    
    public void deleteRow() throws SQLException{
    	String column_name = Input.getColumnName();
    	String condition = Input.getCondition();
    	String query = "DELETE FROM "+Database.database().getTableName()+" WHERE "+column_name+" = "+condition;
    	System.out.println(query);
    	while(true) {
    	System.out.println("choose one of these...");
    	System.out.println("1.execute");
    	System.out.println("2.declined");
    	System.out.println("3.rewrite");

        int flag = Input.getDecision();
    	if(flag==1) {
    		if(warning()) {
    			int row = Database.database().getConnection().createStatement().executeUpdate(query);  
    			System.out.println(row +" row effected ");
    			System.out.println("Query execute successfully");
    			break;
    		}
    		else {
    			System.out.println("Execution declined");
    			break;
    		}
    	}else if(flag == 2){
    		System.out.println("Declined");
    		break;
    	}else if (flag==3){
			deleteRow();
		}else{
			System.out.println("Oops... Wrong input choose again");
		}
    	}
	}
    public int deleteRow(int id) throws SQLException {
    	String Query = "DELETE FROM " + Database.database().getTableName()+" WHERE "+Database.database().getTableFirstColumnName()+" = ?";
    	PreparedStatement con = Database.database().getConnection().prepareStatement(Query);
    	con.setInt(1, id);
        return con.executeUpdate();
    }
}