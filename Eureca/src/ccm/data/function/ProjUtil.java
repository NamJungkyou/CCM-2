package ccm.data.function;

/*
 * 
 * 이건 프로젝트 관련한거임
 * 
 */

public class ProjUtil
{
	private static ProjUtil instance = new ProjUtil();
	public ProjUtil() { super(); }
	public static ProjUtil getInstance() { return instance; }
	
	public String removeCharAt(String s, int pos)
	{
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	public String codeToRecommSortOption(int code)
	{
		switch(code)
		{
		case 1:
			return "FREEKOSA DESC";
		case 2:
			return "FREESCORE DESC";
		case 3:
			return "PROGLANGSCORE DESC";
		case 4:
			return "FRAMEWORKSCORE DESC";
		case 5:
			return "NUMOFEDUS DESC";
		case 6:
			return "PROJCAREERDAYS DESC";
		case 7:
			return "LEFTJOINDAYS DESC";
		default:
			return "PROGLANGSCORE DESC";
		}
	}
	
	public int recommSortOptionToCode(String option)
	{
		if(option.equals("FREEKOSA DESC")) return 1;
		else if(option.equals("FREESCORE DESC")) return 2;
		else if(option.equals("PROGLANGSCORE DESC")) return 3;
		else if(option.equals("FRAMEWORKSCORE DESC")) return 4;
		else if(option.equals("NUMOFEDUS DESC")) return 5;
		else if(option.equals("PROJCAREERDAYS DESC")) return 6;
		else if(option.equals("LEFTJOINDAYS DESC")) return 7;
		else return 1;
	}
	
	public String codeToAppliedSortOption(int code)
	{
		switch(code)
		{
		case 1:
			return "order by joinnum desc";
		case 2:
			return "order by waitingorder asc";
		case 3:
			return "order by waitingorder desc";
		default:
			return "order by joinnum desc";
		}
	}
	public String appliedSortOptionToCode(String option)
	{
		if(option.equals("order by joinnum desc")) return "1";
		else if(option.equals("order by waitingorder asc")) return "2";
		else if(option.equals("order by waitingorder desc")) return "3";
		else return "1";
	}
	
	public int projJoinAcceptListOptionToCode(String option)
	{
		if(option.equals("APPLICATIONDATE ASC")) return 1;
		else if(option.equals("PROJRECRUITENDDATE ASC")) return 2;
		else if(option.equals("PROJNAME ASC")) return 3;
		else return 4;
	}
	
	public String projJoinAcceptListCodeToOption(int code)
	{
		switch(code)
		{
		case 1:
			return "APPLICATIONDATE ASC";
		case 2:
			return "PROJRECRUITENDDATE ASC";
		case 3:
			return "PROJNAME ASC";
		default:
			return "FREENAME ASC";
		}
	}
	
	public String freelancerListToSearchCodeToOption(int code)
	{
		switch(code)
		{
		case 1:
			return "order by f.jobstate asc";
		case 2:
			return "order by f.jobstate desc";
		default:
			return "order by f.FREEJOINDATE desc";
		}
	}
}
