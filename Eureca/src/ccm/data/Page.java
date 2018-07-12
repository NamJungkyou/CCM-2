package ccm.data;

public class Page
{
	private int pageNum;
	private int numOfRow;
	
	public Page(int pageNum) { this.pageNum = pageNum; }
	
	public int getPageNum() { return this.pageNum; }
	public void setPageNum(int pageNum) { this.pageNum = pageNum; }
	public int getNumOfRow() { return this.numOfRow; }
	public void setNumOfRow(int numOfRow) { this.numOfRow = numOfRow; }
	
	public int getNumOfPage() { return (int)(Math.ceil((double)this.numOfRow / 10)); }
	public int getFirstPage() { return (int)((this.pageNum / 10) * 10) + 1; }
	public int getLastPage() { return (int)Math.min(this.getFirstPage(), this.getNumOfPage()); }
}
