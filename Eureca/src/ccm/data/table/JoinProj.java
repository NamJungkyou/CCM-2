package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinProj extends Project
{
	/*
	 * 
	 * JNUM BIGINT NOT NULL AUTO_INCREMENT, #참여번호
  JOINDATE DATE NULL, #투입일
  EXITDATE DATE NULL, #철수일
  ROLE VARCHAR(15) NOT NULL, #역할
  FSTATE VARCHAR(15) NOT NULL, #프리랜서 상태
  APPDATE DATE NOT NULL, #신청일
  RECDATE DATE NULL, #접수일
  PNUM BIGINT NOT NULL, #프로젝트 번호
  EID VARCHAR(15) NULL, #직원 아이디
  FID VARCHAR(15) NULL, #프리랜서 아이디
	 * 
	 */
	
	  private Integer joinNum;
	   private String joinProjDate;
	   private String exitProjDate;
	   private String projRole;
	   private String freeState;
	   private String applicationDate;
	   private String joinAcceptDate;
	   private Integer projNum;
	   private String freeId;
	   
	   public Integer getJoinNum() {
	      return joinNum;
	   }
	   public void setJoinNum(Integer joinNum) {
	      this.joinNum = joinNum;
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
	   public String getProjRole() {
	      return projRole;
	   }
	   public void setProjRole(String projRole) {
	      this.projRole = projRole;
	   }
	   public String getFreeState() {
	      return freeState;
	   }
	   public void setFreeState(String freeState) {
	      this.freeState = freeState;
	   }
	   public String getApplicationDate() {
	      return applicationDate;
	   }
	   public void setApplicationDate(String applicationDate) {
	      this.applicationDate = applicationDate;
	   }
	   public String getJoinAcceptDate() {
	      return joinAcceptDate;
	   }
	   public void setJoinAcceptDate(String joinAcceptDate) {
	      this.joinAcceptDate = joinAcceptDate;
	   }
	   public int getProjNum() {
	      return projNum;
	   }
	   public void setProjNum(Integer projNum) {
	      this.projNum = projNum;
	   }
	   public String getFreeId() {
	      return freeId;
	   }
	   public void setFreeId(String freeId) {
	      this.freeId = freeId;
	   }
	   
	   @Override
	   public String toString() {
	      return "JoinProj [joinNum=" + joinNum + ", joinProjDate=" + joinProjDate + ", exitProjDate=" + exitProjDate
	            + ", projRole=" + projRole + ", freeState=" + freeState + ", applicationDate=" + applicationDate
	            + ", joinAcceptDate=" + joinAcceptDate + ", projNum=" + projNum + ", freeId=" + freeId + "]";
	   }

	   public void setParams(ResultSet rs) throws SQLException
		{
			this.joinNum = rs.getInt("joinNum");
			this.joinProjDate = rs.getString("joinProjDate");
			this.exitProjDate = rs.getString("exitProjDate");
			this.projRole = rs.getString("projRole");
			this.freeState = rs.getString("freeState");
			this.applicationDate = rs.getString("applicationDate");
			this.joinAcceptDate = rs.getString("joinAcceptDate");
			this.projNum = rs.getInt("projNum");
			this.freeId = rs.getString("freeId");
		}
		
		public void setParamsIncludeProject(ResultSet rs) throws SQLException
		{
			this.joinNum = rs.getInt("joinNum");
			this.joinProjDate = rs.getString("joinProjDate");
			this.exitProjDate = rs.getString("exitProjDate");
			this.projRole = rs.getString("projRole");
			this.freeState = rs.getString("freeState");
			this.applicationDate = rs.getString("applicationDate");
			this.joinAcceptDate = rs.getString("joinAcceptDate");
			this.projNum = rs.getInt("projNum");
			this.freeId = rs.getString("freeId");
			
			// 상속받은 프로젝트 파일의 변수들 관련
/*			this.projCompany = rs.getString("projCompany");
			this.isExtern = rs.getBoolean("isExtern");
			this.projName = rs.getString("projName");
			this.projField = rs.getString("projField");
			this.projState = rs.getString("projState");
			this.projRegisterDate = rs.getDate("projRegisterDate");
			this.projRegisterer = rs.getString("projRegisterer");
			this.projReviseDate = rs.getDate("projReviseDate");
			this.projReviser = rs.getString("projReviser");
			this.projStartDate = rs.getDate("projStartDate");
			this.projEndDate = rs.getDate("projEndDate");
			this.projExpectedTime = rs.getInt("projExpectedTime");
			this.projTarget = rs.getString("projTarget");
			this.projPartner = rs.getString("projPartner");
			this.projPlan = rs.getString("projPlan");
			this.projRecruitStartDate = rs.getDate("projRecruitStartDate");
			this.projRecruitEndDate = rs.getDate("projRecruitEndDate");
			this.projDevelopSort = rs.getString("projDevelopSort");
			this.dbNum = rs.getInt("DBNum");*/
		}
}
