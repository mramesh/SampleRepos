package com.oonusave.coupon.model;

public class RegistrationResponse {
	
	private boolean isSuccess = false;
	private String message = "";
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
