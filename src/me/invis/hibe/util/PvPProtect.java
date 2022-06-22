package me.invis.hibe.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.projectiles.ProjectileSource;

public class PvPProtect implements Listener {
	
	public boolean isInNoPvP(Location ploc) {
//		if ((ploc.getBlockZ() <= -1 || (ploc.getBlockX() <= -142 && ploc.getBlockY() >= 33) || (ploc.getBlockZ() >= 75 && ploc.getBlockX() >= -127))) {
//			return true;
//		}
		return false;
	}
	
	@EventHandler
	public void onDamageF(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if (isInNoPvP(p.getLocation())) e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onEntityDamageByEntity( EntityDamageByEntityEvent evt ) {
	    // Is target a player, if not stop
	    if ( !(evt.getEntity() instanceof Player) )
	        return;
	    // Is damager an arrow, if not stop
	    if ( !(evt.getDamager() instanceof Arrow) )
	        return;

	    // Is shooter a player, if not stop
	    ProjectileSource src = ((Arrow) evt.getDamager()).getShooter();
	    if ( !(src instanceof Player) )
	        return;
	    if (evt.getDamager().getCustomName().contains("nopvp")) {
	    	evt.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onDamaqwegeFaasd(EntityDamageByEntityEvent e) {
		Player p = (Player) e.getEntity();
		if (e.getDamager() instanceof Player) {
			Player k = (Player) e.getDamager();
			if (isInNoPvP(p.getLocation()) || isInNoPvP(k.getLocation())) e.setCancelled(true);
		}
	}
}
