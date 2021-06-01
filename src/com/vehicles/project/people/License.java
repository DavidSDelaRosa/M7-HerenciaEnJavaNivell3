package com.vehicles.project.people;
import java.util.GregorianCalendar;

import com.vehicles.project.exceptions.InvalidTypeOfLicenseException;

public class License {
	
	protected int id;
	protected String type; //'A' for bikes, 'B' for cars and 'C' for trucks
	protected String licenseName; 
	protected GregorianCalendar expirationDate;
	
	public License(int id, String type, String licenseName) throws InvalidTypeOfLicenseException {
		this.id = id;
		this.type = type;
		this.licenseName = licenseName;
		
		if(!validateTypeOfLicense(type)) throw new InvalidTypeOfLicenseException();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public GregorianCalendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(GregorianCalendar expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public boolean validateTypeOfLicense(String typeOfLicense) {
		
		char[] typesOfLicense = {'A', 'B', 'C'};
		
		boolean typeIsCorrect=false;
		
		char[] typeOfLicenseArray = typeOfLicense.toCharArray();
		
		for(int i=0; i<typeOfLicenseArray.length;i++) {
			for(int j=0;j<typesOfLicense.length;j++) {
				if(typeOfLicenseArray[i] == typesOfLicense[j]) typeIsCorrect = true;
			}
		}
		return typeIsCorrect;
	}

	@Override
	public String toString() {
		return "License [id=" + id + ", type=" + type + ", licenseName=" + licenseName + ", expirationDate="
				+ expirationDate + "]";
	}
	
}
