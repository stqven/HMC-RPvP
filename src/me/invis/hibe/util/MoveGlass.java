package me.invis.hibe.util;

import org.bukkit.event.player.PlayerMoveEvent;

import me.invis.hibe.RedstonePVP;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MoveGlass implements Listener {
//	@EventHandler
//	public void onRegion(RegionEnterEvent e) {
//		Player p = e.getPlayer();
//		ProtectedRegion rr = e.getRegion();
//		if (rr.getId().contains("r")) {
//			if (rr.getId().contains("1")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 503, 35,  38)).setData((byte) 8);
//			} else if (rr.getId().contains("2")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 503, 35,  40)).setData((byte) 8);
//			} else if (rr.getId().contains("3")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  41)).setData((byte) 8);
//			} else if (rr.getId().contains("4")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  41)).setData((byte) 8);
//			} else if (rr.getId().contains("5")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  40)).setData((byte) 8);
//			} else if (rr.getId().contains("6")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  39)).setData((byte) 8);
//			} else if (rr.getId().contains("7")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  40)).setData((byte) 8);
//			} else if (rr.getId().contains("8")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  41)).setData((byte) 8);
//			} else if (rr.getId().contains("9")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 501, 35,  39)).setData((byte) 8);
//			} else if (rr.getId().contains("10")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 501, 35,  40)).setData((byte) 8);
//			} else if (rr.getId().contains("11")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  41)).setData((byte) 8);
//			} else if (rr.getId().contains("12")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  40)).setData((byte) 8);
//			} else if (rr.getId().contains("13")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  39)).setData((byte) 8);
//			} else if (rr.getId().contains("14")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  38)).setData((byte) 8);
//			} else if (rr.getId().contains("15")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  37)).setData((byte) 8);
//			} else if (rr.getId().contains("16")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 499, 35,  38)).setData((byte) 8);
//			} else if (rr.getId().contains("17")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 499, 35,  40)).setData((byte) 8);
//			}
//		}
//	}
//	
//	@EventHandler
//	public void onRegion(RegionLeaveEvent e) {
//		Player p = e.getPlayer();
//		ProtectedRegion rr = e.getRegion();
//		if (rr.getId().contains("r")) {
//			if (rr.getId().contains("1")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 503, 35,  38)).setData((byte) 15);
//			} else if (rr.getId().contains("2")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 503, 35,  40)).setData((byte) 15);
//			} else if (rr.getId().contains("3")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  37)).setData((byte) 15);
//			} else if (rr.getId().contains("4")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  41)).setData((byte) 15);
//			} else if (rr.getId().contains("5")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  40)).setData((byte) 15);
//			} else if (rr.getId().contains("6")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  39)).setData((byte) 15);
//			} else if (rr.getId().contains("7")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  40)).setData((byte) 15);
//			} else if (rr.getId().contains("8")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 502, 35,  41)).setData((byte) 15);
//			} else if (rr.getId().contains("9")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 501, 35,  39)).setData((byte) 15);
//			} else if (rr.getId().contains("10")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 501, 35,  40)).setData((byte) 15);
//			} else if (rr.getId().contains("11")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  41)).setData((byte) 15);
//			} else if (rr.getId().contains("12")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  40)).setData((byte) 15);
//			} else if (rr.getId().contains("13")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  39)).setData((byte) 15);
//			} else if (rr.getId().contains("14")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  38)).setData((byte) 15);
//			} else if (rr.getId().contains("15")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 500, 35,  37)).setData((byte) 15);
//			} else if (rr.getId().contains("16")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 499, 35,  38)).setData((byte) 15);
//			} else if (rr.getId().contains("17")) {
//				p.getWorld().getBlockAt(new Location(p.getWorld(), 499, 35,  40)).setData((byte) 15);
//			}
//		}
//	}

}
