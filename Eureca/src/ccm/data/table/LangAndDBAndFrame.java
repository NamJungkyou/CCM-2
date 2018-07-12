package ccm.data.table;

import java.util.ArrayList;

import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;

public class LangAndDBAndFrame {
	private ArrayList<DBMS> dbms;
	private ArrayList<Framework> frame;
	private ArrayList<ProgLang> lang;

	public LangAndDBAndFrame() {
		dbms = new ArrayList<DBMS>();
		frame = new ArrayList<Framework>();
		lang = new ArrayList<ProgLang>();
	}

	public LangAndDBAndFrame(ArrayList<DBMS> dbms, ArrayList<Framework> frame, ArrayList<ProgLang> lang) {
		super();
		this.dbms = dbms;
		this.frame = frame;
		this.lang = lang;
	}

	public ArrayList<DBMS> getDbms() {
		return dbms;
	}

	public void setDbms(ArrayList<DBMS> dbms) {
		this.dbms = dbms;
	}

	public ArrayList<Framework> getFrame() {
		return frame;
	}

	public void setFrame(ArrayList<Framework> frame) {
		this.frame = frame;
	}

	public ArrayList<ProgLang> getLang() {
		return lang;
	}

	public void setLang(ArrayList<ProgLang> lang) {
		this.lang = lang;
	}
}