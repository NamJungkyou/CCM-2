package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinAppliedFreelancer extends RecommendedFreelancer implements DTO
{
	private int joinNum;

	public JoinAppliedFreelancer() { super(); }
	public JoinAppliedFreelancer(String freeId, String freeState, String freeName, String leftJoinDays,
			String primaryLangs, String primaryFrames, int projCareerYears, String freeKosa, double freeScore)
	{
		super(freeId, freeState, freeName, leftJoinDays, primaryLangs, primaryFrames,
				projCareerYears, freeKosa, freeScore);
	}
	public JoinAppliedFreelancer(int joinNum)
	{
		super();
		this.joinNum = joinNum;
	}
	
	public int getJoinNum() { return joinNum; }
	public void setJoinNum(int joinNum) { this.joinNum = joinNum; }
	
	@Override
	public void setParams(ResultSet rs) throws SQLException
	{
		this.setFreeId(rs.getString("FREEID"));
		this.setFreeState(rs.getString("FREESTATE"));
		this.setFreeName(rs.getString("FREENAME"));
		this.setLeftJoinDays(rs.getString("LEFTJOINDAYS"));
		this.setPrimaryLangs(rs.getString("PRIMARYLANGS"));
		this.setPrimaryFrames(rs.getString("PRIMARYFRAMES"));
		this.setProjCareerYears(rs.getInt("PROJCAREERYEARS"));
		
		switch(rs.getInt("freeKosa"))
		{
		case 1:
			this.setFreeKosa("초급기능사");	
			break;
		case 2:
			this.setFreeKosa("중급기능사");
			break;
		case 3:
			this.setFreeKosa("고급기능사");
			break;
		case 4:
			this.setFreeKosa("초급기술자");
			break;
		case 5:
			this.setFreeKosa("중급기술자");
			break;
		case 6:
			this.setFreeKosa("고급기술자");
			break;
		case 7:
			this.setFreeKosa("특급기술자");
			break;
		default:
			this.setFreeKosa("등급 없음");
			break;
		}
		
		this.setFreeScore(rs.getDouble("FREESCORE"));
		this.joinNum = rs.getInt("JOINNUM");
	}
}
