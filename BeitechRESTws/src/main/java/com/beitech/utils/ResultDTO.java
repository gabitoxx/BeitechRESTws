package com.beitech.utils;

public class ResultDTO {
	
	private boolean success;
	private String message;
	private int successfulChanges;
	private int unsuccessfulChanges;
	
	public ResultDTO(boolean success, String message, int successfulChanges, int unsuccessfulChanges) {
		super();
		this.success = success;
		this.message = message;
		this.successfulChanges = successfulChanges;
		this.unsuccessfulChanges = unsuccessfulChanges;
	}

	public ResultDTO() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSuccessfulChanges() {
		return successfulChanges;
	}

	public void setSuccessfulChanges(int successfulChanges) {
		this.successfulChanges = successfulChanges;
	}

	public int getUnsuccessfulChanges() {
		return unsuccessfulChanges;
	}

	public void setUnsuccessfulChanges(int unsuccessfulChanges) {
		this.unsuccessfulChanges = unsuccessfulChanges;
	}
}