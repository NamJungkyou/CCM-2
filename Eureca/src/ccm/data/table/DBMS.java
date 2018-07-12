package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMS {
	/*
	 * DNUM BIGINT NOT NULL AUTO_INCREMENT, #DBMS번호 NAME VARCHAR(15) NOT NULL,
	 */

	private int dbNum;
	private String dbName;

	public DBMS() {
		super();
	}

	public DBMS(int dbNum, String dbName) {
		super();
		this.dbNum = dbNum;
		this.dbName = dbName;
	}

	public void setParams(ResultSet rs) throws SQLException {
		this.dbNum = rs.getInt("DBNUM");
		this.dbName = rs.getString("DBNAME");
	}

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
