package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRecruitState implements DTO
{
	private int roleNum;
	private int projNum;
	private String roleName;
	private int requireNum;
	private int numOfJoined;
	private int numOfLack;
	
	public ProjectRecruitState() { super(); }
	public ProjectRecruitState(int roleNum, int projNum, String roleName, int requireNum,
			int numOfJoined, int numOfLack)
	{
		super();
		this.roleNum = roleNum;
		this.projNum = projNum;
		this.roleName = roleName;
		this.requireNum = requireNum;
		this.numOfJoined = numOfJoined;
		this.numOfLack = numOfLack;
	}

	public int getRoleNum() { return roleNum; }
	public void setRoleNum(int roleNum) { this.roleNum = roleNum; }
	public int getProjNum() { return projNum; }
	public void setProjNum(int projNum) { this.projNum = projNum; }
	public String getRoleName() { return roleName; }
	public void setRoleName(String roleName) { this.roleName = roleName; }
	public int getRequireNum() { return requireNum; }
	public void setRequireNum(int requireNum) { this.requireNum = requireNum; }
	public int getNumOfJoined() { return numOfJoined; }
	public void setNumOfJoined(int numOfJoined) { this.numOfJoined = numOfJoined; }
	public int getNumOfLack() { return numOfLack; }
	public void setNumOfLack(int numOfLack) { this.numOfLack = numOfLack; }

	@Override
	public void setParams(ResultSet rs) throws SQLException
	{
		this.roleNum = rs.getInt("ROLENUM");
		this.projNum = rs.getInt("PROJNUM");
		this.roleName = rs.getString("ROLENAME");
		this.requireNum = rs.getInt("REQUIRENUM");
		this.numOfJoined = rs.getInt("NUMOFJOINED");
		this.numOfLack = rs.getInt("NUMOFLACK");
	}
}
