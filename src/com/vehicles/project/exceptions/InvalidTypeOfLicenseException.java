package com.vehicles.project.exceptions;

public class InvalidTypeOfLicenseException extends Exception{
	
	private static final String INVALID_TYPE_OF_LICENSE_ERROR = "There are only 3 types of license allowed: A, B or C ";
	
	public InvalidTypeOfLicenseException() {
		super(INVALID_TYPE_OF_LICENSE_ERROR);
	}

}
