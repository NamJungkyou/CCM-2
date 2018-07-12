package ccm.data.table;

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
