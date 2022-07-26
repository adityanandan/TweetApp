package com.tweetapp.helper;

import com.tweetapp.models.User;

public class UserResponse {

	private User user;
	private String loginStatus;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
}
