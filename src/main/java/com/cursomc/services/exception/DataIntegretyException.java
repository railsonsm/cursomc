package com.cursomc.services.exception;

public class DataIntegretyException extends RuntimeException{
	private static final Long serialVersionUID = 1L;
	
	public DataIntegretyException(String msg) {
		super(msg);
	}
	
	public DataIntegretyException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
