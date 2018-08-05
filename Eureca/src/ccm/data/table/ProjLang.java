package ccm.data.table;

/**
 * DB에 생성된 ProjLang 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

/**
 * 사용되지 않아 폐기됨
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ProjLang {
	/*
	 * projNum BIGINT NOT NULL, #프로젝트 번호 langNum BIGINT NOT NULL, #프로그래밍 언어 번호
	 */

	private int projNum;
	private int langNum;

	/*public ProjLang(int projNum, int langNum) {
		super();
		this.projNum = projNum;
		this.langNum = langNum;
	}*/

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}

	public int getLangNum() {
		return langNum;
	}

	public void setLangNum(int langNum) {
		this.langNum = langNum;
	}

	@Override
	public String toString() {
		return "ProjLang [projNum=" + projNum + ", langNum=" + langNum + "]";
	}
	
}
