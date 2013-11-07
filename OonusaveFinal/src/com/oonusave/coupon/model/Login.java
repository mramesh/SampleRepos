package com.oonusave.coupon.model;

public class Login {
	
	private String username = "";
	private String password = "";
	private String deviceIndentifier = "";
	
	
	public String getDeviceIndentifier() {
		return deviceIndentifier;
	}
	public void setDeviceIndentifier(String deviceIndentifier) {
		this.deviceIndentifier = deviceIndentifier;
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
	
	
}
