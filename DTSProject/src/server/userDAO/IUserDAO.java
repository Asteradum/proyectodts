package server.userDAO;

import java.sql.SQLException;

 public interface IUserDAO {
	public void connect()throws SQLException;;

	public String getUserPassword(String User)throws SQLException;

	public void disconnect() throws SQLException;
}
