package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinFreelancerInterview_view {
	// 면접일정에서 인터뷰와 연관된 프리랜서를 보는 뷰
	
	private String 	freeId;				// 면접과 연관된 프리랜서 아이디
	private String 	freeName;			// 면접과 연관된 프리랜서 이름
	private String 	freeState;			// 면접 채용 여부/참여신청상태
	private String 	interviewNum;		// 면접 번호
	private String 	interviewState;		// 면접 상태(면접대기, 면접완료 등)
	private String 	interviewLocation;	// 면접 장소
	private Date 	interviewDate;		// 면접 일정
	private String 	joinNum;			// 참여 신청 일정
	
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getFreeName() {
		return freeName;
	}
	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}
	public String getFreeState() {
		return freeState;
	}
	public void setFreeState(String freeState) {
		this.freeState = freeState;
	}
	public String getInterviewNum() {
		return interviewNum;
	}
	public void setInterviewNum(String interviewNum) {
		this.interviewNum = interviewNum;
	}
	public String getInterviewState() {
		return interviewState;
	}
	public void setInterviewState(String interviewState) {
		this.interviewState = interviewState;
	}
	public String getInterviewLocation() {
		return interviewLocation;
	}
	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}
	public Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(String joinNum) {
		this.joinNum = joinNum;
	}
	@Override
	public String toString() {
		return "joinFreelancerInterview_view [freeId=" + freeId + ", freeName=" + freeName + ", interviewNum="
				+ interviewNum + ", interviewState=" + interviewState + ", interviewLocation=" + interviewLocation
				+ ", interviewDate=" + interviewDate + ", joinNum=" + joinNum + ", freeState=" + freeState + "]";
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.freeId = rs.getString("freeId");
		this.freeName = rs.getString("freeName");
		this.freeState = rs.getString("freeState");
		this.interviewNum = rs.getString("interviewNum");
		this.interviewState = rs.getString("interviewState");
		this.interviewLocation = rs.getString("interviewLocation");
		this.interviewDate = rs.getDate("interviewDate");
		this.joinNum = rs.getString("joinNum");
	}
	
}
