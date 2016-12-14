package com.javaforgood.goodandgenerous.service;


import com.javaforgood.goodandgenerous.data.model.Role;
import com.javaforgood.goodandgenerous.data.model.Subscription;
import com.javaforgood.goodandgenerous.data.model.User;
import org.springframework.security.core.authority.AuthorityUtils;


public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;
	private Subscription subscription;

	public CurrentUser(User user) {
		super(user.getLoginName(), user.getPwd(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getRole();
	}

}