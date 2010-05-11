package server.userDAO;

import java.sql.SQLException;

public class prueba {
 static UserDAO dao= new UserDAO();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			dao.connect();
			dao.deleteUser("lalala");
			dao.disconnect();
     } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
