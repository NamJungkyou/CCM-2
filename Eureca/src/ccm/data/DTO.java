package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * SQL 쿼리문 실행 후 객체에 값을 저장할 때
 * DAO에서의 가독성을 높임과 동시에
 * 클래스 객체 데이터 초기화를 편하게 해주는 인터페이스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public abstract interface DTO {
	/**
	 * DAO에서 쿼리 실행 후 ResultSet 객체로 받는
	 * 변수들을 이 클래스 객체의 변수들에 세팅하는 메소드
	 * 
	 * @param paramResultSet
	 * @throws SQLException
	 */
	public abstract void setParams(ResultSet paramResultSet) throws SQLException;
}