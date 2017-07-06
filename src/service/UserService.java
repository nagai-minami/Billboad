package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

import beans.User;
import dao.UserDao;
import utils.CipherUtil;


public class UserService {


///////登録
	public void register(User user) {
		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);


			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

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


/////idに一致するユーザーを取得
	public User getUser(int id){

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User ret = userDao.getUser(connection, id);

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


///////ログインIDを引数にユーザー情報を取得
	public User getLoginId(String loginId){

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User ret = userDao.getLoginId(connection, loginId);

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

///////ユーザー一覧を取得
	public List<User> getUsers(){

		Connection connection = null;
		try {
			connection = getConnection();
			List<User> ret = UserDao.getUsers(connection);

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

////////編集変更のメソッド
	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();


			if(user.getPassword() != null){
				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);
				UserDao userDao = new UserDao();
				userDao.update(connection, user);
			}else{
				UserDao userDao = new UserDao();
				userDao.update2(connection, user);
			}

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



////////ログインID一覧取得
	private static final int LIMIT_NUM = 1000;

	public HashSet<String> getLoginIdList() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			HashSet<String> ret = userDao.getLoingIdList(connection, LIMIT_NUM);

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


/////////ユーザー削除のメソッド

	public void delete(int userId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.delete(connection, userId);
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

/////////アカウントのメソッド
	public void isWorking(int userId, int isWorking) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.updateIsWorking(connection, userId, isWorking);
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

