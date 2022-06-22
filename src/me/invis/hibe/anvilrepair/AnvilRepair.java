package me.invis.hibe.anvilrepair;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import me.invis.hibe.RedstoneFeature;
import me.invis.hibe.RedstonePVP;
import me.invis.hibe.util.PaymentManager;

public class AnvilRepair implements Listener {
  private AnvilRepairConfig config;
  
  public AnvilRepair(AnvilRepairConfig config) {
    this.config = config;
  }
  
  
  @EventHandler
  public void onAnvilClick(PlayerInteractEvent event) {
    if (!RedstonePVP.config.isFeatureEnabled(RedstoneFeature.ANVIL_REPAIR)) {
      return;
    }
    Player p = event.getPlayer();
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if (event.getClickedBlock().getType() != Material.ANVIL) {
      return;
    }
    event.setCancelled(true);
    ItemStack item = p.getItemInHand();
    if (item == null) {
      return;
    }
    if (this.config.isRepairable(item)) {
      runRepair(p, event.getClickedBlock());
    } else {
        p.sendMessage("§8❘ §cHozexMC §8❘ §7You can't repair this item");
    }
  }
  @SuppressWarnings("deprecation")
private void runRepair(Player p, Block anvil) {
	  if (anvil.getLocation().add(0, -1, 0).getBlock().getType() == Material.EMERALD_BLOCK) {
		    	  p.playEffect(anvil.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	          ItemStack item = p.getItemInHand();
	          item.setDurability((short)0);
	          p.setItemInHand(null);
	          Item spawnedItem = p.getWorld().dropItem(anvil.getLocation().add(0.5D, 1.5D, 0.5D), item);
	          spawnedItem.setVelocity(new Vector(0, 0, 0));
	          spawnedItem.setItemStack(item);
	          spawnedItem.setPickupDelay(80);
	          BukkitScheduler sched = RedstonePVP.getInstance().getServer().getScheduler();
	          sched.scheduleSyncDelayedTask(RedstonePVP.getInstance(), new RepairTask(p, spawnedItem, RedstonePVP.getInstance()), 20L);
	  } else if (anvil.getLocation().add(0, -1, 0).getBlock().getType() == Material.DIAMOND_BLOCK) {
		  if (PaymentManager.pay(p, Material.REDSTONE, 15)) {
		    	  p.playEffect(anvil.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	          ItemStack item = p.getItemInHand();
	          item.setDurability((short) 0);
	          p.setItemInHand(null);
	          Item spawnedItem = p.getWorld().dropItem(anvil.getLocation().add(0.5D, 1.5D, 0.5D), item);
	          spawnedItem.setVelocity(new Vector(0, 0, 0));
	          spawnedItem.setItemStack(item);
	          spawnedItem.setPickupDelay(80);
	          BukkitScheduler sched = RedstonePVP.getInstance().getServer().getScheduler();
	          sched.scheduleSyncDelayedTask(RedstonePVP.getInstance(), new RepairTask(p, spawnedItem, RedstonePVP.getInstance()), 20L);
		  } else {
			  if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD || p.getInventory().getItemInHand().getType() == Material.GOLD_SWORD || p.getInventory().getItemInHand().getType() == Material.IRON_SWORD || p.getInventory().getItemInHand().getType() == Material.STONE_SWORD || p.getInventory().getItemInHand().getType() == Material.WOOD_SWORD) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your sword");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_HELMET || p.getInventory().getItemInHand().getType() == Material.GOLD_HELMET || p.getInventory().getItemInHand().getType() == Material.IRON_HELMET || p.getInventory().getItemInHand().getType() == Material.LEATHER_HELMET || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_HELMET) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your helmet");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.GOLD_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.IRON_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.LEATHER_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your chestplate");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.GOLD_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.IRON_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.LEATHER_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_LEGGINGS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your leggings");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_BOOTS || p.getInventory().getItemInHand().getType() == Material.GOLD_BOOTS || p.getInventory().getItemInHand().getType() == Material.IRON_BOOTS || p.getInventory().getItemInHand().getType() == Material.LEATHER_BOOTS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_BOOTS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your boots");
			  } else {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e15 Redstone §7in your inventory §7to repair your " + p.getItemInHand().getType().toString().toLowerCase().replace("_", " "));
			  }
	  }
	  } else if (anvil.getLocation().add(0, -1, 0).getBlock().getType() == Material.GOLD_BLOCK) {
		  if (PaymentManager.pay(p, Material.REDSTONE, 25)) {
		    	  p.playEffect(anvil.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	          ItemStack item = p.getItemInHand();
	          item.setDurability((short)0);
	          p.setItemInHand(null);
	          Item spawnedItem = p.getWorld().dropItem(anvil.getLocation().add(0.5D, 1.5D, 0.5D), item);
	          spawnedItem.setVelocity(new Vector(0, 0, 0));
	          spawnedItem.setItemStack(item);
	          spawnedItem.setPickupDelay(80);
	          BukkitScheduler sched = RedstonePVP.getInstance().getServer().getScheduler();
	          sched.scheduleSyncDelayedTask(RedstonePVP.getInstance(), new RepairTask(p, spawnedItem, RedstonePVP.getInstance()), 20L);
		  } else {
			  if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD || p.getInventory().getItemInHand().getType() == Material.GOLD_SWORD || p.getInventory().getItemInHand().getType() == Material.IRON_SWORD || p.getInventory().getItemInHand().getType() == Material.STONE_SWORD || p.getInventory().getItemInHand().getType() == Material.WOOD_SWORD) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your sword");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_HELMET || p.getInventory().getItemInHand().getType() == Material.GOLD_HELMET || p.getInventory().getItemInHand().getType() == Material.IRON_HELMET || p.getInventory().getItemInHand().getType() == Material.LEATHER_HELMET || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_HELMET) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your helmet");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.GOLD_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.IRON_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.LEATHER_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your chestplate");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.GOLD_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.IRON_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.LEATHER_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_LEGGINGS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your leggings");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_BOOTS || p.getInventory().getItemInHand().getType() == Material.GOLD_BOOTS || p.getInventory().getItemInHand().getType() == Material.IRON_BOOTS || p.getInventory().getItemInHand().getType() == Material.LEATHER_BOOTS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_BOOTS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your boots");
			  } else {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e25 Redstone §7in your inventory §7to repair your " + p.getItemInHand().getType().toString().toLowerCase().replace("_", " "));
			  }
	  }
	  } else {
		  if (PaymentManager.pay(p, Material.REDSTONE, 40)) {
		    	  p.playEffect(anvil.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	          ItemStack item = p.getItemInHand();
	          item.setDurability((short)0);
	          p.setItemInHand(null);
	          Item spawnedItem = p.getWorld().dropItem(anvil.getLocation().add(0.5D, 1.5D, 0.5D), item);
	          spawnedItem.setVelocity(new Vector(0, 0, 0));
	          spawnedItem.setItemStack(item);
	          spawnedItem.setPickupDelay(80);
	          BukkitScheduler sched = RedstonePVP.getInstance().getServer().getScheduler();
	          sched.scheduleSyncDelayedTask(RedstonePVP.getInstance(), new RepairTask(p, spawnedItem, RedstonePVP.getInstance()), 20L);
		  } else {
			  if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD || p.getInventory().getItemInHand().getType() == Material.GOLD_SWORD || p.getInventory().getItemInHand().getType() == Material.IRON_SWORD || p.getInventory().getItemInHand().getType() == Material.STONE_SWORD || p.getInventory().getItemInHand().getType() == Material.WOOD_SWORD) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your sword");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_HELMET || p.getInventory().getItemInHand().getType() == Material.GOLD_HELMET || p.getInventory().getItemInHand().getType() == Material.IRON_HELMET || p.getInventory().getItemInHand().getType() == Material.LEATHER_HELMET || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_HELMET) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your helmet");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.GOLD_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.IRON_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.LEATHER_CHESTPLATE || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your chestplate");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.GOLD_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.IRON_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.LEATHER_LEGGINGS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_LEGGINGS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your leggings");
			  } else if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_BOOTS || p.getInventory().getItemInHand().getType() == Material.GOLD_BOOTS || p.getInventory().getItemInHand().getType() == Material.IRON_BOOTS || p.getInventory().getItemInHand().getType() == Material.LEATHER_BOOTS || p.getInventory().getItemInHand().getType() == Material.CHAINMAIL_BOOTS) {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your boots");
			  } else {
	              p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstone §7in your inventory §7to repair your " + p.getItemInHand().getType().toString().toLowerCase().replace("_", " "));
			  }
	  }
	  }
  }
}
