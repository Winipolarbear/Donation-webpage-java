package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JDBCUtil;
import entity.User;

/**
 * <p>
 *

 */

public class UserDao {
	/**
	 * get all data
	 **/
	public static ArrayList<User> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<User> slist = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			slist = LoadUser(rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pst, con);
		}
		return slist;
	}

	public static boolean deleteById(String id) {
		boolean flag = true;

		Connection con = null;
		PreparedStatement pst = null;

		try {
			String sql = "delete from user where id = ?";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(id));
			pst.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			flag = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pst, con);
		}
		return flag;
	}

	public static User getByIdentifier(String  uuid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<User> slist = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user where identifier=?";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uuid);
			rs = pst.executeQuery();
			slist = LoadUser(rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pst, con);
		}
		if (slist != null && slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	public static boolean add(User s) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			String sql = "insert into user (identifier,versions,count) values( ?, ?,?)";
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);

			pst.setString(1, s.getIdentifier());

			pst.setString(2, s.getVersions());

			pst.setInt(3, s.getCount());

			int i = pst.executeUpdate();
			if (i >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pst, con);
		}
		return false;

	}

	public static boolean updateById(User s) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			String sql = "update user set  identifier= ?, versions= ?, count= ?  where id=?";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);

			pst.setString(1, s.getIdentifier());

			pst.setString(2, s.getVersions());

			pst.setInt(3, s.getCount());
			pst.setInt(4, s.getId());
			int i = pst.executeUpdate();
			if (i >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pst, con);
		}

		return false;

	}

	public static int getcount() {
		String sql = "select count(*) from User";
		Connection conn;
		int tr = 0;
		try {
			conn = JDBCUtil.getConnection();

			PreparedStatement smt = conn.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				tr = rs.getInt(1);
			}
			smt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return tr;
	}

	private static ArrayList<User> LoadUser(ResultSet rs) throws SQLException {
		ArrayList<User> sist = new ArrayList<User>();
		while (rs.next()) {
			User s = new User();
			s.setId(rs.getInt("id"));
			s.setIdentifier(rs.getString("identifier"));
			s.setVersions(rs.getString("versions"));
			s.setCount(rs.getInt("count"));
			sist.add(s);

		}
		return sist;

	}

}
