package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import beans.UserContribute;
import exception.SQLRuntimeException;

public class UserContributeDao {

	public List<UserContribute> getUserContributions(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_contributions ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			List<UserContribute> ret = toUserContributeList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public HashSet<String> getCategory(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT category FROM users_contributions ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			HashSet<String> ret = toCategorySet(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

//////期間絞込み

	public static List<UserContribute> getDates(Connection connection, String date1, String date2) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_contributions "
					+ "WHERE ? <= DATE_FORMAT(insert_date,'%Y-%m-%d') "
					+ "AND  DATE_FORMAT(insert_date,'%Y-%m-%d') <=  ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, date1);
			ps.setString(2, date2);

			ResultSet rs = ps.executeQuery();
			List<UserContribute> ret = toUserContributeList(rs);

			return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {

				close(ps);
			}
		}



//////期間絞込みセット

	public static List<UserContribute> getSet(Connection connection, String date1, String date2, String category) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_contributions "
					+ "WHERE category = ? "
					+ "AND ? <= DATE_FORMAT(insert_date,'%Y-%m-%d')"
					+ "AND DATE_FORMAT(insert_date,'%Y-%m-%d') <=  ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, category);
			ps.setString(2, date1);
			ps.setString(3, date2);

			ResultSet rs = ps.executeQuery();
			List<UserContribute> ret = toUserContributeList(rs);

			return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {

				close(ps);
			}
		}


///////カテゴリー
	private static HashSet<String> toCategorySet(ResultSet rs)
			throws SQLException {

		HashSet<String> ret = new HashSet<String>();
		try {
			while (rs.next()) {

				String category = rs.getString("category");
				ret.add(category);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


////////投稿取得
	private static List<UserContribute> toUserContributeList(ResultSet rs)
			throws SQLException {

		List<UserContribute> ret = new ArrayList<UserContribute>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int userId = rs.getInt("user_id");
				int ContributeId = rs.getInt("contributions_id");
				int branchId = rs.getInt("branch_id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserContribute contribute = new UserContribute();
				contribute.setContributeId(ContributeId);
				contribute.setUserId(userId);
				contribute.setBranchId(branchId);
				contribute.setName(name);
				contribute.setSubject(subject);
				contribute.setText(text);
				contribute.setCategory(category);
				contribute.setInsertDate(insertDate);

				ret.add(contribute);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


//////絞込み機能

	public static List<UserContribute> getUserContributions(Connection connection, String category) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_contributions WHERE category = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();
			List<UserContribute> ret = toUserContributeList(rs);
			return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {

				close(ps);
			}
		}
}


