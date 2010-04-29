package VehicleDAO;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	private String ID_vehicle;
	private String state;
	private List <Sensor> sensors=new ArrayList<Sensor>();
	
	
	public Vehicle(String id, String st, List<Sensor> sen)
	{
		this.ID_vehicle=id;
		this.state=st;
		this.sensors=sen;
	}


	public String getID_vehicle() {
		return ID_vehicle;
	}


	public void setID_vehicle(String iDVehicle) {
		ID_vehicle = iDVehicle;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public List<Sensor> getSensors() {
		return sensors;
	}


	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	
		

}
