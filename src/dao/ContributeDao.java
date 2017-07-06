package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Contribute;
import exception.SQLRuntimeException;

public class ContributeDao {

	public void insert(Connection connection, Contribute contribute) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO contributions ( ");
			sql.append("subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", user_id");
			sql.append(", insert_date");
			sql.append(") VALUES ("); // id
			sql.append(" ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, contribute.getSubject());
			ps.setString(2, contribute.getText());

			if(contribute.getCategory().isEmpty()){
				ps.setString(3, contribute.getCategory2());
			}else if(contribute.getCategory2().isEmpty()){
				ps.setString(3, contribute.getCategory());
			}

			ps.setInt(4, contribute.getUserId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void deleteContribution(Connection connection, int contributeId) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM contributions WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, contributeId);
			ps.executeUpdate();
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {

				close(ps);
			}
		}

}