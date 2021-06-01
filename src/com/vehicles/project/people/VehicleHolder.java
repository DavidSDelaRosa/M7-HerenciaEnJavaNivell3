package com.vehicles.project.people;

import java.util.GregorianCalendar;

public class VehicleHolder extends Persona{
	
	protected License license;
	protected boolean hasInsurance;
	protected boolean hasGarage;

	public VehicleHolder(String name, String surName, GregorianCalendar birthdate, License license, boolean hasInsurance, boolean hasGarage) {
		
		super(name, surName, birthdate);
		this.license = license;
		this.hasInsurance = hasInsurance;
		this.hasGarage = hasGarage;	
		
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public boolean isHasInsurance() {
		return hasInsurance;
	}

	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}

	public boolean isHasGarage() {
		return hasGarage;
	}

	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	@Override
	public String toString() {
		return "VehicleHolder [name=" + name + ", surName=" + surName
				+ ", license=" + license + ", hasInsurance=" + hasInsurance + ", hasGarage=" + hasGarage;
				 
	}
}
