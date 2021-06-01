package com.vehicles.project.vehicles;

import com.vehicles.project.exceptions.InvalidWheelDiameterException;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) throws InvalidWheelDiameterException {
		this.brand = brand;
		this.diameter = diameter;
		
		if(!validateDiameter(diameter)) throw new InvalidWheelDiameterException();
		
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public boolean validateDiameter(double diameter) {
		
		boolean correctDiameter = false;
		
		if(diameter>0.4 && diameter<4) correctDiameter = true;
		
		return correctDiameter;
	}
	
	@Override
	public String toString() {
		return "Wheel [brand=" + brand + ", diameter=" + diameter + "]";
	}
}
