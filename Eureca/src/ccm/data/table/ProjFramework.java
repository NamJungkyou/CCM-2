package ccm.data.table;

public class ProjFramework {
	private int projNum;
	private int frameNum;

	public ProjFramework(int projNum, int frameNum) {
		super();
		this.projNum = projNum;
		this.frameNum = frameNum;
	}

	public int getProjNum() {
		return projNum;
	}

	public void setProjNum(int projNum) {
		this.projNum = projNum;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	@Override
	public String toString() {
		return "ProjFramework [projNum=" + projNum + ", frameNum=" + frameNum + "]";
	}
	
}
