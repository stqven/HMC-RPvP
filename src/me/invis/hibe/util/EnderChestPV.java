package me.invis.hibe.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnderChestPV implements Listener {
	
	@EventHandler
	public void onEnderOpen(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				Player p = e.getPlayer();
				e.setCancelled(true);
				if (p.getAllowFlight()) {
					return;
				}
				Bukkit.getServer().dispatchCommand(p, "mvp vault 1");
			}
		}
	}

	@EventHandler
	public void onECO(InventoryOpenEvent e) {
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Vaults")) {
			for (int i = 20; i <= 33; i++) {
				if (inv.getItem(i) != null && inv.getItem(i).getType() != Material.AIR && inv.getItem(i).getType() != Material.RAILS) {
					ItemStack item = inv.getItem(i);
						ItemStack ss = new ItemStack(item.getType());
						ItemMeta mss = item.getItemMeta();
						ArrayList<String> lss = new ArrayList<String>();
						lss.add("");
						lss.add(item.getItemMeta().getLore().get(11).replaceFirst(" ", "").replaceFirst(" ", "").replaceFirst(" ", ""));
						lss.add(item.getItemMeta().getLore().get(12).replaceFirst(" ", "").replaceFirst(" ", "").replaceFirst(" ", ""));
						lss.add("");
						lss.add("§eLeft-Click §7to open");
						lss.add("§eRight-Click §7to configurate");
						mss.setLore(lss);
						ss.setItemMeta(mss);
						inv.setItem(i, ss);
				}
			}
		}
	}
	@EventHandler
	public void onFE(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Vaults")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			if (e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("locked")) {
				e.getWhoClicked().closeInventory();
			}
		}
	}
//	@EventHandler
	public void onECOpen(InventoryOpenEvent e) {
//		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		
		ItemStack vault2 = new ItemStack(Material.CHEST);
		ItemMeta mvault2 = vault2.getItemMeta();
		mvault2.setDisplayName("§7Open your §8[§aVault 2§8]");
		vault2.setItemMeta(mvault2);
		
		ItemStack vault1 = new ItemStack(Material.CHEST);
		ItemMeta mvault1 = vault1.getItemMeta();
		mvault1.setDisplayName("§7Open your §8[§aVault 1§8]");
		vault1.setItemMeta(mvault1);

		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 14);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§cCLOSED");
		glass.setItemMeta(mglass);
		
		if (inv.getName().contains("Vault")) {
			ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemMeta mred = red.getItemMeta();
			mred.setDisplayName("§7HMC");
			
			red.setItemMeta(mred);
			inv.setItem(53, red);
			inv.setItem(52, red);
			inv.setItem(51, red);
			inv.setItem(50, red);
			if (inv.getName().contains("1")) {
				inv.setItem(49, vault2);
			} else {
				inv.setItem(49, vault1);
			}
			inv.setItem(48, red);
			inv.setItem(47, red);
			inv.setItem(46, red);
			inv.setItem(45, red);
			
			if (inv.getName().contains("2")) {
				
			}
		}
	}
	
//	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().contains("Vault")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§7Open your §8[§aVault 1§8]")) {
				e.setCancelled(true);
				Bukkit.getServer().dispatchCommand(p, "mvp vault 1");
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§7Open your §8[§aVault 2§8]")) {
				e.setCancelled(true);
				Bukkit.getServer().dispatchCommand(p, "mvp vault 2");
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§7HMC")) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cCLOSED")) {
				e.setCancelled(true);
			}
		}
	}
	
//	@EventHandler
	public void onVaultOpen(InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		if (e.getInventory().getName().contains("Vault") && e.getInventory().getName().contains("2")) {
			ItemStack gold = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 15);
			ItemMeta mgold = gold.getItemMeta();
			mgold.setDisplayName("§7You can't use this slot");
			ArrayList<String> lgold = new ArrayList<String>();
			lgold.add("§7You should upgrade your rank");
			lgold.add("§7to §6Gold §7rank to open this slot");
			mgold.setLore(lgold);
			gold.setItemMeta(mgold);
			
			ItemStack diamond = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 15);
			ItemMeta mdiamond = diamond.getItemMeta();
			mdiamond.setDisplayName("§7You can't use this slot");
			ArrayList<String> ldiamond = new ArrayList<String>();
			ldiamond.add("§7You should upgrade your rank");
			ldiamond.add("§7to §bDiamond §7rank to open this slot");
			mdiamond.setLore(ldiamond);
			diamond.setItemMeta(mdiamond);
			
			ItemStack emerald = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 15);
			ItemMeta memerald = emerald.getItemMeta();
			memerald.setDisplayName("§7You can't use this slot");
			ArrayList<String> lemerald = new ArrayList<String>();
			lemerald.add("§7You should upgrade your rank");
			lemerald.add("§7to §aEmerald §7rank to open this slot");
			memerald.setLore(lemerald);
			emerald.setItemMeta(memerald);
			if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.Special") || p.hasPermission("H.SrMod") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald")) {
				
			} else if (p.hasPermission("H.Diamond")) {
				for (int j = 36	; j <= 44; j++) {
					inv.setItem(j, emerald);
				}
			} else if (p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
				for (int j = 36	; j <= 44; j++) {
					inv.setItem(j, emerald);
				}
				for (int j = 27; j <= 35; j++) {
					inv.setItem(j, diamond);
				}
			}
		}
	}
	
//	@EventHandler
	public void onVaultClick(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("Vault")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§7You can't use this slot")) {
				e.setCancelled(true);
			} else {
				
			}
		}
	}
	
	public boolean isFull(Inventory inv){
	    for (ItemStack item : inv.getContents()) {
	         if(item == null) {
	                 return false;
	         }
	     }
	return true;
	}
}