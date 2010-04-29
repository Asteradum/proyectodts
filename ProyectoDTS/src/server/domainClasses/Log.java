package server.domainClasses;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
	private String sensor;
	private String vehicle;
	private String code;
	private String date;
	private String hour;
	private String coordinates;
	private String value;
	
	
	public Log( String code,String date, String hour,
			String coordinates, String value) {
		super();
		this.code=code;
		this.date = date;
		this.hour = hour;
		this.coordinates = coordinates;
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String  convertToString ()
	{   String line=date+"; "+hour +"; " +coordinates+ "; "+ value+"; ";
		return line;
	}
	

}
