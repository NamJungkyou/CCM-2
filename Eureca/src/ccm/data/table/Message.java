package ccm.data.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Message 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Message
{
	
	private Integer msgNum;			// 메시지번호
	private String prevMsgNum;		// 이전메시지번호
	private String freeWriter;		// 프리랜서 작성자
	private String empWriter;		// 사원 작성자
	private String freeReceiver;	// 프리랜서 수신자
	private String empReceiver;		// 사원 수신자
	private String msgTitle;		// 메시지 타이틀
	private String msgContent;		// 메시지 내용
	private Date msgSendDate;		// 메시지 보낸날
	private Integer msgChecked;		// 메시지 확인
	private String projNum;			// 관련 프로젝트 번호
	private Date msgCheckedDate;	// 메시지 확인을 한 날
	
	/**
	 * 기본 생성자
	 */
	public Message() {
		super();
	}
	
	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param msgNum
	 * @param prevMsgNum
	 * @param freeWriter
	 * @param empWriter
	 * @param freeReceiver
	 * @param empReceiver
	 * @param msgTitle
	 * @param msgContent
	 * @param msgSendDate
	 * @param msgChecked
	 * @param projNum
	 * @param msgCheckedDate
	 */
	public Message(Integer msgNum, String prevMsgNum, String freeWriter, String empWriter,
			String freeReceiver, String empReceiver, String msgTitle, String msgContent,
			Date msgSendDate, Integer msgChecked, String projNum, Date msgCheckedDate) {
		this.msgNum = msgNum;
		this.prevMsgNum = prevMsgNum;
		this.freeWriter = freeWriter;
		this.empWriter = empWriter;
		this.freeReceiver = freeReceiver;
		this.empReceiver = empReceiver;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgSendDate = msgSendDate;
		this.msgChecked = msgChecked;
		this.projNum = projNum;
		this.msgCheckedDate = msgCheckedDate;
	}
	
	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException{
		this.msgNum = rs.getInt("msgNum");
		this.prevMsgNum = rs.getString("prevMsgNum");
		this.freeWriter = rs.getString("freeWriter");
		this.empWriter = rs.getString("empWriter");
		this.freeReceiver = rs.getString("freeReceiver");
		this.empReceiver = rs.getString("empReceiver");
		this.msgTitle = rs.getString("msgTitle");
		this.msgContent = rs.getString("msgContent");
		this.msgSendDate = rs.getDate("msgSendDate");
		this.msgChecked = rs.getInt("msgChecked");
		this.projNum = rs.getString("projNum");
		this.msgCheckedDate = rs.getDate("msgCheckedDate");
	}
	
	/*******************************  게터 세터  ********************************/
	public Integer getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(Integer msgNum) {
		this.msgNum = msgNum;
	}
	public String getPrevMsgNum() {
		return prevMsgNum;
	}
	public void setPrevMsgNum(String prevMsgNum) {
		this.prevMsgNum = prevMsgNum;
	}
	public String getFreeWriter() {
		return freeWriter;
	}
	public void setFreeWriter(String freeWriter) {
		this.freeWriter = freeWriter;
	}
	public String getEmpWriter() {
		return empWriter;
	}
	public void setEmpWriter(String empWriter) {
		this.empWriter = empWriter;
	}
	public String getFreeReceiver() {
		return freeReceiver;
	}
	public void setFreeReceiver(String freeReceiver) {
		this.freeReceiver = freeReceiver;
	}
	public String getEmpReceiver() {
		return empReceiver;
	}
	public void setEmpReceiver(String empReceiver) {
		this.empReceiver = empReceiver;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getMsgSendDate() {
		return msgSendDate;
	}
	public void setMsgSendDate(Date msgSendDate) {
		this.msgSendDate = msgSendDate;
	}
	public Integer getMsgChecked() {
		return msgChecked;
	}
	public void setMsgChecked(Integer msgChecked) {
		this.msgChecked = msgChecked;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	public Date getMsgCheckedDate() {
		return msgCheckedDate;
	}

	public void setMsgCheckedDate(Date msgCheckedDate) {
		this.msgCheckedDate = msgCheckedDate;
	}

	@Override
	public String toString() {
		return "Message [msgNum=" + msgNum + ", prevMsgNum=" + prevMsgNum + ", freeWriter=" + freeWriter
				+ ", empWriter=" + empWriter + ", freeReceiver=" + freeReceiver + ", empReceiver=" + empReceiver
				+ ", msgTitle=" + msgTitle + ", msgContent=" + msgContent + ", msgSendDate=" + msgSendDate
				+ ", msgChecked=" + msgChecked + ", projNum=" + projNum + "]";
	}

	
	
}
