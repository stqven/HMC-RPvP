package me.invis.hibe.util;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class sco implements CommandExecutor, Listener {
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		if (e.getMessage().equals("/pay")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChatCMD(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equals("pay")) {
			e.setCancelled(true);
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sco") || cmd.getName().equalsIgnoreCase("pay")) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7/sco [Player] [Money]");
			} else if (args.length == 1) {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7/sco [Player] [Money]");
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if (p.hasPlayedBefore()) {
					if (!args[0].equalsIgnoreCase(p.getName())) {
						int money = Integer.valueOf(args[1]).intValue();
						if (money >= 500) {
							if (checkIfHasMoney(p, money)) {
								try {
									p.sendMessage("§8❘ §aHozexMC §8❘ §7You have sent §e$" + money + " §7to §e" + args[0]);
									Economy.setMoney(p.getName(), Economy.getMoney(p.getName()) - money);
									Economy.setMoney(args[0], Economy.getMoney(args[0]) + money);
									if (t != null) {
										t.sendMessage("§8❘ §eHozexMC §8❘ §7You have received §e$" + money + " §7from §e" + p.getName());
									}
								} catch (NoLoanPermittedException e) {
									p.sendMessage("§8❘ §4HozexMC §8❘ §7Error #1 while sending money");
									e.printStackTrace();
								} catch (UserDoesNotExistException e) {
									p.sendMessage("§8❘ §4HozexMC §8❘ §7Error #2 while sending money");
									e.printStackTrace();
								}
							} else {
								p.sendMessage("§8❘ §cHozexMC §8❘ §7You don't have enough money");
							}
						} else {
							p.sendMessage("§8❘ §cHozexMC §8❘ §7You amount should be at least §e$500");
						}
					} else {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7You can't send money to yourself");
					}
				} else {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7Invalid username");
				}
			}
		}
		return false;
	}
	
	  public static boolean checkIfHasMoney(Player p, int amount) {
		    try {
		      if (Economy.getMoney(p.getName()) >= amount) {
		        return true;
		      }
		      return false;
		    }
		    catch (UserDoesNotExistException e) {
		      e.printStackTrace();
		      
		      return false;
		    }  }

}
