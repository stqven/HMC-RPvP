package me.invis.hibe.goldtrade;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.invis.hibe.RedstoneFeature;
import me.invis.hibe.RedstonePVP;
import me.invis.hibe.util.PaymentManager;

public class GoldTrade
  implements Listener, CommandExecutor
{
  private Set<String> signSetters;
  private GoldTradeConfig config;
  
  public GoldTrade(GoldTradeConfig config)
  {
    this.signSetters = new HashSet<String>();
    this.config = config;
  }
  
  @EventHandler
  public void onTradeSignClick(PlayerInteractEvent event)
  {
    if (!RedstonePVP.config.isFeatureEnabled(RedstoneFeature.GOLD_TRADE)) {
      return;
    }
    Player ply = event.getPlayer();
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if (event.getClickedBlock() == null) {
      return;
    }
    if (!(event.getClickedBlock().getState() instanceof Sign))
    {
      if (this.signSetters.contains(ply.getName()))
      {
        ply.sendMessage(ChatColor.RED + "This is not a sign");
        this.signSetters.remove(ply.getName());
      }
      return;
    }
    Sign sign = (Sign)event.getClickedBlock().getState();
    if (this.signSetters.contains(ply.getName()))
    {
      event.setCancelled(true);
      if (!this.config.isTradeSign(sign))
      {
        this.config.storeSign(sign);
        ply.sendMessage("§8❘ §aHozexMC §8❘ §7Sign sucessfully set as a trade sign");
      }
      else
      {
        ply.sendMessage("§8❘ §cHozexMC §8❘ §7This sign is already a trade sign");
      }
      this.signSetters.remove(ply.getName());
      return;
    }
    if (this.config.isTradeSign(sign))
    {
      event.setCancelled(true);
//      int itemId = this.config.getItemId();
      if (PaymentManager.pay(ply, Material.REDSTONE, 1)) {
    	  // Item ID // PaymentManager.pay;
        ply.giveExp(this.config.getRandomXp());
        return;
      }
      ply.sendMessage("§8❘ §cHozexMC §8❘ §7You dont have any §4Redstone§7 to trade!");
    }
  }
  
  @EventHandler
  public void onTradeSignBreak(BlockBreakEvent event)
  {
    if (!(event.getBlock().getState() instanceof Sign)) {
      return;
    }
    Sign sign = (Sign)event.getBlock().getState();
    if (this.config.isTradeSign(sign)) {
      this.config.removeSign(sign);
    }
  }
  
  @EventHandler
  public void onPlayerLogout(PlayerQuitEvent event)
  {
    String name = event.getPlayer().getName();
    if (this.signSetters.contains(name)) {
      this.signSetters.remove(name);
    }
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!RedstonePVP.config.isFeatureEnabled(RedstoneFeature.GOLD_TRADE))
    {
      sender.sendMessage("§8❘ §cHozexMC §8❘ §7This feature is not enabled");
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("settradesign")) {
      return setSignCmd(sender);
    }
    return false;
  }
  
  private boolean setSignCmd(CommandSender sender)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("§8❘ §cHozexMC §8❘ §7You cant run command from console!");
      return true;
    }
    Player ply = (Player)sender;
    if (!this.signSetters.contains(ply.getName()))
    {
      this.signSetters.add(ply.getName());
      ply.sendMessage("§8❘ §eHozexMC §8❘ §7Right click a sign to set it as a trade sign");
      return true;
    }
    return false;
  }
}
