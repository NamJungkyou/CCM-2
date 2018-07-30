package ccm.data;

/**
 * 
 * 프리랜서 아이디, 이름, 프로젝트번호 데이터를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */
public class FreeIdToPutInAndProjNum {
	
	// 프리랜서 아이디
	private String freeId;
	
	// 프리랜서 이름
	private String freeName;
	
	// 해당 프로젝트 번호
	private int projNum;

	/**
	 * 기본 생성자
	 */
	public FreeIdToPutInAndProjNum() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param freeId
	 * @param projNum
	 */
	public FreeIdToPutInAndProjNum(String freeId, int projNum) {
		this.freeId = freeId;
		this.projNum = projNum;
	}

	/***********************************  게터 세터  ******************************************/
	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}
}