package com.example.auth;

public class TokenResponseEntity {
	public TokenResponseEntity(String ptoken, String puser) {
		this.token = ptoken;
		this.user = puser;
	}
	public TokenResponseEntity() {}
	public String logStatus="0";
	private String token;
	private String user;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
