package com.javaforgood.goodandgenerous.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaforgood.goodandgenerous.data.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLoginName(String loginName);

}
