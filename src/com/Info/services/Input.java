package com.Info.services;

import java.io.Console;
import java.util.Scanner;

public class Input {
	static Scanner sc = new Scanner(System.in);
	public static String setDatabaseName() {
		System.out.print("Enter Database Name:- ");
		String databaseNameLocal= sc.nextLine().trim();
	    if(databaseNameLocal.isBlank()){
			return setDatabaseName();
		}else {
			return databaseNameLocal;
		}
	}
	
	public static String getEntrie() {
	   
		String entrie = sc.nextLine().trim();
		return (entrie.isBlank()) ? getEntrie():entrie;
	}
	public static int getIntEntrie() {
		System.out.print("Enter :- ");
		int entrie=0;
		try {
			
		 entrie = Integer.parseInt(sc.nextLine());
		 
		}catch(Exception e) {
			System.out.println("input must in number");
			getIntEntrie();
		}
		return entrie<0 ? getIntEntrie() : entrie;
	}
	public static String getUsername() {
		System.out.print("Enter username :- ");
		String username = sc.nextLine().trim();

		return (username.isBlank()) ? getUsername():username;
	}
	
	public static String getPassword() {
        Console console = System.console();
        String password;
        if (console != null) {
            // Use Console to read password
            var passwordArray = console.readPassword("Enter Password: ");
            password = new String(passwordArray);
        } else {
            // Fallback to Scanner if Console is not available
            System.out.print("Enter Password: ");
            password = sc.nextLine().trim();
        }
		return (password.isBlank()) ? getPassword():password;
	}
	
	public static String getTableName() {
		System.out.print("Enter table name :- ");
		String tableName = sc.nextLine().trim();
		return (tableName.isBlank()) ? getTableName():tableName;
	}
	
	public static String getColumnName() {
		System.out.print("Enter column name :- ");
    	String column_name = sc.nextLine();
        return (column_name.isBlank())? getColumnName():column_name;
	}
	
	public static String getCondition() {
		System.out.print("Enter condition :- ");
    	String condition = sc.nextLine();
        return (condition.isBlank())? getCondition():condition;
	}
	
	public static int getDecision() {
	 	int flag=0;
		try {
			System.out.print("Enter :- ");
    		flag = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
		     System.out.println(e.getMessage());
		     flag = getDecision();
		}
		return flag;
	}
	
	public static boolean getConfirmation() {
        System.out.println("Confirmation : Y or y for Yes || N or n for No");
		try {
			System.out.print("Enter:- ");
    		char c = sc.nextLine().toLowerCase().charAt(0);
    		if(c=='y') return true;
    		else if(c=='n') return false;
    		else getConfirmation();
		} catch (Exception e) {
		    System.err.println("Invalid : input must be Number");
		     getConfirmation();
		}
		return false;
	}
}