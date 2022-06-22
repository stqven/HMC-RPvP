package me.invis.hibe.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.Redstone;

import me.invis.hibe.RedstonePVP;
import me.invis.hibe.Units;
import me.invis.hibe.mysql;
import net.brcdev.gangs.GangsPlusApi;
import net.brcdev.gangs.event.GangLevelUpEvent;
import net.brcdev.gangs.gang.Gang;

public class Gangs implements Listener {
	
	@EventHandler
	public void onGangLevelUP(GangLevelUpEvent e) {
		if (e.getCurrentLevel()%4 == 0) {
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mvp give " + e.getGang().getName() + " 1");
		}
	}
	
	@EventHandler
	public void entityHit(ProjectileHitEvent e) {
		if (e.getEntity().isOnGround()) {
			e.getEntity().remove();
		}
	}
	
//	@EventHandler (priority = EventPriority.MONITOR)
//	public void onRename(PlayerCommandPreprocessEvent e) {
//		if (e.getMessage().equalsIgnoreCase("/gang rename") || e.getMessage().equalsIgnoreCase("/g rename")) {
//			e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7Invalid command usage. Correct usage: /gang rename <name>");
//			e.getPlayer().sendMessage("§8* §7This command will cost you §e$500");;
//			e.setMessage("/lq");
//			e.setCancelled(true);
//		}
//		if (e.getMessage().startsWith("/gang rename ") || e.getMessage().startsWith("/g rename ")) {
//			Player p = e.getPlayer();
//			String name = e.getMessage().replace("/g rename ", "").replace("/gang rename ", "");
//			boolean exists = false;
//			for (Gang gg : GangsPlusApi.getAllGangs()) {
//				if (gg.getName().equalsIgnoreCase(name)) {
//					exists = true;
//				}
//			}
//			if (exists) {
//				e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7This name is already used by another gang");
//				e.setMessage("/lq");
//				e.setCancelled(true);
//				return;
//			}
//			if (RedstonePVP.getDaily().get(p.getName().toLowerCase()) != null) {
//				long endOfBan = RedstonePVP.getDaily().get(p.getName().toLowerCase());
//				if (endOfBan < System.currentTimeMillis()) {
//					RedstonePVP.getDaily().remove(p.getName().toLowerCase());
//				} else {
//					endOfBan = endOfBan - System.currentTimeMillis();
//					endOfBan /= 1000;
//					long days = endOfBan/(3600*24);
//					long hours = (endOfBan%(3600*24))/3600;
//					long minutes = ((endOfBan%(3600*24))%3600)/60;
//					long seconds = ((endOfBan%(3600*24))%3600)%60;
//					StringBuilder str = new StringBuilder();
//					if (days > 1) {
//						str.append(days + " days ");
//					} else if (days == 1) {
//						str.append(days + " day ");
//					} else {
//						
//					}
//					if (hours > 1) {
//						str.append(hours + " hours ");
//					} else if (hours == 1) {
//						str.append(hours + " hour ");
//					} else {
//						
//					}
//					if (minutes > 1) {
//						str.append(minutes + " minutes ");
//					} else if (minutes == 1) {
//						str.append(minutes + " minute ");
//					} else {
//						
//					}
//					if (seconds > 1) {
//						str.append(seconds + " seconds ");
//					} else if (seconds == 1) {
//						str.append(seconds + " second ");
//					} else {
//						
//					}
//					p.sendMessage("§8❘ §cHozexMC §8❘ §7You can change you gang's name again in §e" + str);
//					e.setMessage("/lq");
//					e.setCancelled(true);
//					return;
//				}
//			}
//			if (GangsPlusApi.getPlayersGang(p).getMemberData(p).getRank() == 5) {
//				if (name.length() > 5 || name.length() < 2) {
//					p.sendMessage("§8❘ §cHozexMC §8❘ §7Specified name is either too short or too long, it may contain 2-5 characters");
//				} else {
//					for (Player gall : GangsPlusApi.getPlayersGang(e.getPlayer()).getOnlineMembers()) {
//						if (gall.getName().equalsIgnoreCase(p.getName())) continue;
//						e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7Your gang's name has been changed from §e" + GangsPlusApi.getPlayersGang(p).getName() + " §7to §e" + name);
//					}
//					GangsPlusApi.getPlayersGang(p).setName(name);
//					e.getPlayer().sendMessage("§8❘ §aHozexMC §8❘ §7Successfully renamed your gang to §e" + name);
//					RedstonePVP.setDaily(GangsPlusApi.getPlayersGang(p).getName(), System.currentTimeMillis() + Units.getTicks("day", 4));
//				}
//			} else {
//				e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7You don't have enough permissions to do this");
//			}
//			e.setMessage("/lq");
//			e.setCancelled(true);
//		}
//	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onDisband(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().toLowerCase().startsWith("/gang disband") || e.getMessage().toLowerCase().startsWith("/g disband")) {
			Player p = e.getPlayer();
			if (GangsPlusApi.getPlayersGang(p).getKills() >= 500) {
				e.setCancelled(true);
				e.setMessage("");
				if (GangsPlusApi.getPlayersGang(p).getMemberData(p).getRank() == 5) {
					e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7Please open a ticket to disband this gang");
				} else {
					e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7You don't have enough permissions to do this");
				}
			} else {
				e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7You need §e$500 §7to change your gang name");
			}
		}
	}
	@EventHandler
	public void onJoin(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().startsWith("/gang join ") || e.getMessage().startsWith("/g join ")) {
			String gang = e.getMessage().replace("/gang join ", "").replace("/g join ", "");
			if (!GangsPlusApi.isInGang(p)) {
				boolean found = false;
				for (Gang g : GangsPlusApi.getAllGangs()) {
					found = true;
					if (g.getName().toLowerCase().equals(gang.toLowerCase())) {
						if (g.isInvited(p)) {
							int gold = 0, diamond = 0, emerald = 0, builder = 0, mod = 0, helper = 0;	
							for (OfflinePlayer allg : g.getAllMembers()) {
//								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("gold")) {
//									gold++;
//								}
//								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("diamond")) {
//									diamond++;
//								}
//								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("emerald")) {
//									emerald++;
//								}
//								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("builder")) {
//									builder++;
//								}
								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("mod")) {
									mod++;
								}
								if (mysql.getPrimaryGroup(allg.getName()).toLowerCase().contains("helper")) {
									helper++;
								}
							}
							if (g.getLevel()/5 >= 4) {
								if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Mod")) {
									if (mod >= 1) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Helper") || p.hasPermission("H.Special")) {
									if (helper >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.VIP") || p.hasPermission("H.Developer") || p.hasPermission("H.Emerald")) {
									if (emerald >= 12) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}  else if (p.hasPermission("H.Builder")) {
									if (builder >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}else if (p.hasPermission("H.Diamond")) {
									if (diamond >= 14) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Gold")) {
									if (gold >= 16) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									}
								}
							} else if (g.getLevel()/5 == 3) {
								if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod")) {
									if (mod >= 1) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Helper")) {
									if (helper >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.VIP") || p.hasPermission("H.Developer") || p.hasPermission("H.Emerald")) {
									if (emerald >= 9) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}  else if (p.hasPermission("H.Builder")) {
									if (builder >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}else if (p.hasPermission("H.Diamond")) {
									if (diamond >= 9) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Gold")) {
									if (gold >= 12) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									}
								}
							} else if (g.getLevel()/5 == 2) {
								if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod")) {
									if (mod >= 1) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Helper")) {
									if (helper >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.VIP") || p.hasPermission("H.Developer") || p.hasPermission("H.Emerald")) {
									if (emerald >= 7) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}  else if (p.hasPermission("H.Builder")) {
									if (builder >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}else if (p.hasPermission("H.Diamond")) {
									if (diamond >= 7) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Gold")) {
									if (gold >= 9) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									}
								}
							} else if (g.getLevel()/5 == 1) {
								if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod")) {
									if (mod >= 1) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Helper")) {
									if (helper >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.VIP") || p.hasPermission("H.Developer") || p.hasPermission("H.Emerald")) {
									if (emerald >= 5) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}  else if (p.hasPermission("H.Builder")) {
									if (builder >= 2) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								}else if (p.hasPermission("H.Diamond")) {
									if (diamond >= 5) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									} else {
										Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
									}
								} else if (p.hasPermission("H.Gold")) {
									if (gold >= 7) {
										p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
										e.setCancelled(true);
										e.setMessage("");
									}
								}
							} else if (g.getLevel()/5 == 0) {
							if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod")) {
								if (mod >= 1) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								} else {
									Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
								}
							} else if (p.hasPermission("H.Helper")) {
								if (helper >= 2) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								} else {
									Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
								}
							} else if (p.hasPermission("H.VIP") || p.hasPermission("H.Developer") || p.hasPermission("H.Emerald")) {
								if (emerald >= 3) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								} else {
									Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
								}
							}  else if (p.hasPermission("H.Builder")) {
								if (builder >= 2) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								} else {
									Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
								}
							}else if (p.hasPermission("H.Diamond")) {
								if (diamond >= 4) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								} else {
									Bukkit.getServer().dispatchCommand(p, "g join " + g.getName());
								}
							} else if (p.hasPermission("H.Gold")) {
								if (gold >= 5) {
									p.sendMessage("§8❘ §cHozexMC §8❘ §7This clan has can't accept more members with your rank");
									e.setCancelled(true);
									e.setMessage("");
								}
							}
							}
						} else {
							p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not invited to this gang");
							e.setCancelled(true);
							e.setMessage("");
						}
					}
				}
				if (!found) {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7Invalid gang name");
					e.setCancelled(true);
					e.setMessage("");
				}
			} else {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7You are already in a gang");
				e.setCancelled(true);
				e.setMessage("");
			}
		}
	}

}
