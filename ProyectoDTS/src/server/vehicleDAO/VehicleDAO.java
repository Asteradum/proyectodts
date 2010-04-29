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
	 Connection con= null;	
	
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
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		vehicle.setID_vehicle(rs.getString(1));
		vehicle.setState(rs.getString(2));
		
			String query2 = "SELECT  SENSOR.ID_SENSOR, SENSOR.DESCRIPTION, SENSOR.STATE, LOG.DATE,LOG.CODE, LOG.HOUR,LOG.COORDINATES,LOG.VALUE FROM LOG,SENSOR, INFORMATION, VEHICLE where  VEHICLE.ID_VEHICLE='"+vehicleCode+"'" +
			" and VEHICLE.ID_VEHICLE=INFORMATION.ID_VEHICLE AND SENSOR.ID_SENSOR=INFORMATION.ID_SENSOR AND" +
			" LOG.CODE=INFORMATION.CODE ORDER BY 1 ASC";
			stmt.executeQuery(query2);
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
		//rs1.close();
		
		stmt.close();
		return vehicle;
	}
	/**
	public List<String> getOperaHouse() throws SQLException{
		List<String> lista=new ArrayList<String>();
		String query = "select OPERAHOUSE  from ReservationsT";
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){			
			String nombre = rs.getString("OPERANAME");
			lista.add(nombre); 
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	public void reserveSeat(String studName, String operaHouse, String operaName) throws SQLException {	
		
		String sentencia;

		sentencia = "INSERT INTO ReservationsT VALUES('"+ studName+ "','"+operaHouse+"','"+operaName+"','" +  new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "')";
		Statement stmt;
		stmt = con.createStatement();
		stmt.executeUpdate(sentencia);
		stmt.close();		
	}
		**/
	public void disconnect()throws SQLException{
		con.close();
	}

	
}



