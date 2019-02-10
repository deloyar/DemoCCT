package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.service.UserService;

@RestController
public class UserController{

	@Autowired
	private UserService userService;

	private User user;

	private List<User> userList = new ArrayList<User>();
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

	/*@RequestMapping(value = "/user/users", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() {
		logger.info("Received: User List Request");
		try {
			userList = userService.findAll(User.class);
		}catch(Exception ex){
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}*/

	@PostMapping(value = "/user/save")
	public ResponseEntity<User> Add(HttpServletRequest httpRequest, @RequestBody User user) {
		logger.info("Received: Add User request");
		
		try{
			if (user != null) {
				User u=userService.save(user);
				return new ResponseEntity<User>(u, HttpStatus.OK);
			}
			return new ResponseEntity<User>(new User(), HttpStatus.OK);
			
		}catch(Exception ex) {
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<User>(new User(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/update")
	public ResponseEntity<User> update(HttpServletRequest httpRequest, @RequestBody User user) {
		logger.info("Received room update request for "+user.getName());
		try {
			
			if (user != null) {
				User u =userService.update(user);
				return new ResponseEntity<User>(u, HttpStatus.OK);
			}
		}catch(Exception ex) {
			logger.info("Exception "+ex.getMessage());
		}		
		return new ResponseEntity<User>(new User(), HttpStatus.OK);
		
	}

	@PostMapping(value = "/user/delete")
	public void delete(HttpServletRequest httpRequest, @RequestBody User user) {
		logger.info("Received user delete request for "+user.getName());
		try{
			if (user != null) {
				userService.delete(Room.class, user.getUserId());
			}
		}catch(Exception ex) {
			logger.info("Exception "+ex.getMessage());
		}
		
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}