package me.invis.hibe.util;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.invis.hibe.RedstonePVP;
import me.robin.battlelevels.api.BattleLevelsAPI;
import net.brcdev.gangs.GangsPlusApi;
import net.brcdev.gangs.event.GangCreateEvent;

public class sb implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
	    for (Player all : Bukkit.getOnlinePlayers()) {
      getSB(all);
    }
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
	    for (Player all : Bukkit.getOnlinePlayers()) {
      getSB(all);
    }
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent e) {
	    for (Player all : Bukkit.getOnlinePlayers()) {
	        getSB(all);
	      }
  }
  
  @EventHandler
  public void onjoin(PlayerDeathEvent e) {
    Player p = e.getEntity();
    Player k = p.getKiller();
    if (!(k instanceof Player)) {
      getSB(p);
      return;
    }
    getSB(p);
    getSB(k);
  }
  
  @EventHandler
  public void onGG(GangCreateEvent e) {
	  Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
		@Override
		public void run() {
			Player own = Bukkit.getPlayer(e.getGang().getOwnerName());
			if (own != null) {
		        getSB(own);
			}
		}
	}, 5L);
  }
  
  @EventHandler
  public void onGGJoin(net.brcdev.gangs.event.PlayerJoinGangEvent e) {
	  Player p = e.getPlayer();
      getSB(p);
	  Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
		@Override
		public void run() {
			if (p != null) {
			      getSB(p);
			}
		}
	}, 5L);
  }
  
  @EventHandler
  public void onGGLeave(net.brcdev.gangs.event.PlayerLeaveGangEvent e) {
	  Player p = e.getPlayer();
      getSB(p);
	  Bukkit.getServer().getScheduler().runTaskLater(RedstonePVP.getInstance(), new Runnable() {
		@Override
		public void run() {
			if (p != null) {
			      getSB(p);
			}
		}
	}, 5L);
  }
  
  public static void getSB(Player p) {
	    int ping = ((CraftPlayer)p).getHandle().ping;
	  if (GangsPlusApi.getPlayersGang(p) != null) {
		    ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard scoreboard = manager.getNewScoreboard();
		    Objective objective = scoreboard.registerNewObjective("coins", "dummy");
		    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		    objective.setDisplayName("§6§lHozexMC");
		    Score score1 = objective.getScore("§7･");
		    score1.setScore(14);
		    Score score2 = objective.getScore("§7 §8▼ §4Information");
		    score2.setScore(13);
		    Score score3 = objective.getScore("§7⚫ §fName: §c" + p.getName());
		    score3.setScore(12);
		    Score score4 = objective.getScore("§7⚫ §fProgress: §c" + BattleLevelsAPI.getProgressBar(p.getUniqueId()));
		    score4.setScore(11);
		    Score sc3 = objective.getScore("§7⚫ §fGang: §c" + GangsPlusApi.getPlayersGang(p).getName());
		    sc3.setScore(10);
		    Score score5 = objective.getScore("§7");
		    score5.setScore(9);
		    Score score6 = objective.getScore("§7 §8▼ §4Your stats");
		    score6.setScore(8);
		    Score score7 = objective.getScore("§7⚫ §fLevel: §c" + BattleLevelsAPI.getLevel(p.getUniqueId()));
		    score7.setScore(7);
		    Score score8 = objective.getScore("§7⚫ §fScore: §c" + BattleLevelsAPI.getScore(p.getUniqueId()));
		    score8.setScore(6);
		    Score score9 = objective.getScore("§7 ");
		    score9.setScore(5);
		    Score score10 = objective.getScore("§7 §8▼ §4Server");
		    score10.setScore(4);
		    Score score11 = objective.getScore("§7⚫ §fOnline: §c" + Bukkit.getServer().getOnlinePlayers().size());
		    score11.setScore(3);
		    Score score12 = objective.getScore("§7⚫ §fPing: §c" + ping);
		    score12.setScore(2);
		    Score score13 = objective.getScore("§7  ");
		    score13.setScore(1);
		    Score score14 = objective.getScore("§7play.hozexmc.net");
		    score14.setScore(0);
		    p.setScoreboard(scoreboard);
	  } else {
		    ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard scoreboard = manager.getNewScoreboard();
		    Objective objective = scoreboard.registerNewObjective("coins", "dummy");
		    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		    objective.setDisplayName("§6§lHozexMC");
		    Score score1 = objective.getScore("§7･");
		    score1.setScore(13);
		    Score score2 = objective.getScore("§7 §8▼ §4Information");
		    score2.setScore(12);
		    Score score3 = objective.getScore("§7⚫ §fName: §c" + p.getName());
		    score3.setScore(11);
		    Score score4 = objective.getScore("§7⚫ §fProgress: §c" + BattleLevelsAPI.getProgressBar(p.getUniqueId()));
		    score4.setScore(10);
		    Score score5 = objective.getScore("§7");
		    score5.setScore(9);
		    Score score6 = objective.getScore("§7 §8▼ §4Your stats");
		    score6.setScore(8);
		    Score score7 = objective.getScore("§7⚫ §fLevel: §c" + BattleLevelsAPI.getLevel(p.getUniqueId()));
		    score7.setScore(7);
		    Score score8 = objective.getScore("§7⚫ §fScore: §c" + BattleLevelsAPI.getScore(p.getUniqueId()));
		    score8.setScore(6);
		    Score score9 = objective.getScore("§7 ");
		    score9.setScore(5);
		    Score score10 = objective.getScore("§7 §8▼ §4Server");
		    score10.setScore(4);
		    Score score11 = objective.getScore("§7⚫ §fOnline: §c" + Bukkit.getServer().getOnlinePlayers().size());
		    score11.setScore(3);
		    Score score12 = objective.getScore("§7⚫ §fPing: §c" + ping);
		    score12.setScore(2);
		    Score score13 = objective.getScore("§7  ");
		    score13.setScore(1);
		    Score score14 = objective.getScore("§7play.hozexmc.net");
		    score14.setScore(0);
		    p.setScoreboard(scoreboard);
	  }
  }
}
