package com.vehicles.project.vehicles;

import java.util.List;

import com.vehicles.project.exceptions.InvalidPlateException;
import com.vehicles.project.people.VehicleHolder;

public class Car extends Vehicle {

	public Car(String plate, String brand, String color, VehicleHolder vehicleHolder) throws InvalidPlateException {
		super(plate, brand, color, vehicleHolder);
		
	}

	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels) throws Exception {
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}

	public void addTwoWheels(List<Wheel> wheels) throws Exception {
		if (wheels.size() != 2)
			throw new Exception("No puede haber más de dos ruedas en la Lista");

		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if (!rightWheel.equals(leftWheel))
			throw new Exception("La rueda izquierda no coincide con la rueda derecha");

		this.wheels.add(leftWheel);
		this.wheels.add(rightWheel);
	}


	@Override
	public boolean suitableNumberOfWheels() {
		
		boolean corrrectNumber = false;
		
		if (wheels.size()==4) corrrectNumber = true;
		
		return corrrectNumber;
	}

	@Override
	public String toString() {
		return "Car [idVehicle=" + idVehicle + ", plate=" + plate + ", brand=" + brand + ", color=" + color
				+ ", wheels=" + wheels + ", vehicleHolder=" + vehicleHolder + ", driversAndHolder=" + driversAndHolder
				+ "]";
	}

	
}
