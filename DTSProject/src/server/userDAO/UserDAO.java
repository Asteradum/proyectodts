package server.userDAO;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Statement;
	
	public class UserDAO implements IUserDAO{
		Connection con= null;
		
		public void connect() throws SQLException{
			try{
				Class.forName("org.sqlite.JDBC");
			}catch(ClassNotFoundException e){
				System.out.println("Unable to load Driver Class");
			}
			String url = "jdbc:sqlite:db/users.db";
			con = DriverManager.getConnection(url,"(Login)","(Password)");
		}
		
		public String getUserPassword(String user) throws SQLException{
			String pass="";
			String query = "select password from Users where  User='"+user+"'";
			Statement stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			pass=rs.getString(1);
			rs.close();
			stmt.close();
			return pass;
		}
		
		public void changeUserName(String user, String newUserName) throws SQLException{
			Statement stmt = con.createStatement();
			String update = "UPDATE USERS SET NAME='" + newUserName + "' WHERE USER='" + user + "'";
			stmt.executeUpdate(update);
			stmt.close();
		}
		
		public void changeUserNick(String user, String newUserNick) throws SQLException{
			Statement stmt = con.createStatement();
			String update = "UPDATE USERS SET USER='" + newUserNick + "' WHERE USER='" + user + "'";
			stmt.executeUpdate(update);
			stmt.close();
		}
		
		public void changeUserPassWord(String user, String newUserPassword) throws SQLException{
			Statement stmt = con.createStatement();
			String update = "UPDATE USERS SET PASSWORD='" + newUserPassword + "' WHERE USER='" + user + "'";
			stmt.executeUpdate(update);
			stmt.close();
		}
			

		public void disconnect()throws SQLException{
			con.close();
		}

		
	}
