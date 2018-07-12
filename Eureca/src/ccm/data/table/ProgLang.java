package ccm.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgLang {
	private int langNum;
	private String langName;

	public ProgLang() {
		super();
	}

	public ProgLang(int langNum, String langName) {
		super();
		this.langNum = langNum;
		this.langName = langName;
	}

	public void setParams(ResultSet rs) throws SQLException {
		this.langNum = rs.getInt("LANGNUM");
		this.langName = rs.getString("LANGNAME");
	}

	public int getLangNum() {
		return langNum;
	}

	public void setLangNum(int langNum) {
		this.langNum = langNum;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	@Override
	public String toString() {
		return "ProgLang [langNum=" + langNum + ", langName=" + langName + "]";
	}

}
