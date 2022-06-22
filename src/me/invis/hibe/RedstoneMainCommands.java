package me.invis.hibe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RedstoneMainCommands implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("feature")) {
      return featureCmd(sender, args);
    }
    if (cmd.getName().equalsIgnoreCase("features")) {
      return featuresCmd(sender);
    }
    return false;
  }
  
  public boolean featureCmd(CommandSender sender, String[] args)
  {
    if (args.length == 0) {
      return false;
    }
    if (args.length >= 1)
    {
      RedstoneFeature[] arrayOfRedstoneFeature;
      int j = (arrayOfRedstoneFeature = RedstoneFeature.values()).length;
      for (int i = 0; i < j; i++)
      {
        RedstoneFeature feature = arrayOfRedstoneFeature[i];
        if (args[0].equalsIgnoreCase(feature.getName()))
        {
          if (args.length >= 2)
          {
            if (args[1].equalsIgnoreCase("enable"))
            {
              RedstonePVP.config.enableFeature(feature);
              sender.sendMessage(ChatColor.GOLD + feature.getName() + ChatColor.GREEN + " has been successfully " + ChatColor.DARK_GREEN + "enabled");
              return true;
            }
            if (args[1].equalsIgnoreCase("disable"))
            {
              RedstonePVP.config.disableFeature(feature);
              sender.sendMessage(ChatColor.GOLD + feature.getName() + ChatColor.GREEN + " has been successfully " + ChatColor.RED + "disabled");
              return true;
            }
            return false;
          }
          sender.sendMessage(ChatColor.GOLD + feature.getName() + ": " + ChatColor.GREEN + feature.getDescription());
          
          return true;
        }
      }
      sender.sendMessage(ChatColor.RED + "Could not find specified feature");
      return false;
    }
    return false;
  }
  
  public boolean featuresCmd(CommandSender sender)
  {
    sender.sendMessage(ChatColor.GREEN + "List of features");
    RedstoneFeature[] arrayOfRedstoneFeature;
    int j = (arrayOfRedstoneFeature = RedstoneFeature.values()).length;
    for (int i = 0; i < j; i++)
    {
      RedstoneFeature feature = arrayOfRedstoneFeature[i];
      sender.sendMessage("   " + ChatColor.GOLD + feature.getName());
    }
    return true;
  }
}
