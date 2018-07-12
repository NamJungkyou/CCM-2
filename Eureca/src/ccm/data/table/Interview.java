package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Interview
{
	/*
	
	INUM BIGINT NOT NULL AUTO_INCREMENT, #면접번호
  INTVDATE DATE NOT NULL, #면접일
  LOCATION VARCHAR(15) NOT NULL, #면접장소
  REASON VARCHAR(100) NULL, #불채용사유
  STATE INT NOT NULL, #상태
  FID VARCHAR(15) NOT NULL, #프리랜서아이디
	
	*/
	private Integer intvNum;
	private Date intvDate;
	private String intvLocation;
	private String intvReason;
	private Integer intvState;
	private String freeNum;
	
	public Integer getIntvNum() {
		return intvNum;
	}
	public void setIntvNum(Integer intvNum) {
		this.intvNum = intvNum;
	}
	public Date getIntvDate() {
		return intvDate;
	}
	public void setIntvDate(Date intvDate) {
		this.intvDate = intvDate;
	}
	public String getIntvLocation() {
		return intvLocation;
	}
	public void setIntvLocation(String intvLocation) {
		this.intvLocation = intvLocation;
	}
	public String getIntvReason() {
		return intvReason;
	}
	public void setIntvReason(String intvReason) {
		this.intvReason = intvReason;
	}
	public Integer getIntvState() {
		return intvState;
	}
	public void setIntvState(Integer intvState) {
		this.intvState = intvState;
	}
	public String getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(String freeNum) {
		this.freeNum = freeNum;
	}
	
	@Override
	public String toString() {
		return "Interview [intvNum=" + intvNum + ", intvDate=" + intvDate + ", intvLocation=" + intvLocation
				+ ", intvReason=" + intvReason + ", intvState=" + intvState + ", freeNum=" + freeNum + "]";
	}
	public void setParams(ResultSet rs) throws SQLException
	{
		this.intvNum = rs.getInt("interviewNum");
		this.intvDate = rs.getDate("interviewDate");
		this.intvLocation = rs.getString("interviewLcation");
		this.intvReason = rs.getString("interviewReason");
		this.intvState = rs.getInt("interviewState");
		this.freeNum = rs.getString("freeNum");
	}
}
