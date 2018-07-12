package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjRole {
	/*
	 * 
	 * roleNum BIGINT NOT NULL AUTO_INCREMENT, #역할 번호 roleName VARCHAR(12) NOT NULL,
	 * #역할 이름 requireNum INT NOT NULL, #필요 인원 projNum BIGINT NULL, #프로젝트 번호
	 * 
	 */

	private int roleNum;
	private String roleName;
	private int requireNum;
	private int projNum;

	public ProjRole() {
		super();
	}

	public ProjRole(int roleNum, String roleName, int requireNum, int projNum) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.requireNum = requireNum;
		this.projNum = projNum;
	}

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

	public void setParams(ResultSet rs) throws SQLException
	{
		this.roleNum = rs.getInt("ROLENUM");
		this.roleName = rs.getString("ROLENAME");
		this.requireNum = rs.getInt("REQUIRENUM");
		this.projNum = rs.getInt("PROJNUM");
	}
}
