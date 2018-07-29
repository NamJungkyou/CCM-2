package ccm.data.table;

import java.sql.Date;
import java.util.List;

public class ProjectView extends Project{
	
	private String usedDbName; // 데이터베이스 이름
	private String usedLangName; // 언어 이름
	private String usedFrameName; // 프레임워크 이름
	private int joinFLCount; // 참여한 프리랜서 수
	private int remainDays; // 남은 기간
	private Date expectedEndDate; // 예상종료
	private int requireCount; // 필요한 인원
	private List<ProgLang> langs; // 언어 리스트
	private List<Framework> frames; // 프레임워크 리스트
	
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
