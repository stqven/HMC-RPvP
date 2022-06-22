package me.invis.hibe.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.invis.hibe.RedstonePVP;
//Play Effect on chest remove
public class DropsEvent implements Listener {
	public static boolean canDrop = true;
//	@EventHandler
//	public void onBlockClick(PlayerInteractEvent e) {
//		if (e.getClickedBlock() == null) return;
//		if (e.getClickedBlock() == null) {
//			return;
//		}
//		Player p = e.getPlayer();
//			if (e.getClickedBlock().getType() == Material.SEA_LANTERN) {
//				if (Bukkit.getServer().getOnlinePlayers().size() >= 15) {
//					if (canDrop == true) {
////						dropRandomChests();
//						p.sendMessage("§7There is not enough players to start a chests dropparty");
//					} else {
////						p.sendMessage("§7An event has been started recently, Please wait to use this again");
//						p.sendMessage("§7There is not enough players to start a chests dropparty");
//					}
//				} else {
//					p.sendMessage("§7There is not enough players to start a chests dropparty");
//				}
//			}
//	}
	
	public void dropRandomChests() {
		Location loc1 = new Location(Bukkit.getServer().getWorld("world"), -129, 33, 28);
		Location loc2 = new Location(Bukkit.getServer().getWorld("world"), -105, 41, 46);
		Location loc3 = new Location(Bukkit.getServer().getWorld("world"), -141, 33, 61);
		Location loc4 = new Location(Bukkit.getServer().getWorld("world"), -110, 38, 46);
		Location loc5 = new Location(Bukkit.getServer().getWorld("world"), -98, 41, 42);
		Location loc6 = new Location(Bukkit.getServer().getWorld("world"), -107, 25, 59);
		Location loc7 = new Location(Bukkit.getServer().getWorld("world"), -139, 18, 29);
		Location loc8 = new Location(Bukkit.getServer().getWorld("world"), -129, 42, 13);
		Location loc9 = new Location(Bukkit.getServer().getWorld("world"), -127, 39, 66);
		
		int rr = RedstonePVP.getInstance().getRandom(1, 8);
		loc1.getBlock().setType(Material.CHEST);
		if (rr == 1) {
			loc1.getBlock().setType(Material.CHEST);
			loc2.getBlock().setType(Material.CHEST);
			loc3.getBlock().setType(Material.CHEST);
			loc4.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 2) {
			loc5.getBlock().setType(Material.CHEST);
			loc6.getBlock().setType(Material.CHEST);
			loc7.getBlock().setType(Material.CHEST);
			loc8.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 3) {
			loc9.getBlock().setType(Material.CHEST);
			loc4.getBlock().setType(Material.CHEST);
			loc3.getBlock().setType(Material.CHEST);
			loc6.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 4) {
			loc8.getBlock().setType(Material.CHEST);
			loc2.getBlock().setType(Material.CHEST);
			loc5.getBlock().setType(Material.CHEST);
			loc7.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 5) {
			loc5.getBlock().setType(Material.CHEST);
			loc8.getBlock().setType(Material.CHEST);
			loc3.getBlock().setType(Material.CHEST);
			loc6.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 6) {
			loc1.getBlock().setType(Material.CHEST);
			loc5.getBlock().setType(Material.CHEST);
			loc9.getBlock().setType(Material.CHEST);
			loc7.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 7) {
			loc2.getBlock().setType(Material.CHEST);
			loc8.getBlock().setType(Material.CHEST);
			loc9.getBlock().setType(Material.CHEST);
			loc3.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else if (rr == 8) {
			loc2.getBlock().setType(Material.CHEST);
			loc3.getBlock().setType(Material.CHEST);
			loc7.getBlock().setType(Material.CHEST);
			loc6.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		} else {
			loc2.getBlock().setType(Material.CHEST);
			loc9.getBlock().setType(Material.CHEST);
			loc7.getBlock().setType(Material.CHEST);
			loc6.getBlock().setType(Material.CHEST);
			Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
				@Override
				public void run() {
					removeRandomChests();
				}
			}, 10*20L);
		}
		Bukkit.broadcastMessage("§8❘ §4RedstonePvP §8❘ §e4 §eChests §ehave been dropped randomly in the map, Chests will be removed in 20 minutes");
		canDrop = false;
	}
	
	public void removeRandomChests() {
		Location loc1 = new Location(Bukkit.getServer().getWorld("world"), -129, 33, 28);
		Location loc2 = new Location(Bukkit.getServer().getWorld("world"), -105, 41, 46);
		Location loc3 = new Location(Bukkit.getServer().getWorld("world"), -141, 33, 61);
		Location loc4 = new Location(Bukkit.getServer().getWorld("world"), -110, 38, 46);
		Location loc5 = new Location(Bukkit.getServer().getWorld("world"), -98, 41, 42);
		Location loc6 = new Location(Bukkit.getServer().getWorld("world"), -107, 25, 59);
		Location loc7 = new Location(Bukkit.getServer().getWorld("world"), -139, 18, 29);
		Location loc8 = new Location(Bukkit.getServer().getWorld("world"), -129, 42, 13);
		Location loc9 = new Location(Bukkit.getServer().getWorld("world"), -127, 39, 66);
		loc1.getBlock().setType(Material.AIR);
		loc2.getBlock().setType(Material.AIR);
		loc3.getBlock().setType(Material.AIR);
		loc4.getBlock().setType(Material.AIR);
		loc5.getBlock().setType(Material.AIR);
		loc6.getBlock().setType(Material.AIR);
		loc7.getBlock().setType(Material.AIR);
		loc8.getBlock().setType(Material.AIR);
		loc9.getBlock().setType(Material.AIR);
		Bukkit.broadcastMessage("§8❘ §4RedstonePvP §8❘ §eRandom chests have been undropped");
		canDrop = true;
	}

}
