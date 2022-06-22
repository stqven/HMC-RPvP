package me.invis.hibe.dailysystem;

import java.util.concurrent.TimeUnit;

public class TimeManager {

	public static int getCurrentTime() {
		long timeMillis = System.currentTimeMillis();
		long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
		int timeSecond = (int) timeSeconds;
		return timeSecond;
	}

	public static String getStringRankRemainingTimeDaily(String playername) {
		String remainingTime = "";
		long current = System.currentTimeMillis();
		long end = (DailySQL.getTimeDaily(playername) * 1000L);
		long seconds = end / 1000L - current / 1000L;
		int minutes = (int) (seconds / 60L);
		int hours = minutes / 60;
		int days = hours / 24;
		seconds -= minutes * 60;
		minutes -= hours * 60;
		hours -= days * 24;
		if (minutes < 1) {
			return String.valueOf(seconds + " second(s)");
		}
		if (hours < 1) {
			return String.valueOf(minutes + " minute(s) " + seconds + " second(s)");
		}
		if (days < 1) {
			return String.valueOf(hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
		}
		if (days > 1) {
			return String.valueOf(
					days + " day(s) " + hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
		}
		if (seconds == -1) {
			return String.valueOf("0 second(s)");
		}
		return remainingTime;
	}
	
	public static String getStringRankRemainingTimeWeekly(String playername) {
		String remainingTime = "";
		long current = System.currentTimeMillis();
		long end = (DailySQL.getTimeWeekly(playername) * 1000L);
		long seconds = end / 1000L - current / 1000L;
		int minutes = (int) (seconds / 60L);
		int hours = minutes / 60;
		int days = hours / 24;
		seconds -= minutes * 60;
		minutes -= hours * 60;
		hours -= days * 24;
		if (minutes < 1) {
			return String.valueOf(seconds + " second(s)");
		}
		if (hours < 1) {
			return String.valueOf(minutes + " minute(s) " + seconds + " second(s)");
		}
		if (days < 1) {
			return String.valueOf(hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
		}
		if (days > 1) {
			return String.valueOf(
					days + " day(s) " + hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
		}
		if (seconds == -1) {
			return String.valueOf("0 second(s)");
		}
		return remainingTime;
	}
}
