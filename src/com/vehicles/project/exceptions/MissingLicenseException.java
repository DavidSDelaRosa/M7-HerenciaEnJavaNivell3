package com.vehicles.project.exceptions;

public class MissingLicenseException extends Exception{
	
	private static final String MISSING_LICENSE_ERROR = "Your license type does not allow you to drive that vehicle";
	
	public MissingLicenseException() {
		super(MISSING_LICENSE_ERROR);
	}

}
