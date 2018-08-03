package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 생성된 ProgLang 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProgLang {
	
	// 언어 번호
	private int langNum;
	
	// 언어 이름
	private String langName;

	/**
	 * 기본 생성자
	 */
	public ProgLang() {
		super();
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param langNum
	 * @param langName
	 */
	public ProgLang(int langNum, String langName) {
		super();
		this.langNum = langNum;
		this.langName = langName;
	}

	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public void setParams(ResultSet rs) throws SQLException {
		this.langNum = rs.getInt("LANGNUM");
		this.langName = rs.getString("LANGNAME");
	}

	/*************************  게터 세터  **************************/
	public int getLangNum() {
		return langNum;
	}

	public void setLangNum(int langNum) {
		this.langNum = langNum;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	@Override
	public String toString() {
		return "ProgLang [langNum=" + langNum + ", langName=" + langName + "]";
	}

}
