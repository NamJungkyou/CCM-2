package ccm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTO
{
	public void setParams(ResultSet rs) throws SQLException;
}
