package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Project {

	private int projNum;
	private boolean isExtern;
	private String projField;
	private String projName;
	private String projState;
	private String projRegisterDate;
	private String projRegisterer;
	private String projReviseDate;
	private String projReviser;
	private String projStartDate;
	private String projEndDate;
	private Integer projExpectedTime;
	private String projTarget;
	private String projPartner;
	private String projPlan;
	private String projRecruitStartDate;
	private String projRecruitEndDate;
	private String projDevelopSort;
	private int dbNum;
	private String projLang;
	private String projFramework;
	private String projCompany;
	
	//PNUM BIGINT AUTO_INCREMENT NOT NULL, #프로젝트번호
	//ISEXTERN BOOLEAN NOT NULL, #외부프로젝트 여부
	//PFIELD VARCHAR(10) NOT NULL, #프로젝트 유형
	//NAME VARCHAR(20) NOT NULL, #프로젝트 이름
	//PROGSTATE VARCHAR(5) NOT NULL, #진행상태
	//REGDATE DATE NULL, #등록일
	//REGPERSON VARCHAR(15) NULL, #등록인
	//REVDATE DATE NULL, #수정일
	//REVPERSON VARCHAR(15) NULL, #수정인
	//STARTDATE DATE NOT NULL, #프로젝트 시작일
	//ENDDATE DATE NULL, #프로젝트 종료일
	//EXPECTEDTIME INT NULL, #예상기간
	//TARGET VARCHAR(20) NOT NULL, #고객사
	//PARTNER VARCHAR(20) NULL, #협력사
	//PLAN VARCHAR(5000) NOT NULL, #세부내용
	//RECRUSTARTDATE DATE NULL, #모집시작일
	//RECRUENDDATE DATE NULL, #모집마감일
	//DEVSORT VARCHAR(10) NOT NULL, #개발분야
	//DNUM BIGINT NOT NULL, #사용 DBMS
	
	public void setParams(ResultSet rs) throws SQLException
	{
		this.projNum = rs.getInt("PROJNUM");
		this.projCompany = rs.getString("PROJCOMPANY");
		this.isExtern = rs.getBoolean("ISEXTERN");
		this.projField = rs.getString("PROJFIELD");
		this.projName = rs.getString("PROJNAME");
		this.projState = rs.getString("PROJSTATE");
		this.projRegisterDate = rs.getString("PROJREGISTERDATE");
		this.projRegisterer = rs.getString("PROJREGISTERER");
		this.projReviseDate = rs.getString("PROJREVISEDATE");
		this.projReviser = rs.getString("PROJREVISER");
		this.projStartDate = rs.getString("PROJSTARTDATE");
		this.projEndDate = rs.getString("PROJENDDATE");
		this.projExpectedTime = rs.getInt("PROJEXPECTEDTIME");
		this.projTarget = rs.getString("PROJTARGET");
		this.projPartner = rs.getString("PROJPARTNER");
		this.projPlan = rs.getString("PROJPLAN");
		this.projRecruitStartDate = rs.getString("PROJRECRUITSTARTDATE");
		this.projRecruitEndDate = rs.getString("PROJRECRUITENDDATE");
		this.projDevelopSort = rs.getString("PROJDEVELOPSORT");
		this.dbNum = rs.getInt("DBNUM");
	}
	
	public int getProjNum() {
		return projNum;
	}
	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}
	public boolean getIsExtern() {
		return isExtern;
	}
	public void setIsExtern(boolean isExtern) {
		this.isExtern = isExtern;
	}
	public String getProjField() {
		return projField;
	}
	public void setProjField(String projField) {
		this.projField = projField;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjState() {
		return projState;
	}
	public void setProjState(String projState) {
		this.projState = projState;
	}
	public String getProjRegisterDate() {
		return projRegisterDate;
	}
	public void setProjRegisterDate(String projRegisterDate) {
		this.projRegisterDate = projRegisterDate;
	}
	public String getProjRegisterer() {
		return projRegisterer;
	}
	public void setProjRegisterer(String projRegisterer) {
		this.projRegisterer = projRegisterer;
	}
	public String getProjReviseDate() {
		return projReviseDate;
	}
	public void setProjReviseDate(String projReviseDate) {
		this.projReviseDate = projReviseDate;
	}
	public String getProjReviser() {
		return projReviser;
	}
	public void setProjReviser(String projReviser) {
		this.projReviser = projReviser;
	}
	public String getProjStartDate() {
		return projStartDate;
	}
	public void setProjStartDate(String projStartDate) {
		this.projStartDate = projStartDate;
	}
	public String getProjEndDate() {
		return projEndDate;
	}
	public void setProjEndDate(String projEndDate) {
		this.projEndDate = projEndDate;
	}
	public Integer getProjExpectedTime() {
		return projExpectedTime;
	}
	public void setProjExpectedTime(Integer projExpectedTime) {
		this.projExpectedTime = projExpectedTime;
	}
	public String getProjTarget() {
		return projTarget;
	}
	public void setProjTarget(String projTarget) {
		this.projTarget = projTarget;
	}
	public String getProjPartner() {
		return projPartner;
	}
	public void setProjPartner(String projPartner) {
		this.projPartner = projPartner;
	}
	public String getProjPlan() {
		return projPlan;
	}
	public void setProjPlan(String projPlan) {
		this.projPlan = projPlan;
	}
	public String getProjRecruitStartDate() {
		return projRecruitStartDate;
	}
	public void setProjRecruitStartDate(String projRecruitStartDate) {
		this.projRecruitStartDate = projRecruitStartDate;
	}
	public String getProjRecruitEndDate() {
		return projRecruitEndDate;
	}
	public void setProjRecruitEndDate(String projRecruitEndDate) {
		this.projRecruitEndDate = projRecruitEndDate;
	}
	public String getProjDevelopSort() {
		return projDevelopSort;
	}
	public void setProjDevelopSort(String projDevelopSort) {
		this.projDevelopSort = projDevelopSort;
	}
	public int getDbNum() {
		return dbNum;
	}
	public void setDbNum(int dbNum) {
		this.dbNum = dbNum;
	}
	public String getProjLang() {
		return projLang;
	}
	public void setProjLang(String projLang) {
		this.projLang = projLang;
	}
	public String getProjFramework() {
		return projFramework;
	}
	public void setProjFramework(String projFramework) {
		this.projFramework = projFramework;
	}
	public void setExtern(boolean isExtern) {
		this.isExtern = isExtern;
	}
	
	public String getProjCompany() {
		return projCompany;
	}

	public void setProjCompany(String projCompany) {
		this.projCompany = projCompany;
	}

	@Override
	public String toString() {
		return "Project [projNum=" + projNum + ", isExtern=" + isExtern + ", projField=" + projField + ", projName="
				+ projName + ", projState=" + projState + ", projRegisterDate=" + projRegisterDate + ", projRegisterer="
				+ projRegisterer + ", projReviseDate=" + projReviseDate + ", projReviser=" + projReviser
				+ ", projStartDate=" + projStartDate + ", projEndDate=" + projEndDate + ", projExpectedTime="
				+ projExpectedTime + ", projTarget=" + projTarget + ", projPartner=" + projPartner + ", projPlan="
				+ projPlan + ", projRecruitStartDate=" + projRecruitStartDate + ", projRecruitEndDate="
				+ projRecruitEndDate + ", projDevelopSort=" + projDevelopSort + ", dbNum=" + dbNum + ", projLang="
				+ projLang + ", projFramework=" + projFramework + "]";
	}
	
	
}
