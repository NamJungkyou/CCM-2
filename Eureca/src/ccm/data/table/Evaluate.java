package ccm.data.table;

public class Evaluate
{
	/*
	EVNUM BIGINT NOT NULL AUTO_INCREMENT, #평가 번호
  JNUM BIGINT NULL, #참여 번호
  VALUER VARCHAR(15) NULL, #평가자
  VALUEE VARCHAR(15) NULL, #피평가자
  SCORE INT NOT NULL, #평가점수
	*/
	
	private int EvNum;
	private int jNum;
	private String valuer;
	private String valuee;
	private int score;
	
	public Evaluate() { super(); }
	public Evaluate(int evNum, int jNum, String valuer, String valuee, int score)
	{
		super();
		EvNum = evNum;
		this.jNum = jNum;
		this.valuer = valuer;
		this.valuee = valuee;
		this.score = score;
	}
	
	public int getEvNum() { return EvNum; }
	public void setEvNum(int evNum) { EvNum = evNum; }
	public int getjNum() { return jNum; }
	public void setjNum(int jNum) { this.jNum = jNum; }
	public String getValuer() { return valuer; }
	public void setValuer(String valuer) { this.valuer = valuer; }
	public String getValuee() { return valuee; }
	public void setValuee(String valuee) { this.valuee = valuee; }
	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }
}
