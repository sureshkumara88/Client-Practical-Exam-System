package com.blu.controller;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import com.blu.bean.User;
import com.blu.server.main.SpringBootRestApiApplication;
import com.blu.util.Constants;

/**
 * This class act as a TDD implementation, where it will test all the functionality of the application.
 * @author suresh kumar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RestController
public class ControllerTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void userTestCase1() {
		User user = new User();
		user.setUser_name("suresh");
		
		ResponseEntity<User> response = restTemplate.postForEntity(createURLWithPort(Constants.ENDPOINT), user, User.class);
		
		assertThat(response.getBody().getUser_mac()).isNotEmpty();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus_code()).isEqualTo(Constants.SUCCESS_STATUS_CODE);
	}
	
	@Test
	public void userTestCase2() {
		User user = new User();
		user.setUser_name("sureshkumar");
		
		ResponseEntity<User> response = restTemplate.postForEntity(createURLWithPort(Constants.ENDPOINT), user, User.class);
		
		assertThat(response.getBody().getUser_mac()).isNotEmpty();	
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus_code()).isEqualTo(Constants.ERROR_STATUS_CODE);			
	}
	
	@Test
	public void userTestCase3() {
		User user = new User();
		
		ResponseEntity<User> response = restTemplate.postForEntity(createURLWithPort(Constants.ENDPOINT), user, User.class);
		
		assertThat(response.getBody().getUser_mac()).isNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);		
		assertThat(response.getBody().getStatus_code()).isEqualTo(Constants.ERROR_STATUS_CODE);			
	}
	
	@Test
	public void userTestCase4() {
		ResponseEntity<User> response = restTemplate.postForEntity(createURLWithPort(Constants.ENDPOINT), null, null);
		
		assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
