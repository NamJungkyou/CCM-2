package ccm.data;

import java.util.ArrayList;

import ccm.data.table.DBMS;
import ccm.data.table.Employee;
import ccm.data.table.Framework;
import ccm.data.table.ProgLang;
import ccm.data.table.ProjRole;
import ccm.data.table.Project;

public class AllOfEuclidProject extends Project
{
	private Employee projRegistererInfo;
	private Employee projReviserInfo;
	private DBMS projDBMSInfo;
	private ArrayList<Framework> projFrameworks;
	private ArrayList<ProgLang> projProgLangs;
	private ArrayList<ProjRole> projRequireRoles;
	private ArrayList<ProjJoinedFreelancer> projJoinedFree;
	
	
	public AllOfEuclidProject()
	{
		super();
		this.projRegistererInfo = new Employee();
		this.projReviserInfo = new Employee();
		this.projDBMSInfo = new DBMS();
		this.projRequireRoles = new ArrayList<ProjRole>();
		this.projFrameworks = new ArrayList<Framework>();
		this.projProgLangs = new ArrayList<ProgLang>();
		this.projJoinedFree = new ArrayList<ProjJoinedFreelancer>();
		
	}
	public AllOfEuclidProject(Employee projRegistererInfo, Employee projReviserInfo,
			DBMS projDBMSInfo, ArrayList<Framework> projFrameworks,
			ArrayList<ProgLang> projProgLangs, ArrayList<ProjRole> projRequireRoles)
	{
		super();
		this.projRegistererInfo = projRegistererInfo;
		this.projReviserInfo = projReviserInfo;
		this.projDBMSInfo = projDBMSInfo;
		this.projFrameworks = projFrameworks;
		this.projProgLangs = projProgLangs;
		this.projRequireRoles = projRequireRoles;
	}
	
	public Employee getProjRegistererInfo() { return projRegistererInfo; }
	public void setProjRegistererInfo(Employee projRegistererInfo) { this.projRegistererInfo = projRegistererInfo; }
	public Employee getProjReviserInfo() { return projReviserInfo; }
	public void setProjReviserInfo(Employee projReviserInfo) { this.projReviserInfo = projReviserInfo; }
	public DBMS getProjDBMSInfo() { return projDBMSInfo; }
	public void setProjDBMSInfo(DBMS projDBMSInfo) { this.projDBMSInfo = projDBMSInfo; }
	public ArrayList<Framework> getProjFrameworks() { return projFrameworks; }
	public void setProjFrameworks(ArrayList<Framework> projFrameworks) { this.projFrameworks = projFrameworks; }
	public ArrayList<ProgLang> getProjProgLangs() { return projProgLangs; }
	public void setProjProgLangs(ArrayList<ProgLang> projProgLangs) { this.projProgLangs = projProgLangs; }
	public ArrayList<ProjRole> getProjRequireRoles() { return projRequireRoles; }
	public void setProjRequireRoles(ArrayList<ProjRole> projRequireRoles) { this.projRequireRoles = projRequireRoles; }
	public ArrayList<ProjJoinedFreelancer> getProjJoinedFree() { return projJoinedFree; }
	public void setProjJoinedFree(ArrayList<ProjJoinedFreelancer> projJoinedFree) { this.projJoinedFree = projJoinedFree; }
}
