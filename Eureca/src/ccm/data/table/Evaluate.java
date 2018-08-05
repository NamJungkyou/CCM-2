package ccm.data.table;

/**
 * DB에 생성된 Evaluate 테이블의 프로퍼티를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class Evaluate
{
	// 평가 번호
	private int EvNum;
	
	// 참여 번호
	private int jNum;
	
	// 평가자
	private String valuer;
	
	// 피평가자
	private String valuee;
	
	// 점수
	private int score;
	
	/**
	 * 기본 생성자
	 */
	public Evaluate() { super(); }
	
	/**
	 * 
	 * 매개변수가 있는 생성자
	 * 
	 * @param evNum
	 * @param jNum
	 * @param valuer
	 * @param valuee
	 * @param score
	 */
	public Evaluate(int evNum, int jNum, String valuer, String valuee, int score)
	{
		super();
		EvNum = evNum;
		this.jNum = jNum;
		this.valuer = valuer;
		this.valuee = valuee;
		this.score = score;
	}
	
	/*****************************  게터 세터  *********************************/
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
