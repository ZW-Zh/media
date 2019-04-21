package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IObjectMapper {
	public Object getObjectFromRow(ResultSet rs) throws SQLException;
}
