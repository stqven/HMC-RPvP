package me.invis.hibe.Listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;


public class Death implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player t = e.getEntity().getKiller();
		ItemStack gold = new ItemStack(Material.GOLD_INGOT, 34);
		p.getWorld().dropItemNaturally(p.getLocation(), gold);
		t.playEffect(p.getLocation(), Effect.CLOUD, 3);
		p.spigot().respawn();
	}

}
