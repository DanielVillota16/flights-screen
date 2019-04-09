package model;

public class Time implements Comparable<Time> {

	private int hour;
	private int minutes;
	private String dayTime;

	public Time(int hour, int minutes, String dayTime) {
		this.hour = hour;
		this.minutes = minutes;
		this.dayTime = dayTime;
	}
	
	@Override
	public int compareTo(Time time) {
		int value = 0;
		if(hour > time.hour) {
			if(dayTime.equals("AM") && time.dayTime.equals("PM")) {
				value= -1;
			} else {
				value = 1;
			}
		} else if(hour < time.hour) {
			if(dayTime.equals("PM") && time.dayTime.equals("AM")) {
				value = 1;
			} else {
				value = -1;
			}
		} else {
			if(dayTime.equals(time.dayTime)) {
				if(minutes > time.minutes) {
					value = 1;
				} else if(minutes < time.minutes){
					value = -1;
				} else {
					value = 0;
				}
			} else if(dayTime.equals("AM") && time.dayTime.equals("PM")){
				value = -1;
			} else {
				value = 1;
			}
		}
		return value;
	}

	public int getHour() {
		return this.hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getDayTime() {
		return this.dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public String toString() {
		String minutes = this.minutes < 10 ? "0"+this.minutes:""+this.minutes;
		return hour+":"+minutes+" "+dayTime;
	}
	
	public boolean equals(Time time) {
		return time.toString().equals(toString());
	}
	
}