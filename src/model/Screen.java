package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Screen {

	private Flight[] flights;
	
	public Screen(int numberOfFlights) throws IOException {
		this.flights = new Flight[numberOfFlights];
		for (int i = 0; i < numberOfFlights; i++) {
			flights[i] = generateRandomFlight();
		}
	}

	public Flight[] getFlights() {
		return flights;
	}

	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}

	public void sortByDepartureTime() {
		// TODO Use of Arrays.sort
		Arrays.sort(flights);
	}

	public void sortByCode() {
		// TODO use of selection sort
		for (int i = 0; i < flights.length-1; i++) {
			int minCode = flights[i].getCode();
			int min = i;
			for (int j = i+1; j < flights.length; j++) {
				int currentCode = flights[j].getCode();
				if(currentCode < minCode) {
					minCode = currentCode;
					min = j;
				}
			}
			Flight temp = flights[min];
			flights[min] = flights[i];
			flights[i] = temp;
		}
	}

	public void sortByAirline() {
		Comparator<Flight> fac = new FlightAirlineComparator();
		Arrays.sort(flights, fac);
	}

	public void sortByDestination() {
		// TODO Use of bubble sort
		for (int i = 0; i < flights.length; i++) {
			for (int j = 0; j < flights.length-i-1; j++) {
				if(flights[j].getDestination().compareTo(flights[j+1].getDestination()) > 0) {
					Flight temp = flights[j];
					flights[j] = flights[j+1];
					flights[j+1] = temp;
				}
			}
		}
	}

	public void sortByGate() {
		// TODO Use of insertion sort
		for (int i = 1; i < flights.length; i++) {
			for (int j = i; j > 0; j--) {
				if(flights[j].getGate() < flights[j-1].getGate()) {
					Flight temp = flights[j];
					flights[j] = flights[j-1];
					flights[j-1] = temp;
				}
			}
		}
	}
	
	public void sortByDate() {
		Comparator<Flight> fdc = new FlightDateComparator();
		Arrays.sort(flights, fdc);
	}

	public int searchFlightByCode(int code) {
		// TODO Use linear search to find the specified flight with the correspondant code
		int index = -1;
		for (int i = 0; i < flights.length && index == -1; i++) {
			if(code == flights[i].getCode()) {
				index = i;
			}
		}
		return index;
	}

	public int searchFlightByAirline(String airline) {
		// TODO Use binary search to find the index of the correspondent flight in the array
		sortByAirline();
		int index = -1;
		int low = 0;
		int high = flights.length-1;
		while(low <= high && index == -1) {
			int mid = (low+high)/2;
			int value = flights[mid].getAirline().compareTo(airline);
			if(value < 0) {
				low = mid+1;
			} else if(value > 0) {
				high = mid-1;
			} else {
				index = mid;
			}
		}
		return index;
	}

	public int searchFlightByGate(int gate) {
		int index = -1;
		for (int i = 0; i < flights.length && index == -1; i++) {
			if(flights[i].getGate() == gate) {
				index = i;
			}
		}
		return index;
	}

	public int searchFlightByDestination(String destination) {
		sortByDestination();
		int index = -1;
		int low = 0;
		int high = flights.length-1;
		while(low <= high && index == -1) {
			int mid = (low+high)/2;
			int value = flights[mid].getDestination().compareTo(destination);
			if(value < 0) {
				low = mid+1;
			} else if(value > 0) {
				high = mid-1;
			} else {
				index = mid;
			}
		}
		return index;
	}

	public int searchFlightByDate(String date) {
		int index = -1;
		for (int i = 0; i < flights.length && index == -1; i++) {
			if(flights[i].getDate().toString().equals(date)) {
				index = i;
			}
		}
		return index;
	}
	
	public int searchFlightByTime(String time) {
		int index = -1;
		for (int i = 0; i < flights.length && index == -1; i++) {
			if(flights[i].getDepartureTime().toString().equals(time)) {
				index = i;
			}
		}
		return index;
	}

	public Flight generateRandomFlight() throws IOException {
		int hour = (int)(Math.random()*11+1), minutes = (int)(Math.random()*60);
		String[] dayTimes = {"AM", "PM"};
		Random r = new Random();
		String dayTime = dayTimes[r.nextInt(2)];
		long dateMillis = (long)(Math.random()*2592000000L) + System.currentTimeMillis();
		int code = (int)(Math.random()*1000), gate = (int)(Math.random()*14+1);
		String airline = readAirlines()[(int)(Math.random()*readAirlines().length-1)];
		String destination = readDestinations()[(int)(Math.random()*readDestinations().length-1)];
		return new Flight(new Time(hour, minutes, dayTime), new FlightDate(dateMillis), code, airline, destination, gate);
	}
	
	public String[] readDestinations() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/destinations"));
		String line = br.readLine();
		String destinations = "";
		while(line != null) {
			destinations += line + "~";
			line = br.readLine();
		}
		String[] dest = destinations.split("~");
		br.close();
		return dest;
	}
	
	public String[] readAirlines() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/airlines"));
		String line = br.readLine();
		String airlines = "";
		while(line != null) {
			airlines += line + "~";
			line = br.readLine();
		}
		String[] airl = airlines.split("~");
		br.close();
		return airl;
	}
	
}