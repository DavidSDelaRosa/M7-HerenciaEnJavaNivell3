package com.vehicles.project;

import java.util.*;
import java.util.Scanner;
import com.vehicles.project.exceptions.*;
import com.vehicles.project.people.*;
import com.vehicles.project.vehicles.*;

public class Main {

	static List<Persona> bbddPersonas = new ArrayList<>();
	static List<Vehicle> bbddVehicles = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int rspMainMenu;

		do {		// Menú principal
			System.out.println("MENÚ PRINCIPAL:");
			System.out.println("¿Qué quieres hacer?\n 1. Registrar titular o conductor\n 2. Crear vehículo\n 3. Salir");

			try {
				rspMainMenu = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				rspMainMenu = 3;
				System.err.println("Sólo se aceptan dígitos! Saliendo...");
			}

			int rspRegPeopleMenu;
			int rspCreateVehicleMenu;

			switch (rspMainMenu) {

			case 1: { // 1. REGISTRO DE TITULARES Y CONDUCTORES

				do {
					System.out.println("\nMENÚ DE REGISTRO DE TITULARES Y CONDUCTORES");
					System.out.println(
							"¿Qué quieres registrar?\n 1. Un titular\n 2. Un conductor\n 3. Salir al menú principal");

					try {
						rspRegPeopleMenu = sc.nextInt();
						sc.nextLine();
					} catch (InputMismatchException e) {
						rspRegPeopleMenu = 3;
						System.err.println("Sólo se aceptan dígitos! Volviendo al menú principal...");
					}

					switch (rspRegPeopleMenu) {

					case 1: // REGISTRO DE TITULARES
						System.out.println("Registrando titular...");
						VehicleHolder vehicleHolder = createHolder();
						bbddPersonas.add(vehicleHolder);
						System.out.println(
								"Titular creado: " + vehicleHolder.getName() + " " + vehicleHolder.getSurName());
						System.out.println("Tu nº de registro es: " + vehicleHolder.getIdPersona());
						break;

					case 2: // REGISTRO DE CONDUCTORES
						System.out.println("Registrando conductor...");
						Driver driver = createDriver();
						bbddPersonas.add(driver);
						System.out.println("Conductor creado: " + driver.getName() + " " + driver.getSurName());
						System.out.println("Tu nº de registro es: " + driver.getIdPersona());
						break;

					case 3: // SALIR AL MENÚ ANTERIOR
						System.out.println("Volviendo al menú principal...");
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + rspRegPeopleMenu);
					}
				} while (rspRegPeopleMenu != 3);

				break;
			}
			case 2: { // 2. CREAR VEHÍCULOS

				do {
					System.out.println("\nMENÚ DE REGISTRO DE VEHÍCULOS");
					System.out.println(
							"¿Qué quieres registrar?\n 1. Un coche\n 2. Una moto\n 3. Un camión\n 4. Salir al menú principal");
					try {
						rspCreateVehicleMenu = sc.nextInt();
						sc.nextLine();
					} catch (InputMismatchException e) {
						rspCreateVehicleMenu = 4;
						System.err.println("Sólo se aceptan dígitos! Volviendo al menú principal...");
					}

					switch (rspCreateVehicleMenu) {

					case 1: // COCHES
						System.out.println("Tiene titular el vehículo que quieres crear?\n 1.SI\n 2.NO");
						int tieneTitular;

						try {
							tieneTitular = sc.nextInt();
							sc.nextLine();
						} catch (InputMismatchException e) {
							tieneTitular = 3;
							System.err.println("Sólo se aceptan dígitos! Volviendo al menú principal...");
						}

						if (tieneTitular == 1) {
							if (bbddPersonas.isEmpty()) {
								System.err.println("La base de datos de personas está vacía");
								rspCreateVehicleMenu = 4;
								break;
							}

							System.out.println("Dime tu nº de registro: ");
							int nRegistro = sc.nextInt();
							sc.nextLine();

							VehicleHolder vh = (VehicleHolder) findPersonById(nRegistro);

							if (!vh.getLicense().validateTypeOfLicense("B")) {
								System.err.println("No coinciden los tipos de Licencia");
								rspCreateVehicleMenu = 4;
								break;
							}

							try {
								Car car = createCarForHolder(vh);

								bbddVehicles.add(car);
								System.out.println("Coche creado. ID del vehículo: " + car.getIdVehicle());
								break;
							}catch(Exception e) {
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}
							

						} else if (tieneTitular == 2) {
							System.out.println("Necesitaremos tus datos:");
							VehicleHolder vehicleHolder = createHolderForLicense("B");
							bbddPersonas.add(vehicleHolder);
							System.out.println(
									"Titular creado: " + vehicleHolder.getName() + " " + vehicleHolder.getSurName());
							System.out.println("Tu nº de registro es: " + vehicleHolder.getIdPersona());

							try {
								Car car = createCarForHolder(vehicleHolder);
								bbddVehicles.add(car);
								System.out.println("Coche creado. ID del vehículo: " + car.getIdVehicle());
								break;
							}catch(Exception e) {
								e.printStackTrace();
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}
							

						} else {
							System.out.println("Ha habido un error. Debes elegir 1, si tiene titular el coche o 2, si no lo tiene. Saliendo al menú principal");
							rspCreateVehicleMenu = 4;
							break;
						}

					case 2: // MOTOS

						System.out.println("Tiene titular el vehículo que quieres crear?\n 1.SI\n 2.NO");

						int tieneTitular2;

						try {
							tieneTitular2 = sc.nextInt();
							sc.nextLine();

						} catch (InputMismatchException e) {
							tieneTitular2 = 3;
							System.err.println("Sólo se aceptan dígitos! Volviendo al menú principal...");
						}

						if (tieneTitular2 == 1) {
							if (bbddPersonas.isEmpty()) {
								System.err.println("La base de datos de personas está vacía.");
								rspCreateVehicleMenu = 4;
								break;
							}

							System.out.println("Dime tu nº de registro: ");
							int nRegistro;
							try {
								nRegistro = sc.nextInt();
								sc.nextLine();
							} catch (InputMismatchException e) {
								nRegistro = 0;
								System.err.println("Sólo los dígitos son válidos para el campo: nº registro");
							}

							VehicleHolder vh = (VehicleHolder) findPersonById(nRegistro);

							if (!vh.getLicense().validateTypeOfLicense("A")) {
								System.err.println("No coinciden los tipos de Licencia");
								rspCreateVehicleMenu = 4;
								break;
							}

							try {
								Bike bike = createBikeForHolder(vh);
								bbddVehicles.add(bike);
								System.out.println("Moto creado. ID del vehículo: " + bike.getIdVehicle());
								break;
							}catch(Exception e) {
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}

						} else if (tieneTitular2 == 2) {
							VehicleHolder vehicleHolder = createHolderForLicense("A");
							bbddPersonas.add(vehicleHolder);
							System.out.println(
									"Titular creado: " + vehicleHolder.getName() + " " + vehicleHolder.getSurName());
							System.out.println("Tu nº de registro es: " + vehicleHolder.getIdPersona());

							try {
								Bike bike = createBikeForHolder(vehicleHolder);
								bbddVehicles.add(bike);
								System.out.println("Moto creada. ID del vehículo: " + bike.getIdVehicle());
								break;
							}catch(Exception e) {
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}
							
						} else {
							System.out.println("Ha habido un error, saliendo al menú principal");
							rspCreateVehicleMenu = 4;
							break;
						}
					case 3: // CAMIONES
						System.out.println("Tiene titular el vehículo que quieres crear?\n 1.SI\n 2.NO");
						int tieneTitular3;

						try {
							tieneTitular3 = sc.nextInt();
							sc.nextLine();
						} catch (InputMismatchException e) {
							tieneTitular3 = 3;
							System.err.println("Sólo se aceptan dígitos! Volviendo al menú principal...");
						}

						if (tieneTitular3 == 1) {
							if (bbddPersonas.isEmpty()) {
								System.err.println("La base de datos de personas está vacía");
								rspCreateVehicleMenu = 4;
								break;
							}

							System.out.println("Dime tu nº de registro: ");
							
							int nRegistro;
							try {
								nRegistro = sc.nextInt();
								sc.nextLine();
							} catch (InputMismatchException e) {
								nRegistro = 0;
								System.err.println("Sólo los dígitos son válidos para el campo: nº registro");
							}
							VehicleHolder vh = (VehicleHolder) findPersonById(nRegistro);

							if (!vh.getLicense().validateTypeOfLicense("A")) {
								System.err.println("No coinciden los tipos de Licencia");
								rspCreateVehicleMenu = 4;
								break;
							}

							try{
								Truck truck = createTruckForHolder(vh);
								bbddVehicles.add(truck);
								System.out.println("Camion creado. ID del vehículo: " + truck.getIdVehicle());
								break;
							}catch(Exception e) {
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}
							

						} else if (tieneTitular3 == 2) {
							VehicleHolder vehicleHolder = createHolderForLicense("C");
							bbddPersonas.add(vehicleHolder);
							System.out.println(
									"Titular creado: " + vehicleHolder.getName() + " " + vehicleHolder.getSurName());
							System.out.println("Tu nº de registro es: " + vehicleHolder.getIdPersona());

							Truck truck;
							try {
								truck = createTruckForHolder(vehicleHolder);
								bbddVehicles.add(truck);
								System.out.println("Camion creado. ID del vehículo: " + truck.getIdVehicle());
								break;
							} catch (InvalidPlateException | InvalidWheelDiameterException
									| InvalidNumberOfTruckWheelsException e) {
								e.printStackTrace();
								rspCreateVehicleMenu = 4;
								break;
							}
							
						} else {
							System.out.println("Ha habido un error, saliendo al menú principal");
							rspCreateVehicleMenu = 4;
							break;
						}
					case 4:
						System.out.println("Volviendo al menú principal...");
						break;

					default:
						throw new IllegalArgumentException("Unexpected value: " + rspCreateVehicleMenu);
					}
				} while (rspCreateVehicleMenu != 4);

			} // FIN WHILE VEHICLES
				break;

			case 3: {
				
				System.out.println("Muchas gracias. Hasta otra!");
				
				break; // CASE FIN MENU PRINCIPAL

			}

			}
		} while (rspMainMenu != 3);
	}

	public static Wheel pedirRueda() throws InvalidWheelDiameterException {

		Wheel rueda;

		System.out.println("De qué marca quieres la rueda: ");
		String marcaRueda = sc.nextLine();

		System.out.println("Diámetro de la rueda (Ha de ser superior a 0,4 e inferior a 4: ");
		double diametroRueda = sc.nextDouble();
		sc.nextLine();

		rueda = new Wheel(marcaRueda, diametroRueda);

		return rueda;
	}

	public static boolean isAlsoDriver() {

		boolean isDriver;
		System.out.println("Además del titular del vehículo, eres el conductor del mismo? ");
		String rsp3 = sc.nextLine();

		isDriver = (rsp3.toLowerCase().equals("si")) ? true : false;

		return isDriver;
	}

	public static VehicleHolder createHolder() {

		System.out.println("Datos para la identificación... ");
		System.out.println("Introduce tu nombre: [A-Z]");
		String nameHolder = sc.nextLine();

		System.out.println("Introduce tu apellido: [A-Z]");
		String surNameHolder = sc.nextLine();

		System.out.println("Año de nacimiento: [0-10]");
		int yearHolder = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: [0-10]");
		int monthHolder = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes: [0-10]");
		int dayHolder = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDateHolder = new GregorianCalendar(yearHolder, monthHolder, dayHolder);

		License licenseHolder = null;
		String typeLicenseHolder = "";
		do {
			try {
				System.out.println("De acuerdo " + nameHolder
						+ ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: [0-10]");
				int idLicenseHolder = sc.nextInt();
				sc.nextLine();

				System.out.println("Escribe tu tipo de licencia: A, B ó C");
				typeLicenseHolder = sc.nextLine().toUpperCase();

				System.out.println("Nombre completo de la licencia: ");
				String nameLicenseHolder = sc.nextLine();

				licenseHolder = new License(idLicenseHolder, typeLicenseHolder, nameLicenseHolder);
			} catch (InvalidTypeOfLicenseException e) {
				e.printStackTrace();
			}

		} while (!licenseHolder.validateTypeOfLicense(typeLicenseHolder));

		System.out.println("Tienes seguro?");
		boolean hasInsurance;
		String rsp1 = sc.nextLine();
		hasInsurance = rsp1.toLowerCase().equals("si") ? true : false;

		boolean hasGarage;
		System.out.println("Tienes garage?");
		String rsp2 = sc.nextLine();
		hasGarage = rsp2.toLowerCase().equals("si") ? true : false;

		VehicleHolder vehicleHolder = new VehicleHolder(nameHolder, surNameHolder, birthDateHolder, licenseHolder,
				hasInsurance, hasGarage);

		return vehicleHolder;
	}

	public static VehicleHolder createHolderForLicense(String typeOfLicense) {

		System.out.println("Datos para la identificación: ");
		System.out.println("Introduce tu nombre: ");
		String nameHolder = sc.nextLine();

		System.out.println("Introduce tu apellido: ");
		String surNameHolder = sc.nextLine();

		System.out.println("Año de nacimiento: [0-10]");
		int yearHolder = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: [0-10]");
		int monthHolder = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes: [0-10]");
		int dayHolder = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDateHolder = new GregorianCalendar(yearHolder, monthHolder, dayHolder);

		License licenseHolder = null;
		String typeLicenseHolder = "";

		do {
			try {
				System.out.println("De acuerdo " + nameHolder
						+ ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: [0-10]");
				int idLicenseHolder = sc.nextInt();
				sc.nextLine();

				System.out.println("Necesitas una licencia de tipo " + typeOfLicense
						+ " para conducir este vehículo. Escribe tu tipo de licencia: A, B ó C");
				typeLicenseHolder = sc.nextLine().toUpperCase();

				while (!typeLicenseHolder.toUpperCase().equals(typeOfLicense.toUpperCase())) {
					System.out.println("Tu licencia: " + typeLicenseHolder + " no coincide con: " + typeOfLicense
							+ "\nIntroduce el tipo de licencia: ");
					typeLicenseHolder = sc.nextLine();
				}

				System.out.println("Nombre completo de la licencia: ");
				String nameLicenseHolder = sc.nextLine();

				licenseHolder = new License(idLicenseHolder, typeLicenseHolder, nameLicenseHolder);
			} catch (InvalidTypeOfLicenseException e) {
				e.printStackTrace();
			}
			
		} while (!licenseHolder.validateTypeOfLicense(typeLicenseHolder));

		System.out.println("Tienes seguro?");
		boolean hasInsurance;
		String rsp1 = sc.nextLine();
		hasInsurance = rsp1.toLowerCase().equals("si") ? true : false;

		boolean hasGarage;
		System.out.println("Tienes garage?");
		String rsp2 = sc.nextLine();
		hasGarage = rsp2.toLowerCase().equals("si") ? true : false;

		VehicleHolder vehicleHolder = new VehicleHolder(nameHolder, surNameHolder, birthDateHolder, licenseHolder,
				hasInsurance, hasGarage);

		return vehicleHolder;
	}

	public static Driver createDriver() {

		System.out.println("Datos para la identificación: ");
		System.out.println("Introduce tu nombre: ");
		String nameDriver = sc.nextLine();

		System.out.println("Introduce tu apellido: ");
		String surNameDriver = sc.nextLine();

		System.out.println("Año de nacimiento: [0-10]");
		int yearDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: [0-10]");
		int monthDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes: [0-10]");
		int dayDriver = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDateDriver = new GregorianCalendar(yearDriver, monthDriver, dayDriver);

		License licenseDriver = null;
		String typeLicenseDriver = "";
		do {
			try {
				System.out.println("De acuerdo " + nameDriver
						+ ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: [0-10]");
				int idLicenseDriver = sc.nextInt();
				sc.nextLine();

				System.out.println("Escribe tu tipo de licencia: A, B ó C");
				typeLicenseDriver = sc.nextLine().toUpperCase();

				System.out.println("Nombre completo de la licencia: ");
				String nameLicenseDriver = sc.nextLine();

				licenseDriver = new License(idLicenseDriver, typeLicenseDriver, nameLicenseDriver);
			} catch (InvalidTypeOfLicenseException e) {
				e.printStackTrace();
			}
		} while (!licenseDriver.validateTypeOfLicense(typeLicenseDriver));

		Driver driver = new Driver(nameDriver, surNameDriver, birthDateDriver, licenseDriver);

		return driver;
	}

	public static Driver createDriverForLicense(String typeOfLicense) throws InvalidTypeOfLicenseException {

		System.out.println("Datos para la identificación: ");
		System.out.println("Introduce tu nombre: ");
		String nameDriver = sc.nextLine();

		System.out.println("Introduce tu apellido: ");
		String surNameDriver = sc.nextLine();

		System.out.println("Año de nacimiento: [0-10]");
		int yearDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: [0-10]");
		int monthDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes: [0-10]");
		int dayDriver = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDateDriver = new GregorianCalendar(yearDriver, monthDriver, dayDriver);

		License licenseDriver =null; String typeLicenseDriver="";
		
		do {
			try {
				System.out.println("De acuerdo " + nameDriver
						+ ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: [0-10]");
				int idLicenseDriver = sc.nextInt();
				sc.nextLine();

				System.out.println("Necesitas una licencia de tipo " + typeOfLicense
						+ " para conducir este vehículo. Escribe tu tipo de licencia: A, B ó C");
				typeLicenseDriver = sc.nextLine().toUpperCase();
				
				System.out.println("Nombre completo de la licencia: ");
				String nameLicenseDriver = sc.nextLine();
				
				licenseDriver = new License(idLicenseDriver, typeLicenseDriver, nameLicenseDriver);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}while(!licenseDriver.validateTypeOfLicense(typeLicenseDriver));

		Driver driver = new Driver(nameDriver, surNameDriver, birthDateDriver, licenseDriver);

		return driver;
	}

	public static Persona findPersonById(int id) {

		for (Persona p : bbddPersonas) {
			
			if (p.getIdPersona() == id)
				return p;
		}
		return null;
	}

	public static Car createCarForHolder(VehicleHolder vehicleHolder) throws Exception {

		System.out.println("Introduce la matrícula del coche. Ha de tener 4 dígitos y dos o tres letras: ");
		String matriculaCoche = sc.nextLine();

		System.out.println("Introduce ahora la marca: ");
		String marcaCoche = sc.nextLine();

		System.out.println("Especifica el color del coche: ");
		String colorCoche = sc.nextLine();

		Car car = new Car(matriculaCoche, marcaCoche, colorCoche, vehicleHolder);

		System.out.println("Ahora vamos a ponerle las ruedas delanteras...");
		Wheel ruedaDelantera = pedirRueda();

		System.out.println("Vamos con las traseras...");
		Wheel ruedaTrasera = pedirRueda();

		List<Wheel> ruedasDelanteras = new ArrayList<>();
		ruedasDelanteras.add(ruedaDelantera);
		ruedasDelanteras.add(ruedaDelantera);

		List<Wheel> ruedasTraseras = new ArrayList<>();
		ruedasTraseras.add(ruedaTrasera);
		ruedasTraseras.add(ruedaTrasera);

		car.addWheels(ruedasDelanteras, ruedasTraseras);

		System.out.println(car.toString());

		return car;
	}

	public static Bike createBikeForHolder(VehicleHolder vehicleHolder) throws Exception {

		System.out.println("Introduce la matrícula de la moto. Ha de tener 4 dígitos y dos o tres letras: ");
		String matriculaMoto = sc.nextLine();

		System.out.println("Introduce la marca: ");
		String marcaMoto = sc.nextLine();

		System.out.println("Color de la moto: ");
		String colorMoto = sc.nextLine();

		Bike bike = new Bike(matriculaMoto, marcaMoto, colorMoto, vehicleHolder);

		System.out.println("Ahora vamos a ponerle las ruedas delanteras...");
		Wheel ruedaDelantera = pedirRueda();

		System.out.println("Vamos con las traseras...");
		Wheel ruedaTrasera = pedirRueda();

		bike.addWheels(ruedaDelantera, ruedaTrasera);

		System.out.println(bike.toString());

		return bike;
	}

	public static Truck createTruckForHolder(VehicleHolder vehicleHolder) throws InvalidPlateException, InvalidWheelDiameterException, InvalidNumberOfTruckWheelsException{

		System.out.println("Introduce la matrícula del camión.  Ha de tener 4 dígitos y dos o tres letras: ");
		String matriculaCamion = sc.nextLine();

		System.out.println("Introduce ahora la marca: ");
		String marcaCamion = sc.nextLine();

		System.out.println("Especifica el color del camión: ");
		String colorCamion = sc.nextLine();

		Truck truck = new Truck(matriculaCamion, marcaCamion, colorCamion, vehicleHolder);

		System.out.println("Cuántas ruedas tiene el camión: 4, 6, 8, 10 ó 12 ?");

		int ruedasTotalesCamion = sc.nextInt();
		sc.nextLine();

		List<Wheel> ruedasCamion = new ArrayList<>();

		System.out.println("Necesitaré la marca y diámetro de las ruedas... ");

		for (int i = 0; i < ruedasTotalesCamion / 2; i++) {

			System.out.println("Pidiendo par de ruedas nº " + (i + 1));
			Wheel rueda = pedirRueda();
			ruedasCamion.add(rueda);
			ruedasCamion.add(rueda);
		}

		truck.addWheels(ruedasCamion);

		System.out.println("Número correcto de ruedas: " + truck.suitableNumberOfWheels());

		System.out.println(truck.toString());

		return truck;
	}
}
