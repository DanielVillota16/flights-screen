package model;

import java.util.Comparator;

public class FlightAirlineComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		return f1.getAirline().compareTo(f2.getAirline());
	}

}
