package VehicleDAO;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
	
	private Vehicle vehicle;
	private String ID_sensor;
	private String description;
	private String state;
	private List<Log> log=new ArrayList<Log>();;
	
	public Sensor(Vehicle vehicle, String iDSensor, String description,
			String state, List<Log>log) {
		super();
		this.vehicle = vehicle;
		ID_sensor = iDSensor;
		this.description = description;
		this.state = state;
		this.log = log;
	}
	
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getID_sensor() {
		return ID_sensor;
	}
	public void setID_sensor(String iDSensor) {
		ID_sensor = iDSensor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Log> getLog() {
		return log;
	}
	public void setLog(List<Log> log) {
		this.log = log;
	}
	
	
	

}
