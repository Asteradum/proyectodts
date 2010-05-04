package server.domainClasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sensor {
	
	private Vehicle vehicle;
	private String ID_sensor;
	private String description;
	private String state;
	private List<Log> log=new ArrayList<Log>();
	
	public Sensor( String iDSensor, String description,
			String state) {
		super();
		ID_sensor = iDSensor;
		this.description = description;
		this.state = state;
		
	}
	public void addLog (Log lg)
	{
		log.add(lg);
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

	public void setState(String state) {
		this.state = state;
	}
	public List<Log> getLog() {
		return log;
	}
	public void setLog(List<Log> log) {
		this.log = log;
	}
	
	public List<String>  convertLogsToList ()
	{   List<String> list=new ArrayList<String>(); 
		for (int i=0;i<log.size();i++)
		{String line=log.get(i).convertToString();
		list.add(line);
		}
		return list;
	}
	
	public String convertToStringSensor ()
	{ String line=ID_sensor + ": "+ description + ": "+ state+ " ";
		return line;
	}
	
	public boolean isActivated(){
		boolean activated=true;
		
		if (state.equals("OFF")) activated = false;
		
		return activated;
	}
	
	public String GetCurrentValue(){
		String data =  new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) + ";" + "4°41'24.14-74°02'46.86;5";
		return data;
	}
}
