package com.ecommerce.userservice.common;

public class ApiResponse {
	private  boolean success;
	private  Object responseData;
	public ApiResponse(boolean success, Object responseData) {
		super();
		this.success = success;
		this.responseData = responseData;
	}
	public boolean isSuccess() {
		return success;
	}
	public Object getResponseData() {
		return responseData;
	}
}
