package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerList implements Serializable, Iterable<Customer>{

	/**
	 * defines a list of all students
	 */
	
	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;

	public CustomerList() {
		customerList = new ArrayList<Customer>();
	}

	public List<Customer> getCustomers() {
		return customerList;
	}

	public void addCustomer(Customer c) {
		customerList.add(c);
	}

	@Override
	public Iterator<Customer> iterator() {
		// TODO Auto-generated method stub
		return this.customerList.iterator();
	}

}
