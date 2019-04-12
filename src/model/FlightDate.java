package model;

import java.util.Date;

public class FlightDate extends Date {

	private static final long serialVersionUID = 1L;
	
	private long dateMillis;
	
	public FlightDate(long fDate) {
		super(fDate);
		this.dateMillis = fDate;
	}
	
	@Override
	public String toString() {
		String[] date = super.toString().split(" ");
		return date[1] + " " + date[2] + " " + date[5];
	}
	
	public boolean equals(FlightDate date) {
		return date.toString().equals(toString());
	}
	
	public long getDateMillis() {
		return dateMillis;
	}
	
}
