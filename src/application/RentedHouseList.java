package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RentedHouseList implements Serializable, Iterable<RentedHouse> {

	private static final long serialVersionUID = 1L;
	private List<RentedHouse> rentedHouseList;

	public RentedHouseList() {
		rentedHouseList = new ArrayList<RentedHouse>();
	}

	public List<RentedHouse> getRented() {
		return rentedHouseList;
	}

	public void addRentedHouse(RentedHouse h) {
		rentedHouseList.add(h);
	}

	@Override
	public Iterator<RentedHouse> iterator() {
		// TODO Auto-generated method stub
		return this.rentedHouseList.iterator();
	}
}
