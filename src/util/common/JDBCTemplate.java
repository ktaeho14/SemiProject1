package util.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url   ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String id    ="KH";
	private static String pwd   ="KH";
	private static Connection conn = null;
	
	//connection
	public static Connection getConnection() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pwd);
			
			//자동커밋 방지
			conn.setAutoCommit(false);
		}catch(Exception e) {
			System.out.println("[ERR]Connect ERROR");
			e.printStackTrace();
		}
		return conn;
	}
	
	//commit
	public static void commit(Connection con) {
		//auto commit 막기
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERR]COMMIT 에러");
			e.printStackTrace();
		}
	}
	
	//rollback
	public static void rollback(Connection con) {
		try {
			con.rollback();
		}catch(SQLException e) {
			System.out.println("[ERR]ROLLBACK 에러");
			e.printStackTrace();
		}
	}
	
	//close overloading
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERR]Connection close error!");
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stat) {
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERR]Statement close error!");
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pst) {
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERR]PreparedStatement close error!");
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERR]ResultSet close error!");
			e.printStackTrace();
		}
	}
}
