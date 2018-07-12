package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommendedFreelancer implements DTO
{
	private String freeId;
	private String freeState;
	private String freeName;
	private String leftJoinDays;

	private String primaryLangs;
	private String primaryFrames;
	private int projCareerYears;
	private String freeKosa;
	private double freeScore;

	
	
	public RecommendedFreelancer() { super(); }
	public RecommendedFreelancer(String freeId, String freeState, String freeName, String leftJoinDays
			, String primaryLangs, String primaryFrames, int projCareerYears, String freeKosa, double freeScore)
	{
		super();
		this.freeId = freeId;
		this.freeState = freeState;
		this.freeName = freeName;
		this.leftJoinDays = leftJoinDays;
		this.primaryLangs = primaryLangs;
		this.primaryFrames = primaryFrames;
		this.projCareerYears = projCareerYears;
		this.freeKosa = freeKosa;
		this.freeScore = freeScore;
	}
	
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public String getFreeState() { return freeState; }
	public void setFreeState(String freeState) { this.freeState = freeState; }
	public String getFreeName() { return freeName; }
	public void setFreeName(String freeName) { this.freeName = freeName; }
	public String getLeftJoinDays() { return leftJoinDays; }
	public void setLeftJoinDays(String leftJoinDays) { this.leftJoinDays = leftJoinDays; }
	public String getPrimaryLangs() { return primaryLangs; }
	public void setPrimaryLangs(String primaryLangs) { this.primaryLangs = primaryLangs; }
	public String getPrimaryFrames() { return primaryFrames; }
	public void setPrimaryFrames(String primaryFrames) { this.primaryFrames = primaryFrames; }
	public int getProjCareerYears() { return projCareerYears; }
	public void setProjCareerYears(int projCareerYears) { this.projCareerYears = projCareerYears; }
	public String getFreeKosa() { return freeKosa; }
	public void setFreeKosa(String freeKosa) { this.freeKosa = freeKosa; }
	public double getFreeScore() { return freeScore; }
	public void setFreeScore(double freeScore) { this.freeScore = freeScore; }

	@Override
	public void setParams(ResultSet rs) throws SQLException
	{
		this.freeId = rs.getString("FREEID");
		this.freeState = rs.getString("FREESTATE");
		this.freeName = rs.getString("FREENAME");
		this.leftJoinDays = rs.getString("LEFTJOINDAYS");
		this.primaryLangs = rs.getString("PRIMARYLANGS");
		this.primaryFrames = rs.getString("PRIMARYFRAMES");
		this.projCareerYears = rs.getInt("PROJCAREERYEARS");
		
		switch(rs.getInt("freeKosa"))
		{
		case 1:
			this.freeKosa = "초급기능사";	
			break;
		case 2:
			this.freeKosa = "중급기능사";
			break;
		case 3:
			this.freeKosa = "고급기능사";
			break;
		case 4:
			this.freeKosa = "초급기술자";
			break;
		case 5:
			this.freeKosa = "중급기술자";
			break;
		case 6:
			this.freeKosa = "고급기술자";
			break;
		case 7:
			this.freeKosa = "특급기술자";
			break;
		default:
			this.freeKosa = "등급 없음";
			break;
		}
		
		this.freeScore = rs.getDouble("FREESCORE");
	}
}
