package com.vehicles.project.exceptions;

public class InvalidNumberOfTruckWheelsException extends Exception {

	private static final String INVALID_NUMBER_WHEELS_ERROR = "A truck must have between 4 and 12 wheels";
	
	public InvalidNumberOfTruckWheelsException() {
		super(INVALID_NUMBER_WHEELS_ERROR);
	}
}
