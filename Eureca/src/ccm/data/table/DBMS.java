package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 DBMS 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class DBMS {
	
	// DBMS 번호
	private int dbNum;
	
	// DBMS 이름
	private String dbName;

	/**
	 * 기본 생성자
	 */
	public DBMS() {
		super();
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param dbNum
	 * @param dbName
	 */
	public DBMS(int dbNum, String dbName) {
		super();
		this.dbNum = dbNum;
		this.dbName = dbName;
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		this.dbNum = rs.getInt("DBNUM");
		this.dbName = rs.getString("DBNAME");
	}

	
	/*****************************  게터 세터  *********************************/
	public int getDbNum() {
		return dbNum;
	}

	public void setDbNum(int dbNum) {
		this.dbNum = dbNum;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
