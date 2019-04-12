package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightDateComparatorTest {

	private static final long DATE_REFERENCE = System.currentTimeMillis();
	
	private FlightDateComparator fdc;
	
	private void setupScenary1() {
		fdc = new FlightDateComparator();
	}
	
	@Test
	void testCompare() {
		setupScenary1();
		Flight flight = new Flight(new Time(3,15,"AM"), new FlightDate(DATE_REFERENCE), 50, "Avianca", "Pereira", 5);
		Flight flight2 = new Flight(new Time(3,15,"AM"), new FlightDate(DATE_REFERENCE + 50000000L), 50, "SATENA", "Pereira", 5);
		assertTrue(fdc.compare(flight, flight2) < 0);
		assertFalse(fdc.compare(flight, flight2) == 0);
		assertFalse(fdc.compare(flight, flight2) > 1);
	}

}
