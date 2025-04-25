package com.Info.services;

import java.sql.ResultSet;
import java.sql.Statement;

public class Print {
	private static final Print instance = new Print();

	private Print() {
	}

	public static Print print() {
		return instance;
	}

	private void printSpace(int len) {
		if (len > 0)
			for (int i = 0; i < len; i++) {
				System.out.print(" ");
			}
	}

	public void printTable(ResultSet rs) {
		System.out.println();
		try {
			int whiteSpace = 20;
			int extraSpace=0;
			int columnCount = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				extraSpace = 0;
				String result = rs.getMetaData().getColumnName(i);

				System.out.print("|");
				if(result.equalsIgnoreCase("email")) {
					extraSpace = 14;
				}
				printSpace(((whiteSpace+extraSpace) - result.length() + 1) / 2);
				System.out.print(result);
				printSpace(((whiteSpace +extraSpace)- result.length() + 2) / 2);
				if (columnCount == i)
					System.out.print("|");

			}
			System.out.println();
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					extraSpace = 0;
					String result = rs.getString(i);
					System.out.print("|");
					if(result.endsWith("com")) extraSpace=14;
					printSpace(((whiteSpace+extraSpace) - result.length() + 1) / 2);
					System.out.print(result);
					printSpace(((whiteSpace+extraSpace )- result.length() + 2) / 2);
					if (columnCount == i)
						System.out.print("|");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("printing completed...");
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("             No Record Found              ");
		}
	}

}
