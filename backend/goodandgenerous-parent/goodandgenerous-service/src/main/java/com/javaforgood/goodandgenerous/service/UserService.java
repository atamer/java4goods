package com.javaforgood.goodandgenerous.service;

import com.javaforgood.goodandgenerous.data.model.User;

import java.util.Optional;



public interface UserService {

	Optional<User> getUserByLoginName(String loginName);

	void subscribe(User user, Long categoryId);

	User findById(Long id);

}