package com.ddlab.services;

import com.ddlab.entity.User;

public interface IUserService {
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public User getUserById(String id);

}
