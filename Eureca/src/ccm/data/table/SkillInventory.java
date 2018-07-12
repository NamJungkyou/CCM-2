package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillInventory {

	private Integer projNum;
	private Integer isExtern;
	private String projName;
	private String freeId;
	private String joinProjDate;
	private String exitProjDate;
	private String projTarget;
	private String projCompany;
	private String projRole;
	private Integer dbNum;
	private Integer joinNum;
	private Integer langNum;
	private Integer frameNum;
	ArrayList<Framework> frames;
	ArrayList<ProgLang> projlangs;
	DBMS db;

	public SkillInventory() {
		super();
		frames = new ArrayList<Framework>();
		projlangs = new ArrayList<ProgLang>();
	}

	public void setParams(ResultSet rs) throws SQLException {
		projNum = rs.getInt("PROJNUM");
		isExtern = rs.getInt("ISEXTERN");
		projName = rs.getString("PROJNAME");
		freeId = rs.getString("FREEID");
		joinProjDate = rs.getString("JOINPROJDATE").substring(0, 10);
		exitProjDate = rs.getString("EXITPROJDATE").substring(0, 10);
		projTarget = rs.getString("PROJTARGET");
		projCompany = rs.getString("PROJCOMPANY");
		projRole = rs.getString("PROJROLE");
		dbNum = rs.getInt("DBNUM");
	}

	public DBMS getDb() {
		return db;
	}

	public void setDb(DBMS db) {
		this.db = db;
	}

	public Integer getProjNum() {
		return projNum;
	}

	public void setProjNum(Integer projNum) {
		this.projNum = projNum;
	}

	public Integer getIsExtern() {
		return isExtern;
	}

	public void setIsExtern(Integer isExtern) {
		this.isExtern = isExtern;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public String getJoinProjDate() {
		return joinProjDate;
	}

	public void setJoinProjDate(String joinProjDate) {
		this.joinProjDate = joinProjDate;
	}

	public String getExitProjDate() {
		return exitProjDate;
	}

	public void setExitProjDate(String exitProjDate) {
		this.exitProjDate = exitProjDate;
	}

	public String getProjTarget() {
		return projTarget;
	}

	public void setProjTarget(String projTarget) {
		this.projTarget = projTarget;
	}

	public String getProjCompany() {
		return projCompany;
	}

	public void setProjCompany(String projCompany) {
		this.projCompany = projCompany;
	}

	public String getProjRole() {
		return projRole;
	}

	public void setProjRole(String projRole) {
		this.projRole = projRole;
	}

	public Integer getDbNum() {
		return dbNum;
	}

	public void setDbNum(Integer dbNum) {
		this.dbNum = dbNum;
	}

	public ArrayList<Framework> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<Framework> frames) {
		this.frames = frames;
	}

	public ArrayList<ProgLang> getProjlangs() {
		return projlangs;
	}

	public void setProjlangs(ArrayList<ProgLang> projlangs) {
		this.projlangs = projlangs;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getLangNum() {
		return langNum;
	}

	public void setLangNum(Integer langNum) {
		this.langNum = langNum;
	}

	public Integer getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(Integer frameNum) {
		this.frameNum = frameNum;
	}

	@Override
	public String toString() {
		return "SkillInventory [projNum=" + projNum + ", isExtern=" + isExtern + ", projName=" + projName + ", freeId="
				+ freeId + ", joinProjDate=" + joinProjDate + ", exitProjDate=" + exitProjDate + ", projTarget="
				+ projTarget + ", projCompany=" + projCompany + ", projRole=" + projRole + ", dbNum=" + dbNum
				+ ", joinNum=" + joinNum + ", langNum=" + langNum + ", frameNum=" + frameNum + ", frames=" + frames
				+ ", projlangs=" + projlangs + ", db=" + db + "]";
	}

}