package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Interview 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Interview
{
	// 면접번호
	private Integer intvNum;
	
	// 면접일
	private Date intvDate;
	
	// 면접 장소
	private String intvLocation;
	
	// 불채용사유
	private String intvReason;
	
	// 면접상태
	private Integer intvState;
	
	// 프리랜서 아이디
	private String freeNum;
	
	/***********************  게터 세터  *************************/
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
	
	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
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
