package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightTest {
	
	private static final long DATE_REFERENCE = System.currentTimeMillis();

	private Flight flight;
	
	private void setupScenary1() {
		
	}
	
	private void setupScenary2() {
		flight = new Flight(new Time(3,15,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
	}
	
	@Test
	void testFlight() {
		setupScenary1();
		Flight f = new Flight(new Time(3,15,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
		Time t = new Time(3,15,"AM");
		FlightDate fDate = new FlightDate(DATE_REFERENCE);
		assertTrue(f.getDepartureTime().equals(t) , "The departure time hasn't been assigned correctly");
		assertTrue(f.getDate().equals(fDate) , "The date hasn't been assigned correctly");
		assertTrue(f.getCode() == 50 , "The flight code hasn't been assigned correctly");
		assertTrue(f.getAirline().equals("Avianca") , "The airline hasn't been assigned correctly");
		assertTrue(f.getDestination().equals("Pereira") , "The destination hasn't been assigned correctly");
		assertTrue(f.getGate() == 5 , "The gate hasn't been assigned correctly");
	}
	
	@Test
	void testCompareTo() {
		setupScenary2();
		Flight f1 = new Flight(new Time(3,15,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
		Flight f2 = new Flight(new Time(3,14,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
		Flight f3 = new Flight(new Time(3,16,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
		assertTrue(flight.compareTo(f1) == 0, "The comparison went wrong");
		assertTrue(flight.compareTo(f2) > 0, "The comparison went wrong");
		assertTrue(flight.compareTo(f3) < 0, "The comparison went wrong");
	}
	
	@Test
	void testGettersAndSetters() {
		setupScenary2();
		flight.setDepartureTime(new Time(3,5,"PM"));
		flight.setDate(new FlightDate(DATE_REFERENCE+50000000L));
		flight.setAirline("Wingo");
		flight.setDestination("Bogotá");
		flight.setGate(6);
		flight.setCode(46);
		assertTrue(flight.getDepartureTime().equals(new Time(3,5,"PM")) , "Get method for departure time is not returning the correct object");
		assertTrue(flight.getDate().equals(new FlightDate(DATE_REFERENCE+50000000L)), "Get method for date is not returning the correct object");
		assertTrue(flight.getCode() == 46 , "Get method for flight code is not returning the correct object");
		assertTrue(flight.getAirline().equals("Wingo") , "Get method for airline is not returning the correct object");
		assertTrue(flight.getDestination().equals("Bogotá") , "Get method for destination is not returning the correct object");
		assertTrue(flight.getGate() == 6 , "Get method for gate is not returning the correct object");
	}

}
