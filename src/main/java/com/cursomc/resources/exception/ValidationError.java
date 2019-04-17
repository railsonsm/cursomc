package com.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final Long serialVersionUID = 1L;
	
	private List<FieldMessages> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<FieldMessages> getErrors() {
		return errors;
	}

	public void addError(String fielName, String messagem) {
		errors.add(new FieldMessages(fielName,  messagem));
	}

	
	
	
}
