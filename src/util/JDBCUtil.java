package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCUtil {
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException {
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/donate?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		return con;
	}
	
	
	public static void close(ResultSet rs, Statement st, Connection con){
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
}
