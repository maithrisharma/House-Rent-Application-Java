package application;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class PlaceOfInterest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String place;
	private String postcode;	
	private double latitude;
	private double longitude;
	private int id;
	
	private static int placeCount = 0;
	
	

	public PlaceOfInterest(String place, String postcode, double latitude, double longitude) {
		super();
		this.place = place;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = ++placeCount;
	}
	
	public PlaceOfInterest(String row) {
		String[] placeRow = row.split(",");    // use comma as separator
		this.place = placeRow[0];
		
		this.postcode = placeRow[1];
		this.latitude = Double.parseDouble(placeRow[2].substring(1));
		this.longitude = Double.parseDouble(placeRow[3].substring(0, placeRow[3].length() - 1));
//		this.latitude = Double.parseDouble(houseRow[6]);
//		this.longitude = Double.parseDouble(houseRow[7]);
		System.out.println(this.latitude);
		System.out.println(this.longitude);
//		this.latitude = 0.000;
//		this.longitude = 0.000;
		
		this.id = ++placeCount;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public static int getPlaceCount() {
		return placeCount;
	}

	public static void setPlaceCount(int placeCount) {
		PlaceOfInterest.placeCount = placeCount;
	}
	
	@Override
	public String toString() {
		return "PlaceOfInterest [place=" + place + ", postcode=" + postcode + ", latitude=" + latitude + ", longitude="
				+ longitude + ", id=" + id + "]";
	}

	public String csvHeader() {
		return "Place,Postcode,Latitude/Longitude";
	}
	
	public String csvData() {
		
		return place+","+postcode+",\""+latitude+","+longitude+"\"";
	}
}
