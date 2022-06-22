package me.invis.hibe.util;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.robin.battlelevels.api.BattleLevelsAPI;

public class Death implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player k = e.getEntity().getKiller();
		if (BattleLevelsAPI.getKillstreak(p.getUniqueId()) >= 50) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.playEffect(p.getLocation(), Effect.BOW_FIRE, 5);
				all.playEffect(p.getLocation(), Effect.BOW_FIRE, 5);
				all.playEffect(p.getLocation(), Effect.COLOURED_DUST, 5);
				all.playEffect(p.getLocation(), Effect.COLOURED_DUST, 5);
				all.playEffect(p.getLocation(), Effect.COLOURED_DUST, 5);
				all.playEffect(p.getLocation(), Effect.FLAME, 5);
				all.playEffect(p.getLocation(), Effect.FLAME, 5);
				all.playEffect(p.getLocation(), Effect.FLAME, 5);
				all.playEffect(p.getLocation(), Effect.FLAME, 5);
			}
		}
	}

}
