package ccm.data;

import ccm.data.table.Freelancer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * 프리랜서 검색에서 프로필을 표시할 수 있는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class FreeProfileOfSearch extends Freelancer implements DTO {

	// 프리랜서 채용 상태
	private String freeState;
	
	// 학력 리스트
	private ArrayList<EduForProfile> edus;
	
	// 근무경력 리스트
	private ArrayList<CareerForProfile> cars;
	
	// 스킬인벤토리 리스트
	private ArrayList<SkillInventorySearch> skills;
	
	// 프로젝트 참여기록 리스트
	private ArrayList<ListOfProjJoinedRecord> joinedRecords;
	
	// 참여기록 페이지 정보
	private Page joinedRecordsPage;
	
	// 참여신청한 프로젝트 리스트
	private ArrayList<ListOfJoinAppliedList> joinAppliedList;
	
	// 참여신청 프로젝트 페이지 정보
	private Page joinAppliedListPage;

	/**
	 * 기본생성자
	 */
	public FreeProfileOfSearch() {
		edus = new ArrayList();
		cars = new ArrayList();
		skills = new ArrayList();
		joinedRecords = new ArrayList();
		joinedRecordsPage = new Page(0);
		joinAppliedList = new ArrayList();
		joinAppliedListPage = new Page(0);
	}

	/*************************************  게터 세터  ***********************************************/
	public String getFreeState() {
		return freeState;
	}

	public void setFreeState(String freeState) {
		this.freeState = freeState;
	}

	public ArrayList<EduForProfile> getEdus() {
		return edus;
	}

	public void setEdus(ArrayList<EduForProfile> edus) {
		this.edus = edus;
	}

	public ArrayList<CareerForProfile> getCars() {
		return cars;
	}

	public void setCars(ArrayList<CareerForProfile> cars) {
		this.cars = cars;
	}

	public ArrayList<SkillInventorySearch> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<SkillInventorySearch> skills) {
		this.skills = skills;
	}

	public ArrayList<ListOfProjJoinedRecord> getJoinedRecords() {
		return joinedRecords;
	}

	public void setJoinedRecords(ArrayList<ListOfProjJoinedRecord> joinedRecords) {
		this.joinedRecords = joinedRecords;
	}

	public Page getJoinedRecordsPage() {
		return joinedRecordsPage;
	}

	public void setJoinedRecordsPage(Page joinedRecordsPage) {
		this.joinedRecordsPage = joinedRecordsPage;
	}

	public ArrayList<ListOfJoinAppliedList> getJoinAppliedList() {
		return joinAppliedList;
	}

	public void setJoinAppliedList(ArrayList<ListOfJoinAppliedList> joinAppliedList) {
		this.joinAppliedList = joinAppliedList;
	}

	public Page getJoinAppliedListPage() {
		return joinAppliedListPage;
	}

	public void setJoinAppliedListPage(Page joinAppliedListPage) {
		this.joinAppliedListPage = joinAppliedListPage;
	}

	/**
	 * 부모클래스인 Freelancer의 setParams를 같이 호출함
	 * 프리랜서 기본정보를 세팅해줌
	 */
	public void setParams(ResultSet rs) throws SQLException {
		super.setParams(rs);
		freeState = rs.getString("FREESTATE");
	}
}