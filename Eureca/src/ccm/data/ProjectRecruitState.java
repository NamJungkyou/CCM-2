package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 프로젝트 채용 현황의 데이터를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjectRecruitState implements DTO {
	
	// 역할 번호
	private int roleNum;
	
	// 프로젝트 번호
	private int projNum;
	
	// 역할 이름
	private String roleName;
	
	// 필요 인원
	private int requireNum;
	
	// 참여한 인원
	private int numOfJoined;
	
	// 부족 인원
	private int numOfLack;

	/**
	 * 기본 생성자
	 */
	public ProjectRecruitState() {
	}

	
	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param roleNum
	 * @param projNum
	 * @param roleName
	 * @param requireNum
	 * @param numOfJoined
	 * @param numOfLack
	 */
	public ProjectRecruitState(int roleNum, int projNum, String roleName, int requireNum, int numOfJoined,
			int numOfLack) {
		this.roleNum = roleNum;
		this.projNum = projNum;
		this.roleName = roleName;
		this.requireNum = requireNum;
		this.numOfJoined = numOfJoined;
		this.numOfLack = numOfLack;
	}

	/******************************************  게터 세터  ************************************************/
	public int getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRequireNum() {
		return requireNum;
	}

	public void setRequireNum(int requireNum) {
		this.requireNum = requireNum;
	}

	public int getNumOfJoined() {
		return numOfJoined;
	}

	public void setNumOfJoined(int numOfJoined) {
		this.numOfJoined = numOfJoined;
	}

	public int getNumOfLack() {
		return numOfLack;
	}

	public void setNumOfLack(int numOfLack) {
		this.numOfLack = numOfLack;
	}

	/**
	 * ResultSet에서 가져온 값으로 필드를 초기화하는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		roleNum = rs.getInt("ROLENUM");
		projNum = rs.getInt("PROJNUM");
		roleName = rs.getString("ROLENAME");
		requireNum = rs.getInt("REQUIRENUM");
		numOfJoined = rs.getInt("NUMOFJOINED");
		numOfLack = rs.getInt("NUMOFLACK");
	}
}