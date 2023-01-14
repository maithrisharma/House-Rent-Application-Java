package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;


public class DataHandler {

	private static String customerListFileName;
	private static String houseListFileName;
	private static String rentedHouseListFileName;

	public static void readProperties(Properties config) throws NumberFormatException, IOException {
		customerListFileName = config.getProperty("customerlist.file");
		houseListFileName = config.getProperty("houselist.file");
		rentedHouseListFileName = config.getProperty("rentedhouselist.file");
		
	}


	// Serialize the object to a file
	public static void doSerialize(Object obj, String outputFile) throws IOException {
		FileOutputStream fileTowrite = new FileOutputStream(outputFile);
		ObjectOutputStream objTowrite = new ObjectOutputStream(fileTowrite);
		objTowrite.writeObject(obj);
		fileTowrite.close();
	}

	// Deserialize the Java object from a given file
	public static Object doDeserialize(String inputFile) throws IOException, ClassNotFoundException {
		Object obj = new Object();
		File f = new File (inputFile);
		if (f.exists()) {
			FileInputStream fileToread = new FileInputStream(inputFile);
			ObjectInputStream objToread = new ObjectInputStream(fileToread);
			if (f.length() > 0) {
				System.out.println("Inside if length>0");
				obj = objToread.readObject();
				objToread.close();
			} else {
				System.out.println("File " + inputFile + " is empty");
			}
		}
		else {
			System.out.println("File " + inputFile + " does not exist");
		}
		return obj;
	}

	public static void writeToFile(CustomerList list) throws IOException {
		doSerialize(list, customerListFileName);
		System.out.println("The serialized objects " +"were written to "+ customerListFileName);	
	}

	public static CustomerList readCustomerList() throws IOException, ClassNotFoundException {
		CustomerList list = new CustomerList();
		Customer.setCustomerCount(0);
		Object obj;
		obj = doDeserialize(customerListFileName);
		if (obj instanceof CustomerList)
			list = (CustomerList) obj;
		System.out.println("list size: "+list.getCustomers().size());

		// display list

		if (list.getCustomers().size() > 0) {
			System.out.println("Customers in the list are: ");
			for (int i = 0; i < list.getCustomers().size(); i++) {
				System.out.println("Customer Name: " + list.getCustomers().get(i));
			}
		}
		return list;
	}


	public static void writeToFile(HouseList list) throws IOException {
		doSerialize(list, houseListFileName);
		System.out.println("The serialized objects " +"were written to "+ houseListFileName);	
	}


	public static void writeToFile(RentedHouseList list) throws IOException {
		doSerialize(list, rentedHouseListFileName);
		System.out.println("The serialized objects " +"were written to "+ rentedHouseListFileName);	
	}

	public static RentedHouseList readRentedHouseList() throws IOException, ClassNotFoundException {
		//System.out.println("Inside data handler");
		RentedHouseList list = new RentedHouseList();
		Object obj;
		obj =  doDeserialize(rentedHouseListFileName);
		if (obj instanceof RentedHouseList)
			list = (RentedHouseList) obj;
		System.out.println("list size: "+list.getRented().size());

		return list;
	}
	
	public static HouseList readHouseRentCSV() throws IOException {
		System.out.println("Inside readHouseCSV");
		HouseList houseList = new HouseList();
		BufferedReader br = new BufferedReader(new FileReader("src/House_Rent_Dataset.csv"));  
		House.setHouseCount(0);
		String line = "";  
		boolean header=true;

		while ((line = br.readLine()) != null)   //returns a Boolean value 
		{  
			//System.out.println(line);
			if(header) {
				header = false;  
				continue;
			}
			House house = new House(line);
			
			houseList.addHouse(house);
			
		}
		System.out.println(houseList.size());
		return houseList;

	}
	
	public static void writeHouseRentCSV(HouseList houseList) {
		FileWriter file;
		BufferedWriter writer;

		try {
			file = new FileWriter("src/House_Rent_Dataset.csv");
			writer = new BufferedWriter(file);
			boolean IsFirst = true;
			
			for(House h : houseList)
			{
				if(IsFirst)
				{
					//writer.write("Listed,Bedrooms,Bathrooms");
					writer.write(h.csvHeader());
					writer.newLine();
					IsFirst = false;
				}
				//writer.write("1,2,3");
				writer.write(h.csvData());
				writer.newLine();
			}
			writer.close();
			System.out.println("Successfull");
			//return new _BoolResponse(true, "Property saved successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static PlaceList readPlaceCSV() throws IOException {
		System.out.println("Inside readPlaceCSV");
		PlaceList placeList = new PlaceList();
		BufferedReader br = new BufferedReader(new FileReader("src/PlacesOfInterestDataSet.csv"));  
		PlaceOfInterest.setPlaceCount(0);
		String line = "";  
		boolean header=true;

		while ((line = br.readLine()) != null)   //returns a Boolean value 
		{  
			//System.out.println(line);
			if(header) {
				header = false;  
				continue;
			}
			PlaceOfInterest placeOfInterest = new PlaceOfInterest(line);
			
			placeList.addPlace(placeOfInterest);
			
		}
		System.out.println(placeList.size());
		return placeList;

	}
	
	public static void writePlaceCSV(PlaceList placeList) {
		FileWriter file;
		BufferedWriter writer;

		try {
			file = new FileWriter("src/PlaceOfInterest.csv");
			writer = new BufferedWriter(file);
			boolean IsFirst = true;
			
			for(PlaceOfInterest p : placeList)
			{
				if(IsFirst)
				{
					//writer.write("Listed,Bedrooms,Bathrooms");
					writer.write(p.csvHeader());
					writer.newLine();
					IsFirst = false;
				}
				//writer.write("1,2,3");
				writer.write(p.csvData());
				writer.newLine();
			}
			writer.close();
			System.out.println("Successfull");
			//return new _BoolResponse(true, "Property saved successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
