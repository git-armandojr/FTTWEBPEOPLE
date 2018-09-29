package br.edu.cefsa.ftt.util;

public class MyException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public MyException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
