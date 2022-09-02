package com.example.demo;

public class Acount {
	private String username;
	private String password;
	private String type;//admin/user
	
	public void Acount() {
		this.username="user";
		this.password="user";
		this.type="user";	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

