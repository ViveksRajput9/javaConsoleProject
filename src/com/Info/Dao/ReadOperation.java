package com.Info.Dao;

import java.sql.*;

public class ReadOperation {
	private static final ReadOperation instance = new ReadOperation();
	private ReadOperation() {}
	public static ReadOperation Read() { return instance;}
	
	public ResultSet getAllDetail() throws SQLException{
		String query = "SELECT * FROM "+Database.database().getTableName();
		return Database.database().getConnection().prepareStatement(query).executeQuery();
	}
}
