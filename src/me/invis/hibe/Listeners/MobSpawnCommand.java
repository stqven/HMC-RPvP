package me.invis.hibe.Listeners;
 
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.invis.hibe.RedstonePVP;
 
public class MobSpawnCommand implements CommandExecutor {
 
    private RedstonePVP plugin;
    public MobSpawnCommand(RedstonePVP main) {
        this.plugin = main;
    }
   
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
       
        if(sender instanceof Player) {
           
            if(sender != null) {
               
                Player p = (Player) sender;
                Location loc = p.getLocation();
               
                if(p.hasPermission("Owner")) {
                   
                    if(args.length > 0) {
                       
                        if(args[0] != null) {
               
                           
                            if(args[0].equalsIgnoreCase("On")) {
                                plugin.mobSpawn.summonMobs();
                                p.sendMessage(ChatColor.GREEN + "MobSpawn On");
                           
                            } else if (args[0].equalsIgnoreCase("Off")) {
                                plugin.mobSpawn.stopSummonMobs();
                                p.sendMessage(ChatColor.RED + "MobSpawn Off");
                           
                            } else if(args[0].equalsIgnoreCase("help")) {
                                p.sendMessage(ChatColor.GREEN + "1:/mobspawn on\n"
                                        + "2: /mobspawn off\n"
                                        + "3: /mobspawn set Zombie,Magma,Skeleton,Blaze,Witch");
                            } else if(args[0].equalsIgnoreCase("set")) {
                               
                                if(args[1].equalsIgnoreCase("zombie")) {
                                    plugin.getConfig().set("Mobs.Zombie.X",loc.getBlockX());
                                    plugin.getConfig().set("Mobs.Zombie.Y", loc.getBlockY() + 1);
                                    plugin.getConfig().set("Mobs.Zombie.Z", loc.getBlockZ());
                                    p.sendMessage(ChatColor.GREEN + "Zombie Spawn has been set");
                                    plugin.saveConfig();
                                } else if(args[1].equalsIgnoreCase("pigzombie")) {
                                    plugin.getConfig().set("Mobs.PigZombie.X",loc.getBlockX());
                                    plugin.getConfig().set("Mobs.PigZombie.Y", loc.getBlockY() + 1);
                                    plugin.getConfig().set("Mobs.PigZombie.Z", loc.getBlockZ());
                                    p.sendMessage(ChatColor.GREEN + "Magma Spawn has been set");
                                    plugin.saveConfig();
                               
                                } else if(args[1].equalsIgnoreCase("skeleton")) {
                                    plugin.getConfig().set("Mobs.Skeleton.X",loc.getBlockX());
                                    plugin.getConfig().set("Mobs.Skeleton.Y", loc.getBlockY() + 1);
                                    plugin.getConfig().set("Mobs.Skeleton.Z", loc.getBlockZ());
                                    p.sendMessage(ChatColor.GREEN + "Skeleton Spawn has been set");
                                    plugin.saveConfig();
                                   
                                } else if(args[1].equalsIgnoreCase("blaze")) {
                                    plugin.getConfig().set("Mobs.Blaze.X",loc.getBlockX());
                                    plugin.getConfig().set("Mobs.Blaze.Y", loc.getBlockY() + 1);
                                    plugin.getConfig().set("Mobs.Blaze.Z", loc.getBlockZ());
                                    p.sendMessage(ChatColor.GREEN + "Blaze Spawn has been set");
                                    plugin.saveConfig();
                                   
                                } else if(args[1].equalsIgnoreCase("witch")) {
                                    plugin.getConfig().set("Mobs.Witch.X",loc.getBlockX());
                                    plugin.getConfig().set("Mobs.Witch.Y", loc.getBlockY() + 1);
                                    plugin.getConfig().set("Mobs.Witch.Z", loc.getBlockZ());
                                    p.sendMessage(ChatColor.GREEN + "Witch Spawn has been set");
                                    plugin.saveConfig();
                                   
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "/mobspawn <on/off>");
                            }
                           
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "/mobspawn <on/off>");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "No perms");
                }
            }
        } else {
            System.out.println("No");
        }
        return false;
    }
 
}