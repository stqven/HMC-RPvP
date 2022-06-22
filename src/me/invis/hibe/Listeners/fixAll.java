package me.invis.hibe.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.invis.hibe.RedstonePVP;

public class fixAll implements CommandExecutor {
	
	public boolean fixzAll(Player p) {
		boolean re0 = false;
		for (int i = 0; i < 35; i++) {
			if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() != Material.AIR) {
				ItemStack helmet = p.getInventory().getHelmet();
					if (helmet.getDurability() != 0) {
						p.sendMessage("§8❘ §aHozexMC §8❘ §7Your §ehelmet §7has been fixed");
						re0 = true;
						helmet.setDurability((short) 0);
						p.getInventory().setHelmet(helmet);
					}
			}
			if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() != Material.AIR) {
				ItemStack chestplate = p.getInventory().getChestplate();
					if (chestplate.getDurability() != 0) {
						p.sendMessage("§8❘ §aHozexMC §8❘ §7Your §echestplate §7has been fixed");
						re0 = true;
						chestplate.setDurability((short) 0);
						p.getInventory().setChestplate(chestplate);
					}
			}
			if (p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() != Material.AIR) {
				ItemStack leggings = p.getInventory().getLeggings();
				if (leggings.getDurability() != 0) {
					p.sendMessage("§8❘ §aHozexMC §8❘ §7Your §ehelmet §7has been fixed");
					re0 = true;
					leggings.setDurability((short) 0);
					p.getInventory().setLeggings(leggings);
				}
			}
			if (p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() != Material.AIR) {
				ItemStack boots = p.getInventory().getBoots();
				if (boots.getDurability() != 0) {
					p.sendMessage("§8❘ §aHozexMC §8❘ §7Your §eboots §7has been fixed");
					re0 = true;
					boots.setDurability((short) 0);
					p.getInventory().setBoots(boots);
				}
			}
			if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() != Material.AIR) {
				ItemStack ss = p.getInventory().getItem(i);
				if (ss.getDurability() != 0) {
					p.sendMessage("§8❘ §aHozexMC §8❘ §7Your §e" + p.getInventory().getItem(i).getType().toString().toLowerCase().replace("_", " ") + " §7has been fixed");
					re0 = true;
					ss.setDurability((short) 0);
					p.getInventory().setItem(i, ss);
				}
			}
		}
		return re0;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fix")) {
			Player p = (Player) sender;
			if (p.hasPermission("H.VIP")) {
				if (!RedstonePVP.fix.contains(p.getName())) {
					if (fixzAll(p)) {
						RedstonePVP.fix.add(p.getName());
						Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								if (RedstonePVP.fix.contains(p.getName())) {
									RedstonePVP.fix.remove(p.getName());
								}
							}
						}, 72000L);
					} else {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7Your items are already fixed");
					}
				} else {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7You can use this command one time per hour.");
				}
			} else {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7Only §5VIP §7members can use this command");
			}
		}
		return false;
	}

}
