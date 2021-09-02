package database;

public class DayTime {
	
	private String daysMet;
	private String startTime;
	private String endTime;
	
	public DayTime(String days, String start, String end) {
		setDaysMet(days);
		setStartTime(start);
		setEndTime(end);
	}

	public String getDaysMet() {
		return daysMet;
	}

	public void setDaysMet(String daysMet) {
		this.daysMet = daysMet;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
