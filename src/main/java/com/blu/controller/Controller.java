package com.blu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blu.bean.User;
import com.blu.service.MailService;
import com.blu.service.UserService;
import com.blu.util.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * This class act as a controller to receive the request, process it and send back the response to client
 * @author suresh kumar
 *
 */
@RestController
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
		
	/**
	 * This method is used to register the given account with generated secret key
	 * @param account
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(method = RequestMethod.POST, value= Constants.ENDPOINT)
	@HystrixCommand(fallbackMethod = Constants.USER_FALLBACK, 
					commandProperties = {@HystrixProperty(name = Constants.TIMEOUT_PROPERTY, value = Constants.TIMEOUT_VALUE)})
	public User service(@RequestBody User user) throws Exception {
		
		try {
			validateRequest(user);
			userService.generateHashingForUsername(user);
			userService.validateUser(user);
			if (user.getStatus_code().equals(Constants.SUCCESS_STATUS_CODE)) {
				mailService.sendMail(user);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			user.setStatus_code(Constants.ERROR_STATUS_CODE);
			throw ex;
		}

		return user;
	}
	
	private void validateRequest(User user) throws Exception {
		if (user.getUser_name() == null || user.getUser_name().isEmpty()) {
			user.setStatus_code(Constants.ERROR_STATUS_CODE);
			throw new Exception("The User name is empty");
		}
	}
	
	@SuppressWarnings("unused")
	private User fallbackUser(User user) {
		User response = new User();
		response.setStatus_code(Constants.ERROR_STATUS_CODE);
		return response;
	}
}
