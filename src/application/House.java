package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * stores information of each book
 */

public class House implements Serializable {
	private static final long serialVersionUID = 1L;
	private String listed;
	private int bedrooms;
	private int bathrooms;
	private int rent;
	private int size;
	private String postcode;	
	private double latitude;
	private double longitude;
	private String furnishingStatus;
	private String type;
	private boolean garden;
	private boolean rentStatus;
	private int id;
	
	private static int houseCount = 0;

	public House(String listed, int bedrooms, int bathrooms, int rent, int size, String postcode, double latitude,
			double longitude, String furnishingStatus, String type, boolean garden, boolean rentStatus) {
		this.listed = listed;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.rent = rent;
		this.size = size;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.furnishingStatus = furnishingStatus;
		this.type = type;
		this.garden = garden;
		this.rentStatus = rentStatus;
		this.id = houseCount++;
	}
	
	public House(String row) {
		String[] houseRow = row.split(",");    // use comma as separator
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		this.listed = houseRow[0].formatted(formatter);
		this.bedrooms = Integer.parseInt(houseRow[1]);
		this.bathrooms = Integer.parseInt(houseRow[2]);
		this.rent = Integer.parseInt(houseRow[3]);
		this.size = Integer.parseInt(houseRow[4]);
		this.postcode = houseRow[5];
		this.latitude = Double.parseDouble(houseRow[6].substring(1));
		this.longitude = Double.parseDouble(houseRow[7].substring(0, houseRow[7].length() - 1));
//		this.latitude = Double.parseDouble(houseRow[6]);
//		this.longitude = Double.parseDouble(houseRow[7]);
		System.out.println(this.latitude);
		System.out.println(this.longitude);
//		this.latitude = 0.000;
//		this.longitude = 0.000;
		this.furnishingStatus = houseRow[8];
		this.type = houseRow[9];
		this.garden = houseRow[10].equals("y")?true:false;
		this.rentStatus = houseRow[11].equals("n")?false:true;
		this.id = ++houseCount;
	}
	
	public House(House h,String type, int bedrooms, int bathrooms, int rent, int size, String postcode,String fs, String g) {
		this.listed = h.getListed();
		this.type = type;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.rent = rent;
		this.size = size;
		this.furnishingStatus = fs;
		this.id = h.getId();
		this.garden = g.equals("Yes")?true:false;
		this.postcode = postcode;
		this.rentStatus = h.getRentStatus();
		this.longitude = h.getLongitude();
		this.latitude = h.getLatitude();
		
	}

	public String getListed() {
		return listed;
	}

	public void setListed(String listed) {
		this.listed = listed;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	public String getFurnishingStatus() {
		return furnishingStatus;
	}

	public void setFurnishingStatus(String furnishingStatus) {
		this.furnishingStatus = furnishingStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isGarden() {
		return garden;
	}

	public void setGarden(boolean garden) {
		this.garden = garden;
	}

	public boolean getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(boolean rentStatus) {
		this.rentStatus = rentStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getHouseCount() {
		return houseCount;
	}

	public static void setHouseCount(int houseCount) {
		House.houseCount = houseCount;
	}

	
	@Override
	public String toString() {
		return "House [listed=" + listed + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms + ", rent=" + rent
				+ ", size=" + size + ", postcode=" + postcode + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", furnishingStatus=" + furnishingStatus + ", type=" + type + ", garden=" + garden + ", rentStatus="
				+ rentStatus + ", id=" + id + "]";
	}
	
	
	public String csvHeader() {
		return "Listed,Bedrooms,Bathrooms,Rent PCM,Size,Postcode,\"Latitude, Longitude\",Furnishing Status,Type,Garden,RentStatus";
	}
	
	public String csvData() {
		String g, rs;
		if(garden) {
			g = "y";
		}
		else {
			g = "n";
		}
		if(rentStatus) {
			rs = "y";
		}
		else {
			rs = "n";
		}
		return listed+","+bedrooms+","+bathrooms+","+rent+","+size+","+postcode+",\""+latitude+", "+longitude+"\","+furnishingStatus+","+type+","+g+","+rs;
	}
	
}
