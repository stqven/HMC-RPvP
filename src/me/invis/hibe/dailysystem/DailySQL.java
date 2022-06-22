package me.invis.hibe.dailysystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DailySQL {
	public static boolean exists(String playername) {
		ResultSet rs = MySQL.getResult("SELECT * FROM DailySQL WHERE Playername='" + playername + "'");
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void putDaily(String playername, int time) {
		if (!exists(playername)) {
			MySQL.update("INSERT INTO DailySQL(Playername, Daily) VALUES('" + playername + "','" + time + "')");
		} else {
			MySQL.update("UPDATE DailySQL SET Daily='" + time + "' WHERE Playername='" + playername + "'");
		}
	}
	
	public static void putWeekly(String playername, int time) {
		if (!exists(playername)) {
			MySQL.update("INSERT INTO DailySQL(Playername, Weekly) VALUES('" + playername + "','" + time + "')");
		} else {
			MySQL.update("UPDATE DailySQL SET Weekly='" + time + "' WHERE Playername='" + playername + "'");
		}
	}

	public static int getRankRemainingTimeDaily(String playername) {
		if (!DailySQL.exists(playername)) {
			return 0;
		}

		if (DailySQL.getTimeDaily(playername) == 1) {
			return 1;
		}
		int remaining = (DailySQL.getTimeDaily(playername) - TimeManager.getCurrentTime());
		return remaining;
	}
	
	public static int getRankRemainingTimeWeekly(String playername) {
		if (!DailySQL.exists(playername)) {
			return 0;
		}

		if (DailySQL.getTimeWeekly(playername) == 1) {
			return 1;
		}
		int remaining = (DailySQL.getTimeWeekly(playername) - TimeManager.getCurrentTime());
		return remaining;
	}

	public static int getTimeDaily(String playername) {
		ResultSet rs = MySQL.getResult("SELECT * FROM DailySQL WHERE Playername='" + playername + "'");
		try {
			if (rs.next())
				return rs.getInt("Daily");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getTimeWeekly(String playername) {
		ResultSet rs = MySQL.getResult("SELECT * FROM DailySQL WHERE Playername='" + playername + "'");
		try {
			if (rs.next())
				return rs.getInt("Weekly");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}