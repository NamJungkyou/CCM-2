package ccm.data;


/**
 * 
 * 페이지 번호와 데이터 개수에 따른
 * 전체 페이지 수를 간단하게 계산하기 위해 만든 클래스
 * 
 * 
 * @author jaehw
 *
 */

public class Page {
	// 현재 페이지 번호
	private int pageNum;
	
	// 전체 데이터 개수
	private int numOfRow;

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param pageNum
	 */
	public Page(int pageNum) {
		this.pageNum = pageNum;
	}

	/*********************************  게터 세터  *********************************/
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumOfRow() {
		return numOfRow;
	}

	public void setNumOfRow(int numOfRow) {
		this.numOfRow = numOfRow;
	}
	
	/**
	 * 전체 페이지 개수를 뽑아내기 위해 사용하는 메소드
	 * @return 전체 페이지 개수
	 */
	public int getNumOfPage() {
		// 전체 데이터를 10으로 나눠서 올림을 하면 전체 페이지 개수가 나옴
		return (int) Math.ceil(numOfRow / 10.0D);
	}

	public int getFirstPage() {
		// 현재 페이지번호를 10으로 나눈 후 소수점 아래를 버림하고 10 + 1을 곱해주면 첫페이지 번호가 나옴
		return pageNum / 10 * 10 + 1;
	}

	public int getLastPage() {
		// 첫페이지번호에 9를 더해준 값과 전체 페이지 개수 중 최솟값이 마지막 페이지 번호
		return Math.min(getFirstPage() + 9, getNumOfPage());
	}
}