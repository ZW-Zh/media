package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate {
	/**
	 * 封装了增加、修改、删除操作
	 * @param sql : 待执行的sql语句
	 * @param args: sql的参数
	 * @return 受影响的行
	 * @throws SQLException
	 */
	public static  int executeUpdate(String sql,Object...args) throws SQLException{
		int result = -1 ;
		Connection conn = DbUtil.getConnection() ; 
		PreparedStatement pstmt = conn.prepareStatement(sql) ;
		if(args!=null) {
			for(int i = 0;i<args.length;i++) {
				pstmt.setObject(i+1, args[i]);
			}
		}
		result = pstmt.executeUpdate();
		DbUtil.close(null, pstmt, null);
		return result ; 
	}
	
	
	
	
	
	/**
	 * 执行查询的方法
	 * @param sql :待执行的sql
	 * @param omMapper : 行->java对象
	 * @param args ：sql中的 参数
	 * @return 集合
	 * @throws SQLException
	 */
	public static List executeQuery(String sql,IObjectMapper omMapper,Object ...args) throws SQLException {
		List list = new ArrayList();
		Connection conn = DbUtil.getConnection() ; 
		PreparedStatement pstmt = conn.prepareStatement(sql) ;
		if(args!=null) {
			for(int i = 0;i<args.length;i++) {
				pstmt.setObject(i+1, args[i]);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		//拿数据
		while(rs.next()) {
			list.add(omMapper.getObjectFromRow(rs));
		}
		DbUtil.close(rs, pstmt, null);
		return list;
	}
}
