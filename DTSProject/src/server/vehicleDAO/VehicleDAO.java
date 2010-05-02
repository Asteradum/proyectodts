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
	
	public Vehicle getVehicleInformation(String vehicleCode) throws SQLException{
			
		Vehicle vehicle=new Vehicle();
		
		String query = "SELECT VEHICLE.ID_VEHICLE, VEHICLE.STATE FROM VEHICLE, INFORMATION, SENSOR, LOG where  VEHICLE.ID_VEHICLE='"+vehicleCode+"'" +
						" and VEHICLE.ID_VEHICLE=INFORMATION.ID_VEHICLE AND SENSOR.ID_SENSOR=INFORMATION.ID_SENSOR AND" +
						" LOG.CODE=INFORMATION.CODE";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		vehicle.setID_vehicle(rs.getString(1));
		vehicle.setState(rs.getString(2));
		
			String query2 = "SELECT  SENSOR.ID_SENSOR, SENSOR.DESCRIPTION, SENSOR.STATE, LOG.DATE,LOG.CODE, LOG.HOUR,LOG.COORDINATES,LOG.VALUE FROM LOG,SENSOR, INFORMATION, VEHICLE where  VEHICLE.ID_VEHICLE='"+vehicleCode+"'" +
			" and VEHICLE.ID_VEHICLE=INFORMATION.ID_VEHICLE AND SENSOR.ID_SENSOR=INFORMATION.ID_SENSOR AND" +
			" LOG.CODE=INFORMATION.CODE ORDER BY 1 ASC";
			ResultSet rs2 = stmt.executeQuery(query2);
			Sensor sen = null;
			Log log = null;
			while (rs2.next()){
				if ((sen != null) && (sen.getID_sensor().equals(rs2.getString(1)))){
					    log=new Log (rs2.getString(5), rs2.getString (4),rs2.getString (6),rs2.getString(7),rs2.getString(8));
						sen.addLog(log);
				}
				else {
					if (sen != null)
						vehicle.addSensor(sen);
					sen=new Sensor (rs2.getString(1),rs2.getString(2),rs2.getString(3));
					log=new Log (rs2.getString(5), rs2.getString (4),rs2.getString (6),rs2.getString(7),rs2.getString(8));
					sen.addLog(log);}
			}
			vehicle.addSensor(sen);
			rs2.close();
		
		
		rs.close();
		
		stmt.close();
		
		vehicle.setLocation("4°41'24.14-74°02'46.86");
		
		return vehicle;
	}
	
	public boolean setSensorState(String ID, String state) throws SQLException{
		if (state.equals("ON") || state.equals("OFF")){
			Statement stmt = con.createStatement();
			String update = "UPDATE SENSOR SET STATE='" + state + "' WHERE ID_SENSOR='" + ID + "'";
			stmt.executeUpdate(update);
			stmt.close();
			return true;
		}else return false;
	}
	
	public boolean setGPSState( String ID, String state) throws SQLException{
		if (state.equals("ON") || state.equals("OFF")){
			Statement stmt = con.createStatement();
			String update = "UPDATE VEHICLE SET STATE='" + state + "' WHERE ID_VEHICLE='" + ID + "'";
			stmt.executeUpdate(update);
			stmt.close();
			return true;
		}else return false;
	}

	public void disconnect()throws SQLException{
		con.close();
	}

	
}



