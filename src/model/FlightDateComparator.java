package model;

import java.util.Comparator;

public class FlightDateComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		// TODO Auto-generated method stub
		return f1.getDate().compareTo(f2.getDate());
	}
	
}
