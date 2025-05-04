package com.mst.AlertHub.services;


import com.mst.AlertHub.beans.User;

import java.util.List;

public interface UserService {
	
	User getUserById(int id);
	List<User> getAllUser();
	User createUser(User user);
	User updateUser(int id,User updatedUser);
	boolean deleteUser(int id);
	User assignRoleToUser(int userId, int roleId);
}
