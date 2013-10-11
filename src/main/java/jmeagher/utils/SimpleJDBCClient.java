package jmeagher.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

/**
 * A really simple JDBC client for testing random JDBC related things
 */
public class SimpleJDBCClient {

	public static void main(String[] args) throws Exception {
		int i = 0;
		String driver = args[i++];
		String url = args[i++];
		String user = null;
		String password = null;
		
		if (args.length == 4) {
			user = args[i++];
			password = args[i++];
		}
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		String sql = getSql();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData meta = rs.getMetaData();
		
		System.out.println("colc=" + meta.getColumnCount());
		
		int colCnt = meta.getColumnCount();
		for (int c = 1; c < colCnt+1; c++) {
			System.out.print(meta.getColumnLabel(c));
			System.out.print(", ");
		}
		System.out.println();
		while (rs.next()) {
			for (int c = 1; c < colCnt+1; c++) {
				System.out.print(rs.getString(c));
				System.out.print(", ");
			}			
			System.out.println("");
		}
	}
	
	private static String getSql() {
		Scanner s = new Scanner(System.in, "UTF-8").useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
