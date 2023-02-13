package com.hms.hospitalmanagementsystem.Exception;

public class BadRequestException extends Exception {

    String message;

    public BadRequestException(){
        super();
    }
    public BadRequestException(String message) {
		super(message);
		this.message=message;
	}
	public BadRequestException(Exception ex) {
		super(ex);
	}
}
