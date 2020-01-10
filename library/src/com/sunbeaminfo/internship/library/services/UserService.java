package com.sunbeaminfo.internship.library.services;

import com.sunbeaminfo.internship.library.daos.Dao;
import com.sunbeaminfo.internship.library.daos.UserDao;
import com.sunbeaminfo.internship.library.entities.User;

public class UserService {

	public User signIn(String email, String passwd) {
		try (Dao dao = new Dao()) {
			UserDao userDao = new UserDao(dao.open());
			User user = userDao.findUserByEmail(email);
			if(passwd.equals(user.getPasswd()))
				return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int signUp(User user) {
		try (Dao dao = new Dao()) {
			UserDao userDao = new UserDao(dao.open());
			return userDao.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int changePasswd(User user, String newPasswd) {
		try (Dao dao = new Dao()) {
			UserDao userDao = new UserDao(dao.open());
			user.setPasswd(newPasswd);
			return userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int editProfile(User user, String email, String mobile) {
		try (Dao dao = new Dao()) {
			UserDao userDao = new UserDao(dao.open());
			if(email != null && !email.isEmpty())
				user.setEmail(email);
			if(mobile != null && !mobile.isEmpty())
				user.setEmail(mobile);
			return userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
