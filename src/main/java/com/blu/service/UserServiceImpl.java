package com.blu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blu.bean.User;
import com.blu.dao.UserDao;
import com.blu.util.Constants;
import com.blu.util.Utility; 

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User generateHashingForUsername(User user) throws Exception {
		String userMac = generateUserMac(user.getUser_name());
		user.setUser_mac(userMac);

		return user;
	}

	private String generateUserMac(String userName) throws Exception {		
		return Utility.encodeMd5(userName + Utility.encodeMd5(userName));
	}

	@Override
	public User validateUser(User user) throws Exception {
		boolean isValidUser = userDao.isEventUser(user.getUser_name());

		if (isValidUser) {
			user.setStatus_code(Constants.SUCCESS_STATUS_CODE);
		} else {
			user.setStatus_code(Constants.ERROR_STATUS_CODE);
		}

		return user;
	}

}
