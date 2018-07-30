package ccm.data;

/**
 * 
 * 원래 DAO에서 int 타입의 파라미터를
 * 
 * 값을 세팅해서 메소드 밖으로 내보낼 수 없어서
 * 
 * 만든 메소드이다
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class ParamInt // 메소드 파라미터에 참조변수로 넣을 수 있는 정수
{
	// int형 값을 집어넣는 변수
	private int intValue;

	// 행의 개수
	private int rowCount;

	// 페이지 개수
	private int pageCount;

	// 첫번째 페이지번호
	private int firstPage;

	// 마지막 페이지번호
	private int lastPage;

	public ParamInt(int intValue) {
		this.intValue = intValue;
	}

	/**
	 * 기본 생성자
	 */
	public ParamInt() {

	}

	
	/******************************************  게터 세터  **************************************************/
	
	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * 문자열로 나타내는 메소드
	 */
	@Override
	public String toString() {
		return "ParamInt [rowCount=" + rowCount + ", pageCount=" + pageCount + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + "]";
	}

}
