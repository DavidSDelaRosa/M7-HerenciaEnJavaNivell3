package com.vehicles.project.vehicles;

import java.util.ArrayList;
import java.util.List;
import com.vehicles.project.exceptions.*;
import com.vehicles.project.people.Driver;
import com.vehicles.project.people.Persona;
import com.vehicles.project.people.VehicleHolder;

public abstract class Vehicle {

	protected int idVehicle;
	protected String plate;
	protected String brand;
	protected String color;
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	protected VehicleHolder vehicleHolder;
	protected List<Persona> driversAndHolder;
	private static int vehicleCounter = 1;

	public Vehicle(String plate, String brand, String color, VehicleHolder vehicleHolder) throws InvalidPlateException {
		
		this.plate = plate;
		this.brand = brand;
		this.color = color;
		this.vehicleHolder = vehicleHolder;
		
		if(!validatePlate(plate)) throw new InvalidPlateException();
		
		idVehicle=vehicleCounter;
		vehicleCounter++;
	}
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Wheel> getWheels() {
		return wheels;
	}

	public void setWheels(List<Wheel> wheels) {
		this.wheels = wheels;
	}

	public static int getVehicleCounter() {
		return vehicleCounter;
	}

	public VehicleHolder getVehicleHolder() {
		return vehicleHolder;
	}

	public void setVehicleHolder(VehicleHolder vehicleHolder) {
		this.vehicleHolder = vehicleHolder;
	}

	public List<Persona> getDriversAndHolder() {
		return driversAndHolder;
	}

	public void setDriversAndHolder(List<Persona> driversAndHolder) {
		this.driversAndHolder = driversAndHolder;
	}
	

	public int getIdVehicle() {
		return idVehicle;
	}

	public boolean validatePlate(String plate) {
		
		boolean correctPlate = false;
		final int NUMBER_OF_DIGITS = 4;
		int digitsCounter=0;
		int lettersCounter=0;
		
		for(int i=0;i<plate.length();i++) {
			if(Character.isDigit(plate.charAt(i))) digitsCounter ++;
			if(Character.isLetter(plate.charAt(i))) lettersCounter++;
		}
		
		if(digitsCounter==NUMBER_OF_DIGITS && (lettersCounter==2 || lettersCounter==3)) correctPlate=true;
		
		return correctPlate;
	}
	
	public void addDrivers(Driver driver, String typeOfLicense) throws MissingLicenseException {
		if(driver.getLicense().validateTypeOfLicense(typeOfLicense)) driversAndHolder.add(driver);
		else throw new MissingLicenseException();
	}
	
	
	public abstract boolean suitableNumberOfWheels();
}
