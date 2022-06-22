package me.invis.hibe.dailysystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.invis.hibe.RedstonePVP;
import me.invis.hibe.dailysystem.DailySQL;
import me.invis.hibe.dailysystem.TimeManager;
import me.invis.hibe.dailysystem.api.ItemsAPI;

public class weekly implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			String name = p.getName();
			if (cmd.getName().equalsIgnoreCase("weekly")) {
				if (DailySQL.getTimeWeekly(name) < 2) {
					weeklyItems(p);
					int timer = 7;
					int giventime = (TimeManager.getCurrentTime());
					DailySQL.putWeekly(p.getName(), giventime + (timer * 60 * 60 * 24));
					p.sendMessage(RedstonePVP.prefix + "§a§lYou have been get weekly items!");
				} else {
					p.sendMessage(RedstonePVP.prefix + "§cPlease wait: §7"
							+ TimeManager.getStringRankRemainingTimeWeekly(name));
				}
			}
		}
		return false;
	}

	public static void weeklyItems(Player p) {
		p.getInventory().addItem(ItemsAPI.weeklyHelmet());
		p.getInventory().addItem(ItemsAPI.weeklyChestplate());
		p.getInventory().addItem(ItemsAPI.weeklyLeggings());
		p.getInventory().addItem(ItemsAPI.weeklyBoots());
		p.getInventory().addItem(ItemsAPI.weeklySword());
		p.getInventory().addItem(ItemsAPI.weeklyApple());
		p.getInventory().addItem(ItemsAPI.weeklyEmerald());
	}
}
