package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/***************************
 * 
 * 
 * 프리랜서 기본정보 int형은 nullable이어야되니까 Integer 자료형으로 쓰도록
 * 
 * 작성자 :
 * 
 * 수정자 :
 * 
 * 수정일 :
 *
 *
 ***************************/

public class Freelancer {
/*	  FREEID VARCHAR(15) NOT NULL, #아이디
	  FREEPW VARCHAR(100) NOT NULL, #비번
	  FREENAME VARCHAR(10) NULL, #이름
	  FREEPIC MEDIUMBLOB NULL, #사진
	  FREEBIRTH DATETIME NULL, #생년월일
	  FREESEX BOOLEAN NULL, #성별
	  FREEPHONE VARCHAR(11) NULL, #폰번
	  FREEEMAIL VARCHAR(50) NOT NULL, #이메일
	  FREEJOINDATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(), #가입일
	  FREEDROPDATE DATETIME NULL, #탈퇴일
	  FREEKOSA INT NULL, #코사등급(개발자 등급)
	  FREECLASS INT(1) NULL, #등급속성(회원 등급)
	  FREEMARRIED BOOLEAN NULL, #결혼여부
	  FREEFRONTADDR VARCHAR(30) NULL, #본주소 *************
	  FREEREARADDR VARCHAR(30) NULL, #나머지주소 *************
	  FREEBANK VARCHAR(15) NULL, #계좌은행
	  FREEACCNAME VARCHAR(12) NULL, #계좌명의
	  FREEACCOUNT VARCHAR(30) NULL, #계좌번호
	  FREEREVISER VARCHAR(15) NULL, #수정인
	  FREEREVISEDATE DATETIME NULL, #수정일 */
	private String freeId;
	private String freePw;
	private String freeName;
	private String freePic;
	private String freeBirth;
	private Boolean freeSex;
	private String freePhone;
	private String freeEmail;
	private Timestamp freeJoinDate;
	private Timestamp freeDropDate;
	private Integer freeKosa;
	private Integer freeClass;
	private Boolean freeMarried;
	private String freeFrontAddr;
	private String freeRearAddr;
	private String freeBank;
	private String freeAccName;
	private String freeAccount;
	private String freeReviser;
	private Timestamp freeReviseDate;
	private double freeScore;
	private String freeFilePath;
	
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getFreePw() {
		return freePw;
	}
	public void setFreePw(String freePw) {
		this.freePw = freePw;
	}
	public String getFreeName() {
		return freeName;
	}
	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}
	public String getFreePic() {
		return freePic;
	}
	public void setFreePic(String freePic) {
		this.freePic = freePic;
	}
	public String getFreeBirth() {
		return freeBirth;
	}
	public void setFreeBirth(String freeBirth) {
		this.freeBirth = freeBirth;
	}
	public Boolean getFreeSex() {
		return freeSex;
	}
	public void setFreeSex(Boolean freeSex) {
		this.freeSex = freeSex;
	}
	public String getFreePhone() {
		return freePhone;
	}
	public void setFreePhone(String freePhone) {
		this.freePhone = freePhone;
	}
	public String getFreeEmail() {
		return freeEmail;
	}
	public void setFreeEmail(String freeEmail) {
		this.freeEmail = freeEmail;
	}
	public Timestamp getFreeJoinDate() {
		return freeJoinDate;
	}
	public void setFreeJoinDate(Timestamp freeJoinDate) {
		this.freeJoinDate = freeJoinDate;
	}
	public Timestamp getFreeDropDate() {
		return freeDropDate;
	}
	public void setFreeDropDate(Timestamp freeDropDate) {
		this.freeDropDate = freeDropDate;
	}
	public Integer getFreeKosa() {
		return freeKosa;
	}
	public void setFreeKosa(Integer freeKosa) {
		this.freeKosa = freeKosa;
	}
	public Integer getFreeClass() {
		return freeClass;
	}
	public void setFreeClass(Integer freeClass) {
		this.freeClass = freeClass;
	}
	public Boolean getFreeMarried() {
		return freeMarried;
	}
	public void setFreeMarried(Boolean freeMarried) {
		this.freeMarried = freeMarried;
	}
	public String getFreeFrontAddr() {
		return freeFrontAddr;
	}
	public void setFreeFrontAddr(String freeFrontAddr) {
		this.freeFrontAddr = freeFrontAddr;
	}
	public String getFreeRearAddr() {
		return freeRearAddr;
	}
	public void setFreeRearAddr(String freeRearAddr) {
		this.freeRearAddr = freeRearAddr;
	}
	public String getFreeBank() {
		return freeBank;
	}
	public void setFreeBank(String freeBank) {
		this.freeBank = freeBank;
	}
	public String getFreeAccName() {
		return freeAccName;
	}
	public void setFreeAccName(String freeAccName) {
		this.freeAccName = freeAccName;
	}
	public String getFreeAccount() {
		return freeAccount;
	}
	public void setFreeAccount(String freeAccount) {
		this.freeAccount = freeAccount;
	}
	public String getFreeReviser() {
		return freeReviser;
	}
	public void setFreeReviser(String freeReviser) {
		this.freeReviser = freeReviser;
	}
	public Timestamp getFreeReviseDate() {
		return freeReviseDate;
	}
	public void setFreeReviseDate(Timestamp freeReviseDate) {
		this.freeReviseDate = freeReviseDate;
	}
	public double getFreeScore() {
		return freeScore;
	}
	public void setFreeScore(double freeScore) {
		this.freeScore = freeScore;
	}
	public String getfreeFilePath() {
		return freeFilePath;
	}
	public void setfreeFilePath(String freeFilePath) {
		this.freeFilePath = freeFilePath;
	}
	
	public void setParams(ResultSet rs) throws SQLException
	   {
	      this.freeId = rs.getString("FREEID");
	      this.freePw = rs.getString("FREEPW");
	      this.freeName = rs.getString("FREENAME");
	      this.freePic = rs.getString("FREEPIC");
	      this.freeBirth = rs.getString("FREEBIRTH");
	      this.freeSex = rs.getBoolean("FREESEX");
	      this.freePhone = rs.getString("FREEPHONE");
	      this.freeEmail = rs.getString("FREEEMAIL");
	      this.freeJoinDate = rs.getTimestamp("FREEJOINDATE");
	      this.freeDropDate = rs.getTimestamp("FREEDROPDATE");
	      this.freeClass = rs.getInt("FREECLASS");
	      this.freeKosa = rs.getInt("FREEKOSA");
	      this.freeMarried = rs.getBoolean("FREEMARRIED");
	      this.freeFrontAddr = rs.getString("FREEFRONTADDR");
	      this.freeRearAddr = rs.getString("FREEREARADDR");
	      this.freeBank = rs.getString("FREEBANK");
	      this.freeAccName = rs.getString("FREEACCNAME");
	      this.freeAccount = rs.getString("FREEACCOUNT");
	      this.freeReviser = rs.getString("FREEREVISER");
	      this.freeReviseDate = rs.getTimestamp("FREEREVISEDATE");
	      this.freeFilePath = rs.getString("FREEFILEPATH");
	      this.freeScore=rs.getDouble("FREESCORE");
	   }
	@Override
	public String toString() {
		return "Freelancer [freeId=" + freeId + ", freePw=" + freePw + ", freeName=" + freeName + ", freePic=" + freePic
				+ ", freeBirth=" + freeBirth + ", freeSex=" + freeSex + ", freePhone=" + freePhone + ", freeEmail="
				+ freeEmail + ", freeJoinDate=" + freeJoinDate + ", freeDropDate=" + freeDropDate + ", freeKosa="
				+ freeKosa + ", freeClass=" + freeClass + ", freeMarried=" + freeMarried + ", freeFrontAddr="
				+ freeFrontAddr + ", freeRearAddr=" + freeRearAddr + ", freeBank=" + freeBank + ", freeAccName="
				+ freeAccName + ", freeAccount=" + freeAccount + ", freeReviser=" + freeReviser + ", freeReviseDate="
				+ freeReviseDate + ", freeScore=" + freeScore + ", freeFilePath" + freeFilePath + "]";
	}
	
}




