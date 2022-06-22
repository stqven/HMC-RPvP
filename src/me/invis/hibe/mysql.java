package me.invis.hibe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class mysql {
	
	  public static boolean existsInLuckperms(String Name) {
		  ResultSet Result = getResult("SELECT * FROM lp_players WHERE username='" + Name + "'");
		    try {
		      if (Result.next()) {
		        return true;
		      }
		      return false;
		    }
		    catch (SQLException localSQLException) {}
		    return false;
		  }
	  
	  public static String getPrimaryGroup(String Name) {
		  if (existsInLuckperms(Name)) {
			    String Group = "";
			      ResultSet Result = getResult("SELECT * FROM lp_players WHERE username='" + Name + "'");
			      try {
			        if (Result.next()) {
			          Group = Result.getString("primary_group");
			        }
			      }
			      catch (SQLException localSQLException) {}
			    return Group;  
		  } else {
			  return "";
		  }
	  }
	  public static Connection Conn;
	  
	  public static boolean isClosed() {
	    return Conn == null;
	  }
	  
	  public static boolean isConnected() {
	    return Conn != null;
	  }
	  
	  public static void connect() {
	    try {
	      Conn = DriverManager.getConnection("jdbc:mysql://eu.sql.titannodes.com:3306/" + "" + "?autoReconnect=true", "", "");
	      System.out.println("MySQL2 Connected!");
	    } catch (SQLException e) {
	      Conn = null;
	      System.out.println("MySQL2 Error!");
	    }
	  }
	  
	  public static void close() {
	    try {
	      Conn.close();
	      System.out.println("MySQL2 Connection closed successfuly");
	    } catch (SQLException e) {
	      Conn = null;
	      System.out.println("Failed to close MySQL2 connection");
	    }
	  }
	  
	  public static void update(String Query) {
	    try {
	      PreparedStatement P = Conn.prepareStatement(Query);
	      P.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	  public static ResultSet getResult(String Query) {
	    try {
	      PreparedStatement P = Conn.prepareStatement(Query);
	      return P.executeQuery();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
}
