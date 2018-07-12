package ccm.data.function;

import java.util.ArrayList;

import ccm.data.table.Freelancer;

/***************************
 * 
 * 
 * 프리랜서 관련된거 여러 기능들 정의하는 클래스
 * DAO랑 다르게 쿼리로 계산하기 힘든걸 처리함
 * 
 * 작성자 : 
 * 
 * 수정자 : 
 * 
 * 수정일 : 
 *
 *
 ***************************/

public class FreeUtil
{
	private static FreeUtil instance = new FreeUtil();
	protected FreeUtil() { super(); }
	public static FreeUtil getInstance() { return instance; }
	
	/*예시로 함수를 만들어봄*/
	public ArrayList<Freelancer> getFoo(ArrayList<Freelancer> input)
	{
		ArrayList<Freelancer> foo = input;
		
		for(Freelancer fl : foo)
		{
			//그 어떠한 알고리즘 실행
		}
		
		return foo;
	}
}
