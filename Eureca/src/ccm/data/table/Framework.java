package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Framework 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Framework {
	// 프레임워크 번호
	private int frameNum;
	
	// 프레임워크 이름
	private String frameName;
	
	// 개발 분야
	private String frameDevelopField;

	/**
	 * 기본 생성자
	 */
	public Framework() {
		super();
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param frameNum
	 * @param frameName
	 * @param frameDevelopField
	 */
	public Framework(int frameNum, String frameName, String frameDevelopField) {
		super();
		this.frameNum = frameNum;
		this.frameName = frameName;
		this.frameDevelopField = frameDevelopField;
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		this.frameNum = rs.getInt("FRAMENUM");
		this.frameName = rs.getString("FRAMENAME");
		this.frameDevelopField = rs.getString("FRAMEDEVELOPFIELD");
	}

	/*****************************  게터 세터  *******************************/
	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getFrameDevelopField() {
		return frameDevelopField;
	}

	public void setFrameDevelopField(String frameDevelopField) {
		this.frameDevelopField = frameDevelopField;
	}

	@Override
	public String toString() {
		return "Framework [frameNum=" + frameNum + ", frameName=" + frameName + ", frameDevelopField="
				+ frameDevelopField + "]";
	}
	
}
