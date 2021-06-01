package com.vehicles.project.people;
import java.util.GregorianCalendar;

public abstract class Persona {
	
	protected int idPersona;
	protected String name;
	protected String surName;
	protected GregorianCalendar birthdate;
	private static int peopleCounter = 1;
	
	public Persona(String name, String surName, GregorianCalendar birthdate) {
		
		this.name = name;
		this.surName = surName;
		this.birthdate = birthdate;
		
		idPersona = peopleCounter;
		peopleCounter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public GregorianCalendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(GregorianCalendar birthdate) {
		this.birthdate = birthdate;
	}
	
	public static int getPeopleCounter() {
		return peopleCounter;
	}

	
	public int getIdPersona() {
		return idPersona;
	}

	@Override
	public String toString() {
		return "Persona [name=" + name + ", surName=" + surName + ", birthdate=" + birthdate + "]";
	}
}
