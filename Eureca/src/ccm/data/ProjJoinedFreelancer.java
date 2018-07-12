package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProjJoinedFreelancer
{
	private int projNum;
	private int joinNum;
	private String freeId;
	private String freeName;
	private String projRole;
	private Timestamp joinProjDate;
	private Timestamp exitProjDate;
	private String freePhone;
	private String freeEmail;
	
	public ProjJoinedFreelancer()
	{
		super();
	}
	public ProjJoinedFreelancer(int projNum, int joinNum, String freeId, String freeName, String projRole, Timestamp joinProjDate,
			Timestamp exitProjDate, String freePhone, String freeEmail)
	{
		super();
		this.projNum = projNum;
		this.joinNum = joinNum;
		this.freeId = freeId;
		this.freeName = freeName;
		this.projRole = projRole;
		this.joinProjDate = joinProjDate;
		this.exitProjDate = exitProjDate;
		this.freePhone = freePhone;
		this.freeEmail = freeEmail;
	}
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.projNum = rs.getInt("PROJNUM");
		this.joinNum = rs.getInt("JOINNUM");
		this.freeId = rs.getString("fREEID");
		this.freeName = rs.getString("FREENAME");
		this.projRole = rs.getString("PROJROLE");
		this.joinProjDate = rs.getTimestamp("JOINPROJDATE");
		this.exitProjDate = rs.getTimestamp("EXITPROJDATE");
		this.freePhone = rs.getString("FREEPHONE");
		this.freeEmail = rs.getString("FREEEMAIL");
	}
	
	public int getProjNum() { return projNum; }
	public void setProjNum(int projNum) { this.projNum = projNum; }
	public int getJoinNum() { return joinNum; }
	public void setJoinNum(int joinNum) { this.joinNum = joinNum; }
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public String getFreeName() { return freeName; }
	public void setFreeName(String freeName) { this.freeName = freeName; }
	public String getProjRole() { return projRole; }
	public void setProjRole(String projRole) { this.projRole = projRole; }
	public Timestamp getJoinProjDate() { return joinProjDate; }
	public void setJoinProjDate(Timestamp joinProjDate) { this.joinProjDate = joinProjDate; }
	public Timestamp getExitProjDate() { return exitProjDate; }
	public void setExitProjDate(Timestamp exitProjDate) { this.exitProjDate = exitProjDate; }
	public String getFreePhone() { return freePhone; }
	public void setFreePhone(String freePhone) { this.freePhone = freePhone; }
	public String getFreeEmail() { return freeEmail; }
	public void setFreeEmail(String freeEmail) { this.freeEmail = freeEmail; }
}
