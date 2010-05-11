package server.domainClasses;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	private String ID_vehicle = null;
	private String GPSState = null;
	private String location = null;
	private List <Sensor> sensors = new ArrayList<Sensor>();
	
	
	public Vehicle(String id, String st, List<Sensor> sen)
	{
		this.ID_vehicle=id;
		this.GPSState=st;
		this.sensors=sen;
	}

	public void addSensor (Sensor sensor)
	{
		sensors.add(sensor);
	}
	public Vehicle() {
		super();
	}


	public String getID_vehicle() {
		return ID_vehicle;
	}


	public void setID_vehicle(String iDVehicle) {
		ID_vehicle = iDVehicle;
	}



	public void setState(String state) {
		this.GPSState = state;
	}

	public String getState() {
		return this.GPSState;
	}
	
	public List<Sensor> getSensors() {
		return sensors;
	}
	
	public void setLocation(String loc) {
		location = loc;
	}
	
	public String getLocation() {
		return location;
	}


	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public List<String>  convertToListSensor ()
	{   
		List<String> list=new ArrayList<String>(); 
		for (int i=0;i<sensors.size();i++){
			String line=sensors.get(i).convertToStringSensor();
			list.add(line);
		}
		return list;
	}	
	
	public boolean HasSensor (String name)
	{ boolean sen=false;
		for (int i=0;i<sensors.size();i++)
		{
			if (sensors.get(i).getID_sensor().equals(name))
				sen=true;
		}
		return sen;
	}
	
	public Sensor getSensor (String ID){
		Sensor sen= null;
		for (int i=0;i<sensors.size();i++)
		{
			sen = sensors.get(i);
			if (sen.getID_sensor().equals(ID))
				return sen;
				
		}
		return null;
		
	}
	
	public boolean isGPSActivated(){
		boolean activated=false;
		
		if (GPSState.equals("ON")) activated = true;
		
		return activated;
		
	}

}

