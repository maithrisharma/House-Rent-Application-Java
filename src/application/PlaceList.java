package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaceList implements Serializable, Iterable<PlaceOfInterest>{

	private static final long serialVersionUID = 1L;
	private List<PlaceOfInterest> placeList;
	
	public PlaceList() {
		placeList = new ArrayList<PlaceOfInterest>();
	}

	public List<PlaceOfInterest> getPlaces() {
		return placeList;
	}

	public PlaceOfInterest get(int index)
	{
		return placeList.get(index);
	}
	
	public int size()
	{
		return placeList.size();
	}
	
	public void addPlace(PlaceOfInterest p) {
		placeList.add(p);
	}
	
	public void setPlaceList(List<PlaceOfInterest> placeList) {
		this.placeList = placeList;
	}
	
	@Override
	public Iterator<PlaceOfInterest> iterator() {
		// TODO Auto-generated method stub
		return this.placeList.iterator();
	}

}
