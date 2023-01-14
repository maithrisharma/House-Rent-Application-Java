package application;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RentedHouse implements Serializable {

	private static final long serialVersionUID = 1L;
	private House house;
	private Customer customer;
	private LocalDate startdate;
	

	private LocalDate enddate;
	
	private int rentId;
	
	private static int rentCount = 0;
	
	public RentedHouse(House house, Customer customer, LocalDate startdate, LocalDate enddate) {
		super();
		this.house = house;
		this.customer = customer;
		this.startdate = startdate;
		this.enddate = enddate;
		this.rentId = rentCount++;
	}


	public House getHouse() {
		return house;
	}
	public int getRentId() {
		return rentId;
	}


	public void setRentId(int rentId) {
		this.rentId = rentId;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	
	
	public static int getRentCount() {
		return rentCount;
	}


	public static void setRentCount(int rentCount) {
		RentedHouse.rentCount = rentCount;
	}


	public String printInvoice() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String str = "-------------------------------------     HOUSE RENT INVOICE     -------------------------------------------\n";
		System.out.println("----------------------     HOUSE RENT INVOICE     --------------------------------------");
		str += "\t\tRent Id: "+rentId +"\t\t\t"+"Customer: "+ customer.getName() +"\t\t\t";
		System.out.println("Rent Id: "+rentId);
		str += "\t\tDate: "+formatter.format(startdate) +"\n";
		System.out.print("Date: "+formatter.format(startdate));
//		str += "\t\tHouse Rented: "+ "\n\t\t";
//		System.out.println("House Rented: ");
		str += "\t\t"+house.getFurnishingStatus() + ", ";
		System.out.print(house.getFurnishingStatus());
		str += house.getType() + " with "+ house.getBedrooms()+" Bedrooms and "+house.getBathrooms()+" Bathrooms\n\t\t";
		System.out.println(house.getType());
		str += "Contract End date: "+ formatter.format(enddate) + "\n";
		System.out.println("Contract End date: "+formatter.format(enddate));
		str += "\t\tRent Per Month: \t\t\t\t\t\t\t\t\t£"+house.getRent() + "\n";
		System.out.println("Rent Per Month: "+house.getRent());
//	    long diffInDays = ChronoUnit.DAYS.between(startdate, returndate);
//	    str += "Total number of days: "+ diffInDays + "\n";
//		System.out.println("Total number of days: "+ diffInDays);
//		double hireAmountDue = diffInDays * book.getDailyRate();
//		str += "Cost of Hire: £"+ hireAmountDue + "\n";
//		System.out.println("Cost of Hire: £"+ hireAmountDue);
		int rent = house.getRent();
		str += "\t\tDeposit: \t\t\t\t\t\t\t\t\t\t\t£"+ 6*rent + "\n";
		System.out.println("Deposit: £"+ 6*rent);
		str += "\t\tAgency Fees: \t\t\t\t\t\t\t\t\t\t£"+ 0.20*rent + "\n";
		System.out.println("Agency Fees: \t\t\t\t\t\t\t\t\t£"+ rent*0.20);
		DecimalFormat format = new DecimalFormat("0.00");
		str +="----------------------------------------------------------------------------------------------------------------\n";
		str +=  "\t\tTotal amount to pay: \t\t\t\t\t\t\t\t£"+ format.format((rent+6*rent+rent*0.20));
		System.out.println("Total amount to pay: \t\t\t\t\t\t\t\t\t\t£"+ format.format((rent+6*rent+rent*0.20)));
		return str;
		//return "Invoice will be printed";
	}
	
//	public double getTotalPayment() {
////		long diffInDays = ChronoUnit.DAYS.between(startdate, returndate);
////		double hireAmountDue = diffInDays * book.getDailyRate();
////		DecimalFormat format = new DecimalFormat("0.00");
//		//return Double.parseDouble(format.format((book.getDeposit() + hireAmountDue)));	
//		return 0.0000;
//	}

}
