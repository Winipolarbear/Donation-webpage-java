package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JDBCUtil;
import entity.Money;



public class MoneyDao {
	/**
	 * get all data
	 **/
	public static ArrayList<Money> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Money> slist = new ArrayList<Money>();
		try {
			String sql = "SELECT * FROM money";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			slist = LoadMoney(rs);

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
			String sql = "delete from money where id = ?";

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

	public static Money getbyid(Integer id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Money> slist = new ArrayList<Money>();
		try {
			String sql = "SELECT * FROM money where id=?";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			slist = LoadMoney(rs);

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

	public static boolean add(Money s) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			String sql = "insert into money (identifier,money,creatTime) values( ?, ?,?)";
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);

			pst.setString(1, s.getIdentifier());

			pst.setBigDecimal(2, s.getMoney());

			pst.setDate(3, new java.sql.Date(s.getCreatTime().getTime()));

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

	public static boolean updateById(Money s) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			String sql = "update money set id= ?, identifier= ?, money= ?  where id=?";

			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql);

			pst.setString(1, s.getIdentifier());

			pst.setBigDecimal(2, s.getMoney());

			pst.setDate(3, new java.sql.Date(s.getCreatTime().getTime()));
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
		String sql = "select count(*) from Money";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tr;
	}

	private static ArrayList<Money> LoadMoney(ResultSet rs) throws SQLException {
		ArrayList<Money> sist = new ArrayList<Money>();
		while (rs.next()) {
			Money s = new Money();
			s.setId(rs.getInt("id"));
			s.setIdentifier(rs.getString("identifier"));
			s.setMoney(rs.getBigDecimal("money"));
			s.setCreatTime(rs.getDate("creatTime"));
			sist.add(s);

		}
		return sist;

	}

}
