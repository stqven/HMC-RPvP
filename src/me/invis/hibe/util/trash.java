package me.invis.hibe.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class trash implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("trash")) {
			Player p = (Player) sender;
		openTrash(p);	
		}
		
		return false;
	}
	
	@EventHandler
	public void onTrapDoor(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) return;
		if (e.getClickedBlock() != null) {
		if (e.getClickedBlock().getType() == Material.TRAP_DOOR) {
			e.setCancelled(true);
		}
		}
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) return;
		Block bb = e.getClickedBlock();
		if (bb.getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			if (sign.getLine(1).contains("Trash")) {
				openTrash(e.getPlayer());
			}
		}
	}
	
	public void openTrash(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§cTrash");
		ItemStack ii = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta mii = ii.getItemMeta();
		mii.setDisplayName("§4TRASH ALL");
		ArrayList<String> lii = new ArrayList<String>();
		lii.add("§cDrop all your items in the inventory");
		lii.add("§cto the trash");
		ii.setItemMeta(mii);
		inv.setItem(22,  ii);
		p.openInventory(inv);
	}
	@EventHandler
	public void onTrash(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv == null) return;
			if (inv.getName().contains("Trash")) {
				if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
				if (e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("trash all")) {
					e.setCancelled(true);
					inv.remove(Material.STAINED_GLASS_PANE);
					for (ItemStack items : p.getInventory().getContents()) {
						if (items == null || items.getType() == Material.AIR) continue;
						inv.addItem(items);
					}
					p.getInventory().clear();
				}
			}
		}
	}

}