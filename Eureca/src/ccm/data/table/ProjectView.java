package ccm.data.table;

import java.sql.Date;
import java.util.List;

public class ProjectView extends Project{
	
	private String usedDbName;
	private String usedLangName;
	private String usedFrameName;
	private int joinFLCount;
	private int remainDays;
	private Date expectedEndDate;
	private int requireCount;
	private List<ProgLang> langs;
	private List<Framework> frames;
	
	public String getUsedDbName() {
		return usedDbName;
	}
	public void setUsedDbName(String usedDbName) {
		this.usedDbName = usedDbName;
	}
	public String getUsedLangName() {
		return usedLangName;
	}
	public void setUsedLangName(String usedLangName) {
		this.usedLangName = usedLangName;
	}
	public String getUsedFrameName() {
		return usedFrameName;
	}
	public void setUsedFrameName(String usedFrameName) {
		this.usedFrameName = usedFrameName;
	}
	public int getJoinFLCount() {
		return joinFLCount;
	}
	public void setJoinFLCount(int joinFLCount) {
		this.joinFLCount = joinFLCount;
	}
	public int getRemainDays() {
		return remainDays;
	}
	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}
	public Date getExpectedEndDate() {
		return expectedEndDate;
	}
	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}
	
	public int getRequireCount() {
		return requireCount;
	}
	public void setRequireCount(int requireCount) {
		this.requireCount = requireCount;
	}
	public List<ProgLang> getLangs() {
		return langs;
	}
	public void setLangs(List<ProgLang> langs) {
		this.langs = langs;
	}
	public List<Framework> getFrames() {
		return frames;
	}
	public void setFrames(List<Framework> frames) {
		this.frames = frames;
	}
	@Override
	public String toString() {
		return "ProjectView [usedDbName=" + usedDbName + ", usedLangName=" + usedLangName + ", usedFrameName="
				+ usedFrameName + ", joinFLCount=" + joinFLCount + ", remainDays=" + remainDays + ", expectedEndDate="
				+ expectedEndDate + ", requireCount=" + requireCount + ", langs=" + langs + ", frames=" + frames + "]";
	}
	
}
