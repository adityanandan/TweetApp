package com.tweetapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tweetapp.exception.UserException;
import com.tweetapp.helper.UserResponse;
import com.tweetapp.models.User;

public interface UserService {
	
	User createUser(User user);

	User updateUser(User user);

	int deleteUser(User user);

	List<User> getAllUsers();

	List<User> getUserByUsername(String username);

	Optional<User> getUserById(String id);

	UserResponse loginUser(String username, String password) throws UserException;

	Map<String, String> forgotPassword(String username);

	Map<String, String> resetPassword(String username, String password);
}
