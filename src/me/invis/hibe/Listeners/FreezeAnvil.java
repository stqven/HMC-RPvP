package me.invis.hibe.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.invis.hibe.RedstonePVP;

public class FreezeAnvil implements Listener {	
	@EventHandler
	public void onClick(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Player t = (Player) e.getRightClicked();
		if (e.getRightClicked() instanceof Player) {
			ItemStack anvil = new ItemStack(Material.ANVIL);
			ItemMeta manvil = anvil.getItemMeta();
			manvil.setDisplayName("§b§lFreezer §f§lAnvil");
			anvil.setItemMeta(manvil);			
			if (p.getInventory().getItemInHand().getType() == Material.ANVIL) {
				
				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						p.sendMessage("§8❘ §eHozexMC §8❘ §7You have freezed §6" + t.getName());
						t.sendMessage("§8❘ §eHozexMC §8❘ §7You have freezed by §c" + p.getName());
						p.getInventory().remove(Material.ANVIL);
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 3L);
				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 5L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 10L);
				
				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 15L);
				
				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.sendMessage("§8❘ §eHozexMC §8❘ §bTime§f: §64");
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 20L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 25L);
				
				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 30L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 35L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.sendMessage("§8❘ §eHozexMC §8❘ §bTime§f: §c3");
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 40L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 45L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 50L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 55L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.sendMessage("§8❘ §eHozexMC §8❘ §bTime§f: §c2");
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 60L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 65L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 70L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 75L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.sendMessage("§8❘ §eHozexMC §8❘ §bTime§f: §41");
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 80L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 85L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 90L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
					}
				}, 95L);

				Bukkit.getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
					
					
					@Override
					public void run() {
						t.teleport(t.getLocation());
						t.teleport(t.getLocation());
						t.sendMessage("§8❘ §aHozexMC §8❘ §7You are no longer frozen.");
					}
				}, 100L);
				
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().contains("anvilget")) {
			Player p = e.getPlayer();
			ItemStack anvil = new ItemStack(Material.ANVIL);
			ItemMeta manvil = anvil.getItemMeta();
			manvil.setDisplayName("§b§lFreezer §f§lAnvil");
			anvil.setItemMeta(manvil);
			if (p.hasPermission("HozexMC.Owner")) {
				p.getInventory().addItem(anvil);
			}
		}
	}
	
	@EventHandler
	public static void onMove(PlayerMoveEvent e, boolean tf) {
		e.setCancelled(tf);
	}

}
