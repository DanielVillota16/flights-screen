package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class FlightDateTest {
	
	private static final long DATE_REFERENCE = System.currentTimeMillis();

	private FlightDate flightDate;
	
	private void setupScenary1() {
		
	}
	
	private void setupScenary2() {
		flightDate = new FlightDate(DATE_REFERENCE);
	}
	
	@Test
	void testFlightDate() {
		setupScenary1();
		FlightDate fd = new FlightDate(DATE_REFERENCE);
		assertTrue(fd.getDateMillis() == DATE_REFERENCE);
	}
	
	@Test
	void testToString() {
		setupScenary2();
		Date d = new Date(DATE_REFERENCE);
		String[] date = d.toString().split(" ");
		String da = date[1] + " " + date[2] + " " + date[5];
		assertTrue(flightDate.toString().equals(da), "The toString method is returning an incorrect value");
	}
	
	@Test
	void testEquals() {
		setupScenary2();
		FlightDate d = new FlightDate(DATE_REFERENCE);
		assertTrue(flightDate.equals(d), "The equals method is doing a wrong comparison");
	}

}
