package server.vehicleDAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import server.domainClasses.Log;
import server.domainClasses.Sensor;
import server.domainClasses.Vehicle;



  

 public class VehicleDAO {
	private Connection con = null;	
	
	public void connect() throws SQLException{
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.out.println("Unable to load Driver Class");
		}
		String url = "jdbc:sqlite:db/Vehicle.db";
		con = DriverManager.getConnection(url,"(Login)","(Password)");
	}
	
	public Vehicle getVehicleInformation() throws SQLException{
			
		Vehicle vehicle=new Vehicle();
		
		String query = "SELECT  SENSOR.ID_SENSOR, SENSOR.DESCRIPTION, SENSOR.STATE, LOG.DATE,LOG.CODE, LOG.HOUR,LOG.COORDINATES,LOG.VALUE FROM LOG,SENSOR where " +
		"LOG.ID_SENSOR=SENSOR.ID_SENSOR ORDER BY 1 ASC";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		Sensor sen = null;
		Log log = null;
		while (rs.next()){
			if ((sen != null) && (sen.getID_sensor().equals(rs.getString(1)))){
				    log=new Log (rs.getString(5), rs.getString (4),rs.getString (6),rs.getString(7),rs.getString(8));
					sen.addLog(log);
			}
			else {
				if (sen != null)
					vehicle.addSensor(sen);
				sen=new Sensor (rs.getString(1),rs.getString(2),rs.getString(3));
				log=new Log (rs.getString(5), rs.getString (4),rs.getString (6),rs.getString(7),rs.getString(8));
				sen.addLog(log);}
		}
		vehicle.addSensor(sen);
		rs.close();
		stmt.close();
		
		return vehicle;
	}
	
	public boolean setSensorState(String ID, String state) throws SQLException{
		ID.toUpperCase();
		state.toUpperCase();
		if (state.equals("ON") || state.equals("OFF")){
			Statement stmt = con.createStatement();
			String update = "UPDATE SENSOR SET STATE='" + state + "' WHERE ID_SENSOR='" + ID + "'";
			//String update = "UPDATE SENSOR SET STATE='ON' WHERE ID_SENSOR='S1'";
			stmt.executeUpdate(update);
			stmt.close();
			return true;
		}else return false;
	}
	
	public void disconnect()throws SQLException{
		con.close();
	}

	
}



