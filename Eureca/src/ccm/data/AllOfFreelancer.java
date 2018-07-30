package ccm.data;

import ccm.data.table.Career;
import ccm.data.table.Education;
import ccm.data.table.Freelancer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 프리랜서의 모든 정보를 담는 클래스
 * Freelancer 클래스로부터 상속받음
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class AllOfFreelancer extends Freelancer implements DTO {
	
	// 근무 경력 리스트
	private ArrayList<Career> careers;
	
	// 학력 리스트
	private ArrayList<Education> edus;

	// 기본 생성자
	public AllOfFreelancer() {
		careers = new ArrayList();
		edus = new ArrayList();
	}

	// 매개변수가 있는 생성자
	public AllOfFreelancer(ArrayList<Career> careers, ArrayList<Education> edus) {
		this.careers = careers;
		this.edus = edus;
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		setFreeId(rs.getString("FREEID"));
		setFreePw(rs.getString("FREEPW"));
		setFreeName(rs.getString("FREENAME"));
		setFreePic(rs.getString("FREEPIC"));
		setFreeBirth(rs.getString("FREEBIRTH"));
		setFreeSex(Boolean.valueOf(rs.getBoolean("FREESEX")));
		setFreePhone(rs.getString("FREEPHONE"));
		setFreeEmail(rs.getString("FREEEMAIL"));
		setFreeJoinDate(rs.getTimestamp("FREEJOINDATE"));
		setFreeDropDate(rs.getTimestamp("FREEDROPDATE"));
		setFreeClass(Integer.valueOf(rs.getInt("FREECLASS")));
		setFreeKosa(Integer.valueOf(rs.getInt("FREEKOSA")));
		setFreeMarried(Boolean.valueOf(rs.getBoolean("FREEMARRIED")));
		setFreeFrontAddr(rs.getString("FREEFRONTADDR"));
		setFreeRearAddr(rs.getString("FREEREARADDR"));
		setFreeBank(rs.getString("FREEBANK"));
		setFreeAccName(rs.getString("FREEACCNAME"));
		setFreeAccount(rs.getString("FREEACCOUNT"));
		setFreeReviser(rs.getString("FREEREVISER"));
		setFreeReviseDate(rs.getTimestamp("FREEREVISEDATE"));
		setfreeFilePath(rs.getString("FREEFILEPATH"));
		setFreeScore(rs.getDouble("FREESCORE"));
	}

	
	//*********************************** 게터 세터 **************************************************
	public ArrayList<Career> getCareers() {
		return careers;
	}

	public void setCareers(ArrayList<Career> careers) {
		this.careers = careers;
	}

	public ArrayList<Education> getEdus() {
		return edus;
	}

	public void setEdus(ArrayList<Education> edus) {
		this.edus = edus;
	}
}