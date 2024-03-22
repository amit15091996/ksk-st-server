package com.khadbhandarserver.inventory.exception;

public class InternalServerError extends RuntimeException {

	public InternalServerError() {
		super();
	}
	public InternalServerError(String message) {
		super(message);
		
	}
}
