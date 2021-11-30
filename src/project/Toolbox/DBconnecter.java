package project.Toolbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnecter {
	private static Connection connection;
	private static String username;
	private static String password;
	  private DBconnecter() {}
	  public static synchronized Connection getConnection(int gradebookfunction) throws SQLException {
	    if (connection != null) {
	      return connection;
	    } else if(gradebookfunction != 9) {
	      try {
	        // set the db url, username, and password
	        String url = "jdbc:mysql://mydatabase-1.cfy5fxgxgeq7.us-east-1.rds.amazonaws.com/Grades";
	        getlogin();
	        System.out.print("Conecting...");
	        // String dbname = "cpsc2810db1";
	        // get and return connection
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected!\n");
	        return connection;
	      } catch (SQLException | ClassNotFoundException e) {
	    	  System.out.println("Failed");
	        throw new SQLException(e);
	      }
	    } else {
	    	return(null);
	    }
	  }
	  public static synchronized void closeConnection() throws SQLException {
	    if (connection != null) {
	      try {
	        connection.close();
	      } catch (SQLException e) {
	        throw new SQLException(e);
	      } finally {
	        connection = null;
	      }
	    }
	  }
	  public static synchronized void getlogin() {
	      username = Console.getRequiredString("What is your username for the Database?: ");
	      password = Console.getRequiredString("What is your password for the Database?: ");
		  return;
	  }
}
