package com.app.tesisbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tesisbackend.model.User;
import com.app.tesisbackend.service.TesisbackendService;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TesisbackendController {
	
	@Autowired
	private TesisbackendService service;
	
	@PostMapping("/register-user")
	public User registerUser(@RequestBody User user) throws Exception {
		
		String tempEmail = user.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			User userObj = service.fetchUserByEmail(tempEmail);
			if(userObj != null) {
				throw new Exception("user with " + tempEmail + " is already exist.");
			}
		}
		
		
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
		User userObj = null;
		
		if(tempEmail != null && tempPassword != null) {
			userObj = service.fetchUserByEmailAndPassword(tempEmail, tempPassword);
		}
		
		if(userObj == null) {
			throw new Exception("bad credentials");
		}
		
		return userObj;
	}
	
	@DeleteMapping(path = {"/delete-user/{id}"})
	public User deleteUser(@PathVariable("id") int id) throws Exception {
		User userObj = service.fetchUserById(id);
		
		if(userObj == null) {
			throw new Exception("user does not exist");
		}
		
		service.deleteUserById(id);
		return userObj;
	}
	
	@PutMapping(path = {"/update-user/{id}"})
	public User updateUser(@RequestBody User user, @PathVariable("id") int id) throws Exception {
		User userObj = service.fetchUserById(id);
		
		if(userObj == null) {
			throw new Exception("user does not exist");
		}
		
		
		return service.edit(user);
	}
	
	@GetMapping
	public List<User>listar(){
		return service.listar();
	}
}
