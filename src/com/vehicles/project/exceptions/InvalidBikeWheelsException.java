package com.vehicles.project.exceptions;

public class InvalidBikeWheelsException extends Exception {
	
	private static final String INVALID_BIKE_WHEELS_EXCEPTION = "Diameter of front and back wheels must be the same";
	
	public InvalidBikeWheelsException() {
		super(INVALID_BIKE_WHEELS_EXCEPTION);
	}

}
