package ccm.data.table;

/**
 * DB에 생성된 ProjFramework 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

/**
 * 사용되지 않아 폐기됨
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

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
