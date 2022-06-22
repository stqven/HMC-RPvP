package me.invis.hibe.dailysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.invis.hibe.RedstonePVP;

public class MySQL {
	@SuppressWarnings("unused")
	private String HOST;
	@SuppressWarnings("unused")
	private String DATABASE;
	@SuppressWarnings("unused")
	private String USER;
	@SuppressWarnings("unused")
	private String PASSWORD;
	public static Connection con;

	public MySQL(String host, String database, String user, String password) {
		connect();
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static void connect() {
		if (!isConnected())
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://" + RedstonePVP.getInstance().getConfig().getString("MySQL.Host") + ":"
								+ RedstonePVP.getInstance().getConfig().getString("MySQL.Port") + "/"
								+ RedstonePVP.getInstance().getConfig().getString("MySQL.Database") + "?autoReconnect=true",
						RedstonePVP.getInstance().getConfig().getString("MySQL.Username"),
						RedstonePVP.getInstance().getConfig().getString("MySQL.Password"));
			     update("CREATE TABLE IF NOT EXISTS DailySQL(Playername VARCHAR(100), Daily int, Weekly int)");
				Bukkit.getConsoleSender().sendMessage(RedstonePVP.prefix + "§aMySQL connection enabled succesfully");
				
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(RedstonePVP.prefix + "§cMySQL connection failed!");
			}
	}

	public static void close() {
		if (isConnected())
			try {
				con.close();
				Bukkit.getConsoleSender().sendMessage(RedstonePVP.prefix + "§aMySQL connection closed succesfully!");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(RedstonePVP.prefix + "§cMySQL connection failed!");
			}
	}

	public static void update(String qry) {
		if (isConnected())
			try {
				PreparedStatement pt = con.prepareStatement(qry);
				pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static ResultSet getResult(String sql) {
		if (isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void createTables() {
		if (isConnected())
			try {
				con.createStatement()
						.executeUpdate("CREATE TABLE IF NOT EXISTS FFAGAME (Playername VARCHAR(100), Kills int, "
								+ "Deaths int, Points int)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static ResultSet query(String s) {
		ResultSet executeQuery = null;
		try {
			executeQuery = con.createStatement().executeQuery(s);
		} catch (SQLException ex) {
			connect();
			System.err.println(ex);
		}
		return executeQuery;
	}

	public static boolean isConnected1() {
		return con != null;
	}

	public static Connection getConnection() {
		return con;
	}

	public static PreparedStatement getStatement(String sql) {
		if (isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				return ps;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}