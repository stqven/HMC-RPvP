package me.invis.hibe.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KeepPaper implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		ItemStack s1 = new ItemStack(Material.PAPER);
		ItemMeta m1 = s1.getItemMeta();
		m1.setDisplayName("§6§lKeeping §f§lPaper");
		s1.setItemMeta(m1);
		
		if (p.getInventory().contains(s1)) {
			p.getInventory().remove(s1);
			e.setKeepInventory(true);
			e.setKeepLevel(true);
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().contains("paperget")) {
			if (e.getPlayer().hasPermission("HozexMC.Owner")) {
				ItemStack s1 = new ItemStack(Material.PAPER);
				ItemMeta m1 = s1.getItemMeta();
				m1.setDisplayName("§6§lKeeping §f§lPaper");
				s1.setItemMeta(m1);
				e.getPlayer().getInventory().addItem(s1);
			}
		}
	}

}
