package ccm.data;

import ccm.data.table.DBMS;
import ccm.data.table.Employee;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjRole;
import ccm.data.table.Project;
import java.util.ArrayList;

/**
 * 유클리드소프트 프로젝트 정보를 모두 담는 클래스
 * 
 * @author 글로벌 IT 경영 진재환
 *
 */

public class AllOfEuclidProject extends Project {
	// 등록자 인적사항
	private Employee projRegistererInfo;
	
	// 수정자 인적사항
	private Employee projReviserInfo;
	
	// 프로젝트 사용 DBMS
	private DBMS projDBMSInfo;
	
	// 프로젝트 사용 프레임워크 리스트
	private ArrayList<Framework> projFrameworks;
	
	// 프로젝트 사용 프로그래밍 언어 리스트
	private ArrayList<ProgLang> projProgLangs;
	
	// 프로젝트 역할 별 채용현황 리스트
	private ArrayList<ProjRole> projRequireRoles;
	
	// 프로젝트에 참여한 프리랜서 리스트
	private ArrayList<ProjJoinedFreelancer> projJoinedFree;

	/**
	 * 생성자
	 */
	public AllOfEuclidProject() {
		projRegistererInfo = new Employee();
		projReviserInfo = new Employee();
		projDBMSInfo = new DBMS();
		projRequireRoles = new ArrayList();
		projFrameworks = new ArrayList();
		projProgLangs = new ArrayList();
		projJoinedFree = new ArrayList();
	}

	/**
	 * 매개변수가 있는 생성자
	 * 
	 * @param projRegistererInfo
	 * @param projReviserInfo
	 * @param projDBMSInfo
	 * @param projFrameworks
	 * @param projProgLangs
	 * @param projRequireRoles
	 */
	public AllOfEuclidProject(Employee projRegistererInfo, Employee projReviserInfo, DBMS projDBMSInfo,
			ArrayList<Framework> projFrameworks, ArrayList<ProgLang> projProgLangs,
			ArrayList<ProjRole> projRequireRoles) {
		this.projRegistererInfo = projRegistererInfo;
		this.projReviserInfo = projReviserInfo;
		this.projDBMSInfo = projDBMSInfo;
		this.projFrameworks = projFrameworks;
		this.projProgLangs = projProgLangs;
		this.projRequireRoles = projRequireRoles;
	}

	//************************************ 게터 세터 ****************************************
	public Employee getProjRegistererInfo() {
		return projRegistererInfo;
	}

	public void setProjRegistererInfo(Employee projRegistererInfo) {
		this.projRegistererInfo = projRegistererInfo;
	}

	public Employee getProjReviserInfo() {
		return projReviserInfo;
	}

	public void setProjReviserInfo(Employee projReviserInfo) {
		this.projReviserInfo = projReviserInfo;
	}

	public DBMS getProjDBMSInfo() {
		return projDBMSInfo;
	}

	public void setProjDBMSInfo(DBMS projDBMSInfo) {
		this.projDBMSInfo = projDBMSInfo;
	}

	public ArrayList<Framework> getProjFrameworks() {
		return projFrameworks;
	}

	public void setProjFrameworks(ArrayList<Framework> projFrameworks) {
		this.projFrameworks = projFrameworks;
	}

	public ArrayList<ProgLang> getProjProgLangs() {
		return projProgLangs;
	}

	public void setProjProgLangs(ArrayList<ProgLang> projProgLangs) {
		this.projProgLangs = projProgLangs;
	}

	public ArrayList<ProjRole> getProjRequireRoles() {
		return projRequireRoles;
	}

	public void setProjRequireRoles(ArrayList<ProjRole> projRequireRoles) {
		this.projRequireRoles = projRequireRoles;
	}

	public ArrayList<ProjJoinedFreelancer> getProjJoinedFree() {
		return projJoinedFree;
	}

	public void setProjJoinedFree(ArrayList<ProjJoinedFreelancer> projJoinedFree) {
		this.projJoinedFree = projJoinedFree;
	}
}