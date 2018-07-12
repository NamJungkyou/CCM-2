package ccm.data.table;

public class LangSkill
{
	/*
	LSNUM BIGINT NOT NULL AUTO_INCREMENT, #언어스킬 번호
  JNUM BIGINT NOT NULL, #참여 번호
  LNUM BIGINT NOT NULL, #프로그래밍 언어 번호
	*/
	
	private int lsNum;
	private int jNum;
	private int lNum;
	
	public int getLsNum() { return lsNum; }
	public void setLsNum(int lsNum) { this.lsNum = lsNum; }
	public int getjNum() { return jNum; }
	public void setjNum(int jNum) { this.jNum = jNum; }
	public int getlNum() { return lNum; }
	public void setlNum(int lNum) { this.lNum = lNum; }
}
