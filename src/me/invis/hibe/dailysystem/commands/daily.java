package me.invis.hibe.dailysystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.invis.hibe.RedstonePVP;
import me.invis.hibe.dailysystem.DailySQL;
import me.invis.hibe.dailysystem.TimeManager;
import me.invis.hibe.dailysystem.api.ItemsAPI;

public class daily implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandlable, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			String name = p.getName();
			if (cmd.getName().equalsIgnoreCase("daily")) {
				if (DailySQL.getTimeDaily(name) < 2) {
					dailyItems(p);
					int timer = 1;
					int giventime = (TimeManager.getCurrentTime());
					DailySQL.putDaily(p.getName(), giventime + (timer * 60 * 60 * 24));
					p.sendMessage(RedstonePVP.prefix + "§a§lYou have been get daily items!");
				} else {
					p.sendMessage(RedstonePVP.prefix + "§cPlease wait: §7"
							+ TimeManager.getStringRankRemainingTimeDaily(name));
				}
			}
		}
		return false;
	}

	public static void dailyItems(Player p) {//a74f al table
		p.getInventory().addItem(ItemsAPI.dailyHelmet());
		p.getInventory().addItem(ItemsAPI.dailyChestplate());
		p.getInventory().addItem(ItemsAPI.dailyLeggings());
		p.getInventory().addItem(ItemsAPI.dailyBoots());
		p.getInventory().addItem(ItemsAPI.dailySword());
		p.getInventory().addItem(ItemsAPI.dailyApple());
		p.getInventory().addItem(ItemsAPI.dailyRedstone());
	}
}
