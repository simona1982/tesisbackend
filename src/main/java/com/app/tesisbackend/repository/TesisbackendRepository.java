package com.app.tesisbackend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tesisbackend.model.User;



//import antlr.collections.List;

public interface TesisbackendRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email, String password);
	
	//public User deleteById(int id);
	
	public User findById(int id);
	
	public List<User> findAll();
	
	//public List<User> listar();
	//User listarId(int id);
	
	//User delete(int id);
}
