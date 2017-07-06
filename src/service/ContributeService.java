package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

import beans.Contribute;
import beans.UserContribute;
import dao.ContributeDao;
import dao.UserContributeDao;

public class ContributeService {

	public void register(Contribute contribute) {

		Connection connection = null;
		try {
			connection = getConnection();

			ContributeDao contributeDao = new ContributeDao();
			contributeDao.insert(connection, contribute);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

////////絞込み機能(カテゴリー)

	public List<UserContribute> getContribute(String category) {


		Connection connection = null;
		try {
			connection = getConnection();
			List<UserContribute> ret = UserContributeDao.getUserContributions(connection, category);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

////////絞込み機能(期間：指定～指定)

	public List<UserContribute> getDates(String date1, String date2) {

		Connection connection = null;
		try {
			connection = getConnection();
			List<UserContribute> ret = UserContributeDao.getDates(connection, date1, date2);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


////////絞込み機能(カテゴリー 期間：指定～指定)

	public List<UserContribute> getSet(String date1, String date2, String category) {

		Connection connection = null;
		try {
			connection = getConnection();
			List<UserContribute> ret = UserContributeDao.getSet(connection, date1, date2, category);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



////////投稿を取得
	private static final int LIMIT_NUM = 1000;

	public List<UserContribute> getContribute() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserContributeDao contributeDao = new UserContributeDao();
			List<UserContribute> ret = contributeDao.getUserContributions(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

////////カテゴリーを取得

	public HashSet<String> getCategory() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserContributeDao contributeDao = new UserContributeDao();
			HashSet<String> ret = contributeDao.getCategory(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

/////////投稿削除のメソッド
	public void deleteContribution(int contributeId) {
		Connection connection = null;
		try {
			connection = getConnection();
			ContributeDao contributeDao = new ContributeDao();
			contributeDao.deleteContribution(connection, contributeId);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}

	}



}

