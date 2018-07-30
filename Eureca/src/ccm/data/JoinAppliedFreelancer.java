package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 프로젝트 참여신청한 프리랜서의 정보를 담는 클래스
 * RecommendedFreelancer 로 부터 상속받음
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class JoinAppliedFreelancer extends RecommendedFreelancer implements DTO {
	
	// 참여번호
	private int joinNum;

	/**
	 * 기본생성자
	 */
	public JoinAppliedFreelancer() {
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * 
	 * @param freeId
	 * @param freeState
	 * @param freeName
	 * @param leftJoinDays
	 * @param primaryLangs
	 * @param primaryFrames
	 * @param projCareerYears
	 * @param freeKosa
	 * @param freeScore
	 */
	public JoinAppliedFreelancer(String freeId, String freeState, String freeName, String leftJoinDays,
			String primaryLangs, String primaryFrames, int projCareerYears, String freeKosa, double freeScore) {
		
		// super 생성자를 호출함
		super(freeId, freeState, freeName, leftJoinDays, primaryLangs, primaryFrames, projCareerYears, freeKosa,
				freeScore);
	}

	/**
	 * joinNum 만 있는 생성자
	 * @param joinNum
	 */
	public JoinAppliedFreelancer(int joinNum) {
		this.joinNum = joinNum;
	}

	
	/***********************************************  게터 세터  *******************************************************/
	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}
	
	
	/**
	 * ResultSet을 가져와서 필드들을 초기화하는 메소드
	 */
	public void setParams(ResultSet rs) throws SQLException {
		
		// 부모클래스의 필드를 세팅함
		setFreeId(rs.getString("FREEID"));
		setFreeState(rs.getString("FREESTATE"));
		setFreeName(rs.getString("FREENAME"));
		setLeftJoinDays(rs.getString("LEFTJOINDAYS"));
		setPrimaryLangs(rs.getString("PRIMARYLANGS"));
		setPrimaryFrames(rs.getString("PRIMARYFRAMES"));
		setProjCareerYears(rs.getInt("PROJCAREERYEARS"));

		// rs의 freeKosa에 따라
		// 초급부터 특급까지의 등급을 문자열로 변환함
		switch (rs.getInt("freeKosa")) {
		case 1:
			setFreeKosa("초급기능사");
			break;
		case 2:
			setFreeKosa("중급기능사");
			break;
		case 3:
			setFreeKosa("고급기능사");
			break;
		case 4:
			setFreeKosa("초급기술자");
			break;
		case 5:
			setFreeKosa("중급기술자");
			break;
		case 6:
			setFreeKosa("고급기술자");
			break;
		case 7:
			setFreeKosa("특급기술자");
			break;
		default:
			setFreeKosa("등급 없음");
		}

		setFreeScore(rs.getDouble("FREESCORE"));
		joinNum = rs.getInt("JOINNUM");
	}
}
