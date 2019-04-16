package com.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessages implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fielName;
	private String message;

	public FieldMessages(String fielName, String message) {
		this.fielName = fielName;
		this.message = message;
	}

	public FieldMessages() {

	}

	public String getFielName() {
		return fielName;
	}

	public void setFielName(String fielName) {
		this.fielName = fielName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
