package com.app.tesisbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tesisbackend.model.User;
import com.app.tesisbackend.repository.TesisbackendRepository;
import java.util.List;


@Service
public class TesisbackendService {
	
	@Autowired
	private TesisbackendRepository repo;
	
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public User fetchUserByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
		
	}
	
	public User fetchUserById(int id) {
		return repo.findById(id);
	}
	
	public User edit(User user) {
		return repo.save(user);
	}
	
	public void deleteUserById(int id) {
		repo.deleteById(id);
	}
	
	public List<User> listar(){
		return repo.findAll();
	}
}
