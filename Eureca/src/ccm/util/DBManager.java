package ccm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***************************
 * 
 * 
 * 디비매니저(연결과 해제를 하는 기능으로 구성된 클래스이다)
 * 
 * 작성자 :
 * 
 * 수정자 :
 * 
 * 수정일 :
 *
 *
 ***************************/

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;

		/*try {
			Context initContext = new InitialContext();
			// Context envContext = (Context) initContext.lookup("java:/comp/env");
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// jdbc/myoracle이란 이름의 객체를 찾아서 DataSource가 받는다.
			DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
			// DataSource ds = (DataSource) envContext.lookup("java:comp/env/jdbc/mysql");
			// ds가 생성되었으므로 Connection을 구합니다.
			conn = ds.getConnection();

			System.out.println(conn == null ? "null" : conn.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;*/
		  try {
		         Class.forName("org.gjt.mm.mysql.Driver");
		         conn = DriverManager.getConnection("jdbc:mysql://ccmfmsmysqlserver.mysql.database.azure.com:3306/fms?useSSL=true&requireSSL=false", 
		                                    "ccmadmin@ccmfmsmysqlserver", "ccmfmsmysqladmin1234!");
		         System.out.println("DB 연동 :  " + conn);         

		      } catch (SQLException ex) {
		         System.out.println("SQLException" + ex);
		      }

		      catch (Exception ex) {
		         System.out.println("Exception" + ex);
		      }
		      
		      return conn;

	}

	// select를 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt, ResultSet... rs) {
		try {
			for (ResultSet r : rs) {
				if (r != null)
					r.close();
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}