package ccm.data;

public class FreeIdFreeNameStartEndDate
{
	private String freeId;
	private String freeName;
	private String startDate;
	private String endDate;
	
	public FreeIdFreeNameStartEndDate() { super(); }
	public FreeIdFreeNameStartEndDate(String freeId, String freeName, String startDate, String endDate)
	{
		super();
		this.freeId = freeId;
		this.freeName = freeName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getFreeId() { return freeId; }
	public void setFreeId(String freeId) { this.freeId = freeId; }
	public String getFreeName() { return freeName; }
	public void setFreeName(String freeName) { this.freeName = freeName; }
	public String getStartDate() { return startDate; }
	public void setStartDate(String startDate) { this.startDate = startDate; }
	public String getEndDate() { return endDate; }
	public void setEndDate(String endDate) { this.endDate = endDate; }
}
