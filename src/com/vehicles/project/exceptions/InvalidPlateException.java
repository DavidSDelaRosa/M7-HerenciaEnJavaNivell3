package com.vehicles.project.exceptions;

public class InvalidPlateException extends Exception {

	private static final String INVALID_PLATE_ERROR= "The plate must have 4 digits and 2 or 3 letters";
	
	public InvalidPlateException() {
		super(INVALID_PLATE_ERROR);
	}
}
