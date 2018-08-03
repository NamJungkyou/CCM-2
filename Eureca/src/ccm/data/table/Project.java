package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 Project 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Project {

	// 프로젝트 번호
	private int projNum;
	
	// 외부프로젝트 여부
	private boolean isExtern;
	
	// 프로젝트 분야
	private String projField;
	
	// 프로젝트 이름
	private String projName;
	
	// 프로젝트 상태
	private String projState;
	
	// 프로젝트 등록일
	private String projRegisterDate;
	
	// 프로젝트 등록일
	private String projRegisterer;
	
	// 프로젝트 수정일
	private String projReviseDate;
	
	// 프로젝트 수정자
	private String projReviser;
	
	// 시작일
	private String projStartDate;
	
	// 종료일
	private String projEndDate;
	
	// 예상기간
	private Integer projExpectedTime;
	
	// 고객사
	private String projTarget;
	
	// 협력사
	private String projPartner;
	
	// 프로젝트 계획서
	private String projPlan;
	
	// 모집시작일
	private String projRecruitStartDate;
	
	// 모집종료일
	private String projRecruitEndDate;
	
	// 개발분야
	private String projDevelopSort;
	
	// DBMS 번호
	private int dbNum;
	
	
	private String projLang;
	private String projFramework;
	private String projCompany;
	
	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
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
	
	
	/************************  게터 세터  ****************************/
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
