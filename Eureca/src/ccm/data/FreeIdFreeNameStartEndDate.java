package ccm.data;

/**
 * 
 * 프리랜서 아이디, 이름 프로젝트 투입일, 철수일 데이터를 저장하는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class FreeIdFreeNameStartEndDate {

	// 프리랜서 아이디
	private String freeId;
	
	// 프리랜서 이름
	private String freeName;
	
	// 프로젝트 시작일
	private String startDate;
	
	// 프로젝트 철수일
	private String endDate;

	/**
	 * 기본 생성자
	 */
	public FreeIdFreeNameStartEndDate() {
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param freeId
	 * @param freeName
	 * @param startDate
	 * @param endDate
	 */
	public FreeIdFreeNameStartEndDate(String freeId, String freeName, String startDate, String endDate) {
		this.freeId = freeId;
		this.freeName = freeName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/****************************************************  게터 세터  ***************************************************************/
	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public String getFreeName() {
		return freeName;
	}

	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
