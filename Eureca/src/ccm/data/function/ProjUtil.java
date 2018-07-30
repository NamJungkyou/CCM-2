package ccm.data.function;

/**
 * 프로젝트에 관련된 유틸리티 메소드를 모아놓은 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjUtil {
	public ProjUtil() {
	}

	private static ProjUtil instance = new ProjUtil();

	public static ProjUtil getInstance() {
		return instance;
	}

	/**
	 * 날짜와 시간 문자열에서
	 * 시간 부분만 잘라내는 메소드
	 * 
	 * @param s 날짜 시간 문자열데이터
	 * @param pos 지울 문자열 인덱스
	 * @return 날짜 문자열 데이터
	 */
	public String removeCharAt(String s, int pos) {
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	/**
	 * 추천 프리랜서 정렬 코드를
	 * 정렬 SQL 구문으로 바꾸는 메소드
	 * 
	 * @param code 정렬 코드
	 * @return 문자열 SQL 구문
	 */
	public String codeToRecommSortOption(int code) {
		switch (code) {
		case 1:
			return "FREEKOSA DESC"; // 코사등급
		case 2:
			return "FREESCORE DESC"; // 평점
		case 3:
			return "PROGLANGSCORE DESC"; // 언어 사용횟수
		case 4:
			return "FRAMEWORKSCORE DESC"; // 프레임워크 사용횟수
		case 5:
			return "NUMOFEDUS DESC"; // 교육기관 이수의 총합
		case 6:
			return "PROJCAREERDAYS DESC"; // 근속년수
		case 7:
			return "LEFTJOINDAYS DESC"; // 남은기간
		}
		return "PROGLANGSCORE DESC"; // 기본값으로 언어 사용횟수를 반환
	}

	/**
	 * 추천프리랜서 정렬옵션을 다시 코드로 바꾸는 메소드
	 * 
	 * @param option 정렬 SQL 구문
	 * @return 정렬 코드
	 */
	public int recommSortOptionToCode(String option) {
		if (option.equals("FREEKOSA DESC"))
			return 1;
		if (option.equals("FREESCORE DESC"))
			return 2;
		if (option.equals("PROGLANGSCORE DESC"))
			return 3;
		if (option.equals("FRAMEWORKSCORE DESC"))
			return 4;
		if (option.equals("NUMOFEDUS DESC"))
			return 5;
		if (option.equals("PROJCAREERDAYS DESC"))
			return 6;
		if (option.equals("LEFTJOINDAYS DESC"))
			return 7;
		return 1;
	}

	/**
	 * 참여신청한 프리랜서 정렬코드를 SQL구문으로 바꿔줌
	 * @param code
	 * @return
	 */
	public String codeToAppliedSortOption(int code) {
		switch (code) {
		case 1:
			return "order by joinnum desc"; // 참여번호(최신순)
		case 2:
			return "order by waitingorder asc"; // 참여자순
		case 3:
			return "order by waitingorder desc"; // 대기자순
		}
		return "order by joinnum desc";
	}

	/**
	 * 참여신청한 프리랜서 목록 정렬 SQL 구문을
	 * 다시 코드로 변환하는 메소드
	 * 
	 * @param option
	 * @return
	 */
	public String appliedSortOptionToCode(String option) {
		if (option.equals("order by joinnum desc"))
			return "1";
		if (option.equals("order by waitingorder asc"))
			return "2";
		if (option.equals("order by waitingorder desc"))
			return "3";
		return "1";
	}

	/**
	 * 참여신청한 프로젝트 목록 정렬 SQL 구문을
	 * 정렬 코드로 바꿔줌
	 * 
	 * @param option
	 * @return
	 */
	public int projJoinAcceptListOptionToCode(String option) {
		if (option.equals("APPLICATIONDATE ASC")) // 참여신청날짜
			return 1;
		if (option.equals("PROJRECRUITENDDATE ASC")) // 모집마감일
			return 2;
		if (option.equals("PROJNAME ASC")) //프로젝트 이름
			return 3;
		return 4;
	}

	/**
	 * 참여신청한 프로젝트 목록 정렬코드를
	 * 다시 SQL구문으로 바꿔줌
	 * 
	 * @param code
	 * @return
	 */
	public String projJoinAcceptListCodeToOption(int code) {
		switch (code) {
		case 1:
			return "APPLICATIONDATE ASC";
		case 2:
			return "PROJRECRUITENDDATE ASC";
		case 3:
			return "PROJNAME ASC";
		}
		return "FREENAME ASC";
	}

	/**
	 * 프리랜서 검색 페이지에서
	 * 프리랜서 목록 정렬 코드를
	 * SQL 구문으로 바꿔줌
	 * 
	 * @param code
	 * @return
	 */
	public String freelancerListToSearchCodeToOption(int code) {
		switch (code) {
		case 1:
			return "order by f.FREEJOINDATE desc"; // 투입일순
		case 2:
			return "order by f.jobstate desc"; // 구직중
		}
		return "order by f.jobstate asc"; // 참여중
	}

	/**
	 * 참여신청한 프로젝트 목록의 정렬코드를
	 * SQL 구문으로 바꿔줌
	 * 
	 * @param code
	 * @return
	 */
	public String joinAppliedListSortCodeToOption(int code) {
		switch (code) {
		case 1:
			return "ORDER BY JOINPROJDATE DESC"; // 투입일순
		case 2:
			return "ORDER BY REQUIRECNT"; // 참여자순
		}
		return "ORDER BY EXITPROJDATE"; // 종료일순
	}

	/**
	 * 프로젝트 참여기록 목록 정렬코드를
	 * SQL 구문으로 바꿔줌
	 * 
	 * @param code
	 * @return
	 */
	public String joinedRecordSortCodeToOption(int code) {
		switch (code) {
		case 1:
			return "ORDER BY APPLICATIONDATE DESC"; // 참여신청일순
		case 2:
			return "ORDER BY REQUIRECNT"; // 참여자순
		}
		return "ORDER BY PROJRECRUITENDDATE"; // 모집마감일순
	}
}