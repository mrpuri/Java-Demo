package com.example.demo.test;

import java.util.List;

public interface IUserService {

	List<Users> findAll();
	
//	UserRepository repo = context.getBean(UserRepository.class);

		Users Create(Users user);
		
}
