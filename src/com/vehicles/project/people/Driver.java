package com.vehicles.project.people;
import java.util.GregorianCalendar;

public class Driver extends Persona{
	
	protected License license;

	public Driver(String name, String surName, GregorianCalendar birthdate, License license){
		super(name, surName, birthdate);
		this.license = license;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	@Override
	public String toString() {
		return "Driver [license=" + license + ", name=" + name + ", surName=" + surName + ", birthdate=" + birthdate
				+ "]";
	}
}
