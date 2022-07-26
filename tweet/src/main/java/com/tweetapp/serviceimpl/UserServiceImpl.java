package com.tweetapp.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.ResourceAlreadyPresentException;
import com.tweetapp.exception.ResourceNotFoundException;
import com.tweetapp.exception.UserException;
import com.tweetapp.helper.UserResponse;
import com.tweetapp.models.User;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	public static final String SUCCESS="success";

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		Boolean isUserWithEmailPresent = userRepository.findUserByEmail(user.getEmail()).isPresent();
		User isUserWithUsernamePresent = userRepository.findByUsername(user.getUsername());
		if (isUserWithUsernamePresent != null) {
			throw new ResourceAlreadyPresentException("The username "+user.getUsername()+" is already registered");
		} else if (isUserWithEmailPresent.booleanValue()) {
			throw new ResourceAlreadyPresentException("The emailId "+user.getEmail()+" is already registered");
		} else {
			
			return userRepository.save(user);
			
		}
		
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public int deleteUser(User user) {
		userRepository.delete(user);
		return 1;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getUserByUsername(String username) {
		return userRepository.findByUsernameContaining(username);
	}

	@Override
	public Optional<User> getUserById(String id) {
		Optional<User> user= userRepository.findById(id);
		if (!user.isPresent()) {
			
			throw new ResourceNotFoundException("User not present in Database");
		}
		
		return user;
	}

	@Override
	public UserResponse loginUser(String username, String password) throws  UserException{
		UserResponse response = new UserResponse();
		User user = userRepository.findByUsername(username);
		if (user.getPassword().equals(password)) {
			response.setUser(user);
			response.setLoginStatus(SUCCESS);
		} else {
			response.setLoginStatus("failed");
		}
		return response;
	}

	@Override
	public Map<String, String> forgotPassword(String username) {
		Map<String, String> map = new HashMap<>();
		User user = userRepository.findByUsername(username);
		user.setPassword(UUID.randomUUID().toString());
		userRepository.save(user);
		map.put("newPassword", user.getPassword());
		map.put("resetStatus", SUCCESS);
		return map;
	}

	@Override
	public Map<String, String> resetPassword(String username, String password) {
		Map<String, String> map = new HashMap<>();
		User user = userRepository.findByUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		map.put("newPassword", user.getPassword());
		map.put("resetStatus", SUCCESS);
		return map;
	}

}