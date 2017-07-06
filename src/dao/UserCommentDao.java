package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class UserCommentDao {

	public List<Comment> getUserComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_comments ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Comment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int commentId = rs.getInt("comment_id");
				String name = rs.getString("name");
				int contributeId = rs.getInt("contribution_id");
				int userId = rs.getInt("user_id");
				int branchId = rs.getInt("branch_id");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				Comment comment = new Comment();
				comment.setId(commentId);
				comment.setName(name);
				comment.setText(text);
				comment.setUserId(userId);
				comment.setContributeId(contributeId);
				comment.setBranchId(branchId);
				comment.setInsertDate(insertDate);
				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

}
