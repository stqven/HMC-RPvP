package me.invis.hibe.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class warps implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("warps")) {
			Player p = (Player) sender;
			onWarpsMenu(p);
		}
		return false;
	}
	@EventHandler
	public void onChatCMD(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().toLowerCase().contains("/warps") || e.getMessage().toLowerCase().contains("/warp")) {
			
		} else if (e.getMessage().toLowerCase().contains("/warp youtuber")) {
			e.setMessage("");
			if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp YouTuber " + p.getName());
			} else {
				p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
			}
		} else if (e.getMessage().toLowerCase().contains("/warp gold")) {
			e.setMessage("");
			if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Gold " + p.getName());
			} else {
				p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
			}
		} else if (e.getMessage().toLowerCase().contains("/warp diamond")) {
			e.setMessage("");
			if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Diamond " + p.getName());
			} else {
				p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
			}
		} else if (e.getMessage().toLowerCase().contains("/warp emerald")) {
			e.setMessage("");
			if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Emerald " + p.getName());
			} else {
				p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
			}
		}
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		org.bukkit.inventory.Inventory inv = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		if (inv == p.getInventory()) return;
		if (inv.getName().equals("§8Warps")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§7HozexMC")) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§8» §5YouTuber")) {
				e.setCancelled(true);
				if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp YouTuber " + p.getName());
				} else {
					p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§8» §6Gold")) {
				e.setCancelled(true);
				if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Gold " + p.getName());
				} else {
					p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§8» §bDiamond")) {
				e.setCancelled(true);
				if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Diamond " + p.getName());
				} else {
					p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§8» §aEmerald")) {
				e.setCancelled(true);
				if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp Emerald " + p.getName());
				} else {
					p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions to enter this warp");
				}
			}
			}
		}
	
	public static void onWarpsMenu(Player p) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 27, "§8Warps");
		
		ItemStack s1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta m1 = s1.getItemMeta();
		m1.setDisplayName("§7HozexMC");
		s1.setItemMeta(m1);
		
		ItemStack s2 = new ItemStack(Material.GOLD_INGOT);
		ItemMeta m2 = s2.getItemMeta();
		m2.setDisplayName("§8» §6Gold");
		s2.setItemMeta(m2);
		
		ItemStack s3 = new ItemStack(Material.DIAMOND);
		ItemMeta m3 = s3.getItemMeta();
		m3.setDisplayName("§8» §bDiamond");
		s3.setItemMeta(m3);
		
		ItemStack s4 = new ItemStack(Material.EMERALD);
		ItemMeta m4 = s4.getItemMeta();
		m4.setDisplayName("§8» §aEmerald");
		s4.setItemMeta(m4);
		
		ItemStack s5 = new ItemStack(351);
		ItemMeta m5 = s5.getItemMeta();
		m5.setDisplayName("§8» §5YouTuber");
		s5.setItemMeta(m5);
		
		inv.setItem(0, s1);
		inv.setItem(1, s1);
		inv.setItem(2, s1);
		inv.setItem(3, s1);
		inv.setItem(4, s1);
		inv.setItem(5, s1);
		inv.setItem(6, s1);
		inv.setItem(7, s1);
		inv.setItem(8, s1);
		
		inv.setItem(9, s1);
		inv.setItem(10, s5);
		
		inv.setItem(11, s1);
		
		inv.setItem(12, s2);
		
		inv.setItem(13, s1);
		
		inv.setItem(14, s3);
		
		inv.setItem(15, s1);
		
		inv.setItem(16, s4);
		inv.setItem(17, s1);
		
		inv.setItem(18, s1);
		inv.setItem(19, s1);
		inv.setItem(20, s1);
		inv.setItem(21, s1);
		inv.setItem(22, s1);
		inv.setItem(23, s1);
		inv.setItem(24, s1);
		inv.setItem(25, s1);
		inv.setItem(26, s1);
		
		if (p.isOp() || p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Developer") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
			p.openInventory(inv);
		} else {
			p.sendMessage("§8┃ §cHozexMC §8┃ §7You don't have permissions open warps menu");
		}
	}
	
}
