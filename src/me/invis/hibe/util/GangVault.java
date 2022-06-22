package me.invis.hibe.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.invis.hibe.RedstonePVP;
import net.brcdev.gangs.GangsPlusApi;

public class GangVault implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
		if (b.getType() == Material.CHEST) {
			e.setCancelled(true);
			if (p.getAllowFlight()) {
				return;
			}
			if (GangsPlusApi.getPlayersGang(p) != null) {
				String gname =  GangsPlusApi.getPlayersGang(p).getName();
					if (RedstonePVP.getInstance().getConfig().contains("vault." + gname) && RedstonePVP.getInstance().getConfig().getBoolean("vault." + gname)) {
						openGangChestMenu(e.getPlayer());
					} else {
						if (GangsPlusApi.getPlayersGang(p).getBankMoney() >= 500) {
							openYesNo(p);
						} else {
							p.sendMessage("§8❘ §cHozexMC §8❘ §7Your gang should have at least §e$500 §7in the bank to open this");
						}
					}
			} else {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not in gang");
			}
		}
	}
	
	public static void openYesNo(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Yes§c/§8No");
		ItemStack glassGray = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 7);
		ItemMeta mglassGray = glassGray.getItemMeta();
		mglassGray.setDisplayName("§7RedstonePvP");
		glassGray.setItemMeta(mglassGray);
		
		ItemStack glassBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 15);
		ItemMeta mglassBlack = glassBlack.getItemMeta();
		mglassBlack.setDisplayName("§7RedstonePvP");
		glassBlack.setItemMeta(mglassBlack);
		
		ItemStack yes = new ItemStack(Material.WOOL, 1,(short) 5);
		ItemMeta myes = yes.getItemMeta();
		myes.setDisplayName("§a§lYES!");
		yes.setItemMeta(myes);
		
		ItemStack no = new ItemStack(Material.WOOL, 1,(short) 14);
		ItemMeta mno = no.getItemMeta();
		mno.setDisplayName("§c§lNO!");
		no.setItemMeta(mno);

		inv.setItem(0, glassGray);
		inv.setItem(1, glassGray);
		inv.setItem(2, glassGray);
		inv.setItem(4, glassGray);
		inv.setItem(6, glassGray);
		inv.setItem(7, glassGray);
		inv.setItem(8, glassGray);
		inv.setItem(9, glassGray);
		inv.setItem(17, glassGray);
		inv.setItem(18, glassGray);
		inv.setItem(19, glassGray);
		inv.setItem(20, glassGray);
		inv.setItem(22, glassGray);
		inv.setItem(24, glassGray);
		inv.setItem(25, glassGray);
		inv.setItem(26, glassGray);
		inv.setItem(3, glassBlack);
		inv.setItem(5, glassBlack);
		inv.setItem(10, glassBlack);
		inv.setItem(11, glassBlack);
		inv.setItem(13, glassBlack);
		inv.setItem(15, glassBlack);
		inv.setItem(16, glassBlack);
		inv.setItem(21, glassBlack);
		inv.setItem(23, glassBlack);
		inv.setItem(12, yes);
		inv.setItem(14, no);
		p.openInventory(inv);
	}
	@EventHandler
	public void onDropItems(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getItemInHand() == null || p.getInventory().getItemInHand().getType() == Material.AIR)
			return;
		if (e.getItemDrop() == null)
			return;
		if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("emerald")) {
			if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cYou can't drop that item here");
				p.getItemInHand().setType(Material.AIR);
			} else {
				e.getItemDrop().remove();
				e.getItemDrop().getItemStack().setType(Material.AIR);
				p.getInventory().removeItem(e.getItemDrop().getItemStack());
			}
		}
	}
	@EventHandler
	public void onBlockMove(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (p.getOpenInventory().getTitle().contains("Gang Vault")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			if (e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("emerald")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		if (inv.getName().contains("§8Yes§c/§8No")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("yes")) {
				e.setCancelled(true);
				double bank = GangsPlusApi.getPlayersGang(p).getBankMoney();
				if (bank >= 500) {
					GangsPlusApi.getPlayersGang(p).setBankMoney(bank - 500, true);
					RedstonePVP.getInstance().getConfig().set("vault." + GangsPlusApi.getPlayersGang(p).getName(), true);
					RedstonePVP.getInstance().saveConfig();
					for (Player all : GangsPlusApi.getPlayersGang(p).getOnlineMembers()) {
						if (all.getName().equals(p.getName())) {
							all.sendMessage("§8❘ §aHozexMC §8❘ §7You have successfuly purchased a §eGang Vault");
						} else {
							all.sendMessage("§8❘ §eHozexMC §8❘ §7Your gang now has a §6Gang Vault");
						}
					}
				} else {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7Your gang should have at least §e$500 §7in the bank to open this");
				}
				p.closeInventory();
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("no")) {
				e.setCancelled(true);
				p.closeInventory();
				if (RedstonePVP.getInstance().getRandom(1, 3) == 1) {
					p.sendMessage("§cTake your time in thinking");
				} else if (RedstonePVP.getInstance().getRandom(1, 3) == 1) {
					p.sendMessage("§cThere we go");
				} else {
					p.sendMessage("§cIt's ok");
				}
				p.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onGangChestBuy(InventoryOpenEvent e) {
		Inventory inv = e.getInventory();
		if (inv == null || inv == e.getInventory()) return;
		if (inv.getName().contains("Gang Vault")) {
			
		}
	}
	public int countGangChest(String gang) {
		if (RedstonePVP.getInstance().getConfig().contains(gang + ".pv")) {
			int pv = RedstonePVP.getInstance().getConfig().getInt(gang + ".pv");
			if (pv == 0) {
				return 1;
			}
			return pv;
		} else {
			return 1;
		}
	}
	
	public void openGangChestMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "Vaults Menu");
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 15);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7");
		glass.setItemMeta(mglass);
		ItemStack chest = new ItemStack(Material.STORAGE_MINECART);
		ItemStack chestclosed = new ItemStack(Material.MINECART);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, glass);
		}
		inv.setItem(9, glass);
		inv.setItem(10, glass);
		int pv = countGangChest(GangsPlusApi.getPlayersGang(p).getName());
		for (int i = 0; i < pv; i++) {
			ItemMeta mchest = chest.getItemMeta();
			mchest.setDisplayName("§6(#" + (i + 1) + ") §fGang Vault");
			ArrayList<String> str = new ArrayList<String>();
			str.add("§7");
			str.add("§8➥ §aClick to open this!");
			str.add("§7");
			mchest.setLore(str);
			chest.setItemMeta(mchest);
			inv.setItem((11 + i), chest);
		}
		for (int i = 0; i < 5 - pv; i++) {
			ItemMeta mchestclosed = chestclosed.getItemMeta();
			mchestclosed.setDisplayName("§6(#" + (i + 1 + pv) + ") §fGang Vault");
			ArrayList<String> str = new ArrayList<String>();
			str.add("§7");
			str.add("§8➥ §eCost: §f$10000");
			str.add("§8➥ §cYour gang doesn't have this");
			str.add("§7");
			mchestclosed.setLore(str);
			chestclosed.setItemMeta(mchestclosed);
			inv.setItem((11 + pv + i), chestclosed);
		}
		
		inv.setItem(16, glass);
		inv.setItem(17, glass);
		for (int i = 18; i < 27; i++) {
			inv.setItem(i, glass);
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onGangChestMenu(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("Vaults Menu")) {
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.MINECART) {
				e.setCancelled(true);
				if (GangsPlusApi.getPlayersGang(p).getMemberData(p).getRank() >= 4) {
					double money = GangsPlusApi.getPlayersGang(p).getBankMoney();
					String gname = GangsPlusApi.getPlayersGang(p).getName();
					if (money >= 5000) {
						if (RedstonePVP.getInstance().getConfig().contains(gname + ".pv")) {
							RedstonePVP.getInstance().getConfig().set(gname + ".pv", (Integer.valueOf(RedstonePVP.getInstance().getConfig().getInt(GangsPlusApi.getPlayersGang(p).getName() + ".pv")).intValue() + 1));
							RedstonePVP.getInstance().saveConfig();
						} else {
							RedstonePVP.getInstance().getConfig().set(gname + ".pv", 2);
							RedstonePVP.getInstance().saveConfig();
						}
						RedstonePVP.getInstance().saveConfig();
						p.closeInventory();
						p.sendMessage("§8❘ §aHozexMC §8❘ §7You have bought a new chest for your gang");
						GangsPlusApi.getPlayersGang(p).setBankMoney(money - 10000, true);
					} else {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7There's not enough money in your gang bank");
					}
				} else {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7You can't use money in the gang bank");
				}
			} else if (e.getCurrentItem().getType() == Material.STORAGE_MINECART) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("1")) {
					Bukkit.getServer().dispatchCommand(p, "playervaults " + GangsPlusApi.getPlayersGang(p).getName() + " 1");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("2")) {
					Bukkit.getServer().dispatchCommand(p, "playervaults " + GangsPlusApi.getPlayersGang(p).getName() + " 2");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("3")) {
					Bukkit.getServer().dispatchCommand(p, "playervaults " + GangsPlusApi.getPlayersGang(p).getName() + " 3");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("4")) {
					Bukkit.getServer().dispatchCommand(p, "playervaults " + GangsPlusApi.getPlayersGang(p).getName() + " 4");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("5")) {
					Bukkit.getServer().dispatchCommand(p, "playervaults " + GangsPlusApi.getPlayersGang(p).getName() + " 5");
				}
			}
		}
	}

}
