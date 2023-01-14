package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HouseList implements Serializable, Iterable<House>{

	/**
	 * defines a list of all students
	 */
	
	private static final long serialVersionUID = 1L;
	private List<House> houseList;

	public HouseList() {
		houseList = new ArrayList<House>();
	}

	public List<House> getHouses() {
		return houseList;
	}

	public House get(int index)
	{
		return houseList.get(index);
	}
	
	public int size()
	{
		return houseList.size();
	}
	
	public void addHouse(House h) {
		houseList.add(h);
	}
	
	public void setHouseList(List<House> houseList) {
		this.houseList = houseList;
	}

	@Override
	public Iterator<House> iterator() {
		// TODO Auto-generated method stub
		return this.houseList.iterator();
	}

}
