package ccm.data;

public class FreeIdToPutInAndProjNum
{
	private String freeId;
	private String freeName;
	private int projNum;
	
	public FreeIdToPutInAndProjNum() { super(); }
	public FreeIdToPutInAndProjNum(String freeId, int projNum)
	{
		super();
		this.freeId = freeId;
		this.projNum = projNum;
	}
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public int getProjNum() { return projNum; }
	public void setProjNum(int projNum) { this.projNum = projNum; }
}
