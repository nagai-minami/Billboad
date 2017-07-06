package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {
	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("text");
			sql.append(", user_id");
			sql.append(", contribution_id");
			sql.append(", insert_date");
			sql.append(") VALUES ("); // id
			sql.append(" ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", ?"); // contribution_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getContributeId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void deleteComment(Connection connection, int commentId) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM comments WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, commentId);
			ps.executeUpdate();
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {

				close(ps);
			}
		}

}
