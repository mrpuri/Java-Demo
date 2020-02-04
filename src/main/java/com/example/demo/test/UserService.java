package com.example.demo.test;

import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.City;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Users> findAll() {
		List<Users> user = (List<Users>) userRepo.findAll();		
		
		return user;
	}

	@Override
	public Users Create(Users user) {
		Users user2 = userRepo.save(user);
     	return user2;
	}


}
