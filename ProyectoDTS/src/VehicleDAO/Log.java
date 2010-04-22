package VehicleDAO;

import java.util.Date;

public class Log {
	private String sensor;
	private String vehicle;
	private Date date;
	private String hour;
	private String coordinates;
	private int value;
	
	
	public Log(String sensor, String vehicle, Date date, String hour,
			String coordinates, int value) {
		super();
		this.sensor = sensor;
		this.vehicle = vehicle;
		this.date = date;
		this.hour = hour;
		this.coordinates = coordinates;
		this.value = value;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	

}
