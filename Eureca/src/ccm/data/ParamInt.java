package ccm.data;

public class ParamInt //메소드 파라미터에 참조변수로 넣을 수 있는 정수
{
	private int intValue;
	private int rowCount;
	private int pageCount;
	private int firstPage;
	private int lastPage;
	
	public ParamInt(int intValue)
	{
		this.intValue = intValue;
	}
	
	public ParamInt()
	{
		
	}

	public int getIntValue() { return intValue; }
	public void setIntValue(int intValue) { this.intValue = intValue; }
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

	@Override
	public String toString() {
		return "ParamInt [rowCount=" + rowCount + ", pageCount=" + pageCount + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + "]";
	}

}
