package server.vehicleDAO;



import java.sql.SQLException;

import server.domainClasses.Vehicle;



public class PRUEBA2 {
	private static VehicleDAO DAO;
	
	public static void main(String[] args){
	  Vehicle ve=new Vehicle();
		DAO=new VehicleDAO();
	  try {
		DAO.connect();
		ve=DAO.getVehicleInformation();
		for (int i=0;i<ve.getSensors().size();i++)
			System.out.println(ve.getSensors().get(i).getDescription());
		if (ve.HasSensor("Humedad"))
			System.out.println("seeeeeeeeeeeeeeeeeeeeeee");
		System.out.println(ve.convertToListSensor());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	

}