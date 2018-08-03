package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 ProjRole 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjRole {
	
	// 역할 번호
	private int roleNum;
	
	// 역할 이름
	private String roleName;
	
	// 필요인원
	private int requireNum;
	
	// 프로젝트 번호
	private int projNum;

	/**
	 * 기본 생성자
	 */
	public ProjRole() {
		super();
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param roleNum
	 * @param roleName
	 * @param requireNum
	 * @param projNum
	 */
	public ProjRole(int roleNum, String roleName, int requireNum, int projNum) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.requireNum = requireNum;
		this.projNum = projNum;
	}

	/*********************  게터 세터  **********************/
	public int getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
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

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
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
		this.roleNum = rs.getInt("ROLENUM");
		this.roleName = rs.getString("ROLENAME");
		this.requireNum = rs.getInt("REQUIRENUM");
		this.projNum = rs.getInt("PROJNUM");
	}
}
