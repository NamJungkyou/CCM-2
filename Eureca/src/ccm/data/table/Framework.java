package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Framework {
	private int frameNum;
	private String frameName;
	private String frameDevelopField;

	public Framework() {
		super();
	}

	public Framework(int frameNum, String frameName, String frameDevelopField) {
		super();
		this.frameNum = frameNum;
		this.frameName = frameName;
		this.frameDevelopField = frameDevelopField;
	}

	public void setParams(ResultSet rs) throws SQLException {
		this.frameNum = rs.getInt("FRAMENUM");
		this.frameName = rs.getString("FRAMENAME");
		this.frameDevelopField = rs.getString("FRAMEDEVELOPFIELD");
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getFrameDevelopField() {
		return frameDevelopField;
	}

	public void setFrameDevelopField(String frameDevelopField) {
		this.frameDevelopField = frameDevelopField;
	}

	@Override
	public String toString() {
		return "Framework [frameNum=" + frameNum + ", frameName=" + frameName + ", frameDevelopField="
				+ frameDevelopField + "]";
	}
	
}
