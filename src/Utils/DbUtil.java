package Utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 负责连接获取 
 * 资源的管理
 */
public class DbUtil {
	private static  ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	//重量级组件(最好只创建一次)
	private static DataSource ds  ;
	//通过静态代码块实例化数据源对象
	static{
		Properties p = new Properties();
		try {
			
			p.load(DbUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));
			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取连接
	public static  Connection getConnection() throws SQLException{
			Connection  conn =threadLocal.get() ; 
			if(conn==null) {
				conn = ds.getConnection();
				threadLocal.set(conn);
			}
			return conn ;
	}
	
	//关闭连接
	public static void closeConnection() throws SQLException{
		Connection conn = threadLocal.get() ; 
		if(conn!=null) {
			conn.close();
			threadLocal.set(null);
		}
	}
	//关闭的资源的方法
	public static  void close(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
