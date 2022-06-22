package me.invis.hibe.dailysystem.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.invis.hibe.dailysystem.DailySQL;

public class JoinEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		String name = e.getPlayer().getName();
		if (!DailySQL.exists(name)) {
			DailySQL.putDaily(name, 1);
			DailySQL.putWeekly(name, 1);
		}
	}
}
