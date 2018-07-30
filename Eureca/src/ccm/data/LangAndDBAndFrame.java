package ccm.data;

import java.util.ArrayList;

import ccm.data.table.DBMS;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;

/**
 * 
 * 프로그래밍 언어, 프레임워크, DBMS 정보를
 * 통으로 묶어서 UI단에 보내기 위해 만든 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class LangAndDBAndFrame {
	
	// DBMS 리스트
	private ArrayList<DBMS> dbms;
	
	// 프레임워크 리스트
	private ArrayList<Framework> frame;
	
	// 프로그래밍 언어 리스트
	private ArrayList<ProgLang> lang;

	/**
	 * 기본 생성자
	 */
	public LangAndDBAndFrame() {
		dbms = new ArrayList<DBMS>();
		frame = new ArrayList<Framework>();
		lang = new ArrayList<ProgLang>();
	}

	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param dbms
	 * @param frame
	 * @param lang
	 */
	public LangAndDBAndFrame(ArrayList<DBMS> dbms, ArrayList<Framework> frame, ArrayList<ProgLang> lang) {
		super();
		this.dbms = dbms;
		this.frame = frame;
		this.lang = lang;
	}

	/******************************************  게터 세터  **********************************************/
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
