package application;
import java.io.Serializable;
import java.time.LocalDate;

/*
 * Student class stores details of each student. The constructor generates a unique id for each student
 */


public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int customerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String gender;
	private LocalDate bdate;
	private static int customerCount = 0;

	public Customer() {
		name = "";
		email = "";
		phoneNumber="";
		bdate = null;
		gender = "";
		customerId = ++customerCount;
	
	}
	
	public Customer(int id) {
		name = "";
		email = "";
		phoneNumber = "";
		bdate = null;
		gender = "";
		customerId = id;
	}
	
	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public LocalDate getBdate() {
		return bdate;
	}



	public void setBdate(LocalDate bdate) {
		this.bdate = bdate;
	}



	public static int getCustomerCount() {
		return customerCount;
	}



	public static void setCustomerCount(int customerCount) {
		Customer.customerCount = customerCount;
	}



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", bdate=" + bdate + "]";
	}

	

	
	
}
