package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.models.User;


public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByUsernameContaining(String username);
	Optional<User> findByUsername(String username);
	Optional<User> findUserByEmail(String email);
	

}
