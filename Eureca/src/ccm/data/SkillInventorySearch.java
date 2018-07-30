package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 프리랜서 검색에서 스킬인벤토리를 보여주는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class SkillInventorySearch implements DTO {
	// 프리랜서 이름
	private String projName;
	
	// 참여 기간
	private String joinTerm;
	
	// 프로젝트 협력사
	private String projPartner;
	
	// 프로젝트 당시 소속회사
	private String projCompany;
	
	// 역할
	private String projRole;
	
	// 언어 스킬
	private String langSkills;
	
	// 프레임워크 스킬
	private String frameSkills;
	
	// 프로젝트에서 쓰던 DBMS 이름
	private String dbName;

	/**
	 * 기본 생성자
	 */
	public SkillInventorySearch() {
	}

	/********************************  게터 세터  ***********************************/
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getJoinTerm() {
		return joinTerm;
	}

	public void setJoinTerm(String joinTerm) {
		this.joinTerm = joinTerm;
	}

	public String getProjPartner() {
		return projPartner;
	}

	public void setProjPartner(String projPartner) {
		this.projPartner = projPartner;
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

	public String getLangSkills() {
		return langSkills;
	}

	public void setLangSkills(String langSkills) {
		this.langSkills = langSkills;
	}

	public String getFrameSkills() {
		return frameSkills;
	}

	public void setFrameSkills(String frameSkills) {
		this.frameSkills = frameSkills;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * DAO에서 편리하게 쓰기 위한 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		projName = rs.getString("PROJNAME");
		joinTerm = rs.getString("JOINTERM");
		projPartner = rs.getString("PROJPARTNER");
		projCompany = rs.getString("PROJCOMPANY");
		projRole = rs.getString("PROJROLE");
		langSkills = rs.getString("LANGSKILLS");
		frameSkills = rs.getString("FRAMESKILLS");
		dbName = rs.getString("DBNAME");
	}
}
