package com.vehicles.project.exceptions;

public class InvalidWheelDiameterException extends Exception {

	private static final String INVALID_WHEEL_DIAMETER_ERROR= "Wheel diameter must be bettween 0.4 and 4";
	
	public InvalidWheelDiameterException() {
		super(INVALID_WHEEL_DIAMETER_ERROR);
	}
}
