package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * DB에 생성된 Freelancer 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Freelancer {
	
	// 프리랜서 아이디
	private String freeId;
	
	// 패스워드
	private String freePw;
	
	// 이름
	private String freeName;
	
	// 사진
	private String freePic;
	
	// 생년월일
	private String freeBirth;
	
	// 성별
	private Boolean freeSex;
	
	// 전화번호
	private String freePhone;
	
	// 이메일
	private String freeEmail;
	
	// 등록일
	private Timestamp freeJoinDate;
	
	// 탈퇴일
	private Timestamp freeDropDate;
	
	// 코사등급
	private Integer freeKosa;
	
	// 권한
	private Integer freeClass;
	
	// 결혼여부
	private Boolean freeMarried;
	
	// 앞 주소
	private String freeFrontAddr;
	
	// 나머지주소
	private String freeRearAddr;
	
	// 계좌 은행
	private String freeBank;
	
	// 계좌명의
	private String freeAccName;
	
	// 계좌번호
	private String freeAccount;
	
	// 수정자
	private String freeReviser;
	
	// 수정일
	private Timestamp freeReviseDate;
	
	// 평가점수
	private double freeScore;
	
	// 파일경로
	private String freeFilePath;
	
	
	
	/*******************************  게터 세터  ********************************/
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
	
	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
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




