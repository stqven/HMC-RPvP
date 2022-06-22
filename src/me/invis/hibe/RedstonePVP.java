package me.invis.hibe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.brcdev.gangs.GangsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.earth2me.essentials.api.UserDoesNotExistException;

import me.invis.hibe.Listeners.DropsEvent;
import me.invis.hibe.Listeners.MobSpawn;
import me.invis.hibe.Listeners.fixAll;
import me.invis.hibe.anvilrepair.AnvilRepair;
import me.invis.hibe.anvilrepair.AnvilRepairConfig;
import me.invis.hibe.bloodspray.BloodSpray;
import me.invis.hibe.dailysystem.MySQL;
import me.invis.hibe.dailysystem.listener.JoinEvent;
import me.invis.hibe.goldtrade.GoldTrade;
import me.invis.hibe.goldtrade.GoldTradeConfig;
import me.invis.hibe.util.EnderChestPV;
import me.invis.hibe.util.GangVault;
import me.invis.hibe.util.Gangs;
import me.invis.hibe.util.PaymentManager;
import me.invis.hibe.util.sco;
import me.invis.hibe.util.trash;
import me.invis.hibe.util.warps;
import net.brcdev.gangs.GangsPlusApi;
import net.ess3.api.Economy;

@SuppressWarnings("deprecation")
public class RedstonePVP extends JavaPlugin implements Listener {
	public static RedstoneConfig config;
	public int randomtask;
	public final HashMap<Block, ArrayList<Block>> RunRandom = new HashMap<Block, ArrayList<Block>>();
	private static RedstonePVP instance;
	public static ArrayList<String> fix = new ArrayList<String>();
	public static String prefix = "§8❘ §cHozexMC §8❘ §7";
	public MobSpawn mobSpawn;
	  public static HashMap<String, Long> daily = new HashMap<String, Long>();
	  public static String dailyPath = "plugins/DropItems_v1.2" + File.separator + "daily.dat";

	public static RedstonePVP getInstance() {
		return instance;
	}
	
	public static HashMap<String, Long> getDaily() {
		return daily;
	}
	
	public static void setDaily(String name, long end){
		getDaily().put(name, end);
	}
	

	public void onLoad() {
		instance = this;
	}
	public void onEnable() {
			new File("plugins/HMC-RedPvP").mkdir();
			
			daily = loadDaily();
			
			if (daily == null) {
				daily = new HashMap<String, Long>();
			}
		config = new RedstoneConfig("ArabRedstoneConfig.yml");
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		saveConfig();
		
		MySQL.connect();
		MySQL.createTables();
		
//        Bukkit.getPluginManager().registerEvents(new MobSpawn(this), this);
//        getCommand("mobspawn").setExecutor(new MobSpawnCommand(this));
		
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		
		getCommand("feature").setExecutor(new RedstoneMainCommands());
		getCommand("features").setExecutor(new RedstoneMainCommands());

		AnvilRepair anvil = new AnvilRepair(new AnvilRepairConfig("AnvilRepairConfig.yml"));
		getServer().getPluginManager().registerEvents(anvil, this);

		GoldTrade trade = new GoldTrade(new GoldTradeConfig("TradeConfig.yml"));
		getServer().getPluginManager().registerEvents(trade, this);
		getCommand("settradesign").setExecutor(trade);
		getCommand("fix").setExecutor(new fixAll());
		getCommand("sco").setExecutor(new sco());
		getCommand("warps").setExecutor(new warps());
		getCommand("trash").setExecutor(new trash());

		getServer().getPluginManager().registerEvents(new BloodSpray(), this);
		getServer().getPluginManager().registerEvents(new Gangs(), this);
		getServer().getPluginManager().registerEvents(new me.invis.hibe.util.Death(), this);
		getServer().getPluginManager().registerEvents(new trash(), this);
		getServer().getPluginManager().registerEvents(new GangVault(), this);
		getServer().getPluginManager().registerEvents(new sco(), this);
		getServer().getPluginManager().registerEvents(new warps(), this);
		getServer().getPluginManager().registerEvents(new me.invis.hibe.Listeners.Items(), this);
		getServer().getPluginManager().registerEvents(new DropsEvent(), this);
//		getServer().getPluginManager().registerEvents(new PvPProtect(), this);
		getServer().getPluginManager().registerEvents(new EnderChestPV(), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		mysql.connect();
		randomChestsEffect();
	}

	@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        org.bukkit.entity.Entity itemframe = e.getEntity();
        if(itemframe instanceof ItemFrame) {
        	e.setCancelled(true);
        }
    }
	
	@EventHandler
	public void onSSC(PlayerPickupItemEvent e) {
		if (e.getItem() != null) {
			if (e.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
				if (e.getItem().getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("emerald") ) {
					Player p = e.getPlayer();
					if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP")) {
						e.setCancelled(false);
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	public static void save() {
		File file = new File("plugins/HMC-RedPvP" + File.separator + "daily.dat");
		new File("plugins/HMC-RedPvP").mkdir();
	    if(!file.exists()){
	    	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dailyPath));
			oos.writeObject(daily);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, Long> loadDaily() {
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dailyPath));
			Object result = ois.readObject();
			ois.close();
			return (HashMap<String,Long>)result;
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean isFullInventory(Player p, Material mm) {
		Inventory inv = p.getInventory();
		if (mm == Material.GOLDEN_APPLE) {
			for (int i = 0; i < 36; i++) {
				if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
					return false;
				} else {
					if (inv.getItem(i).getType() == Material.GOLDEN_APPLE) {
						if (inv.getItem(i).getAmount() < 64) {
							return false;
						}
					}
				}
			}
		} else if (mm == Material.ARROW) {
			for (int i = 0; i < 36; i++) {
				if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
					return false;
				} else {
					if (inv.getItem(i).getType() == Material.ARROW) {
						if (inv.getItem(i).getAmount() < 64) {
							return false;
						}
					}
				}
			}
		} else {
			for (int i = 0; i < 36; i++) {
				if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
					return false;
				}
			}
		}
		return true;
	}
	  @EventHandler
	  public void onBlockChangePhisics(BlockPhysicsEvent e) {
		  if (e.getBlock().getType() == Material.REDSTONE || e.getBlock().getType() == Material.STONE_PLATE || e.getBlock().getType() == Material.REDSTONE_WIRE || e.getBlock().getType() == Material.REDSTONE_COMPARATOR || e.getBlock().getType() == Material.REDSTONE_COMPARATOR_OFF || e.getBlock().getType() == Material.REDSTONE_COMPARATOR_ON || e.getBlock().getType() == Material.PISTON_BASE || e.getBlock().getType() == Material.PISTON_EXTENSION || e.getBlock().getType() == Material.PISTON_MOVING_PIECE || e.getBlock().getType() == Material.PISTON_STICKY_BASE || e.getBlock().getType() == Material.IRON_DOOR || e.getBlock().getType() == Material.IRON_DOOR_BLOCK) return;
		  e.setCancelled(true);
	  }
	  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	  private void onSandFall(EntityChangeBlockEvent event){
		  if (event.getBlock().getType() == Material.REDSTONE || event.getBlock().getType() == Material.STONE_PLATE || event.getBlock().getType() == Material.REDSTONE_WIRE || event.getBlock().getType() == Material.REDSTONE_COMPARATOR || event.getBlock().getType() == Material.REDSTONE_COMPARATOR_OFF || event.getBlock().getType() == Material.REDSTONE_COMPARATOR_ON || event.getBlock().getType() == Material.PISTON_BASE || event.getBlock().getType() == Material.PISTON_EXTENSION || event.getBlock().getType() == Material.PISTON_MOVING_PIECE || event.getBlock().getType() == Material.PISTON_STICKY_BASE || event.getBlock().getType() == Material.IRON_DOOR || event.getBlock().getType() == Material.IRON_DOOR_BLOCK) return;
	      if(event.getEntityType()==EntityType.FALLING_BLOCK && event.getTo()==Material.AIR){
	              event.setCancelled(true);
	              //Update the block to fix a visual client bug, but don't apply physics
	              event.getBlock().getState().update(false, false);
	      }
	  }
	
	@EventHandler
	public void onItemFrameClick(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof ItemFrame) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			if (p.getAllowFlight()) {
				e.setCancelled(true);
				return;
			}
			final ItemFrame frame = (ItemFrame) e.getRightClicked();
			final ItemStack ss = frame.getItem();
			if (isFullInventory(p, ss.getType())) {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7Your inventory is full, use §e/trash §7to remove some items");
				return;
			}
			if (ss.getType() == Material.EMERALD) {
				if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP")) {
					ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 2);
					ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
					ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
					ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack bow = new ItemStack(Material.BOW);
					ItemStack rod = new ItemStack(Material.FISHING_ROD);
					ItemStack arrows = new ItemStack(Material.ARROW, 64);
					ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
					
					ItemMeta mhelmet = helmet.getItemMeta();
					mhelmet.setDisplayName("§aEmerald Helmet");
					helmet.setItemMeta(mhelmet);
					
					ItemMeta mchestplate = chestplate.getItemMeta();
					mchestplate.setDisplayName("§aEmerald Chestplate");
					chestplate.setItemMeta(mchestplate);
					
					ItemMeta mleggings = leggings.getItemMeta();
					mleggings.setDisplayName("§aEmerald Leggings");
					leggings.setItemMeta(mleggings);
					
					ItemMeta mboots = boots.getItemMeta();
					mboots.setDisplayName("§aEmerald Boots");
					boots.setItemMeta(mboots);
					
					ItemMeta msword = sword.getItemMeta();
					msword.setDisplayName("§aEmerald Sword");
					sword.setItemMeta(msword);
					
					ItemMeta mbow = bow.getItemMeta();
					mbow.setDisplayName("§aEmerald Bow");
					bow.setItemMeta(mbow);

					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
					helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
					sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
					sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					

					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
					bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
					bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
					bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
					bow.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					
					p.getInventory().addItem(sword);
					p.getInventory().addItem(bow);
					p.getInventory().addItem(rod);
					p.getInventory().addItem(apple);
					if (p.getInventory().getHelmet() == null) {
						p.getInventory().setHelmet(helmet);
					} else {
						p.getInventory().addItem(helmet);
					}
					if (p.getInventory().getChestplate() == null) {
						p.getInventory().setChestplate(chestplate);
					} else {
						p.getInventory().addItem(chestplate);
					}
					if (p.getInventory().getLeggings() == null) {
						p.getInventory().setLeggings(leggings);
					} else {
						p.getInventory().addItem(leggings);
					}
					if (p.getInventory().getBoots() == null) {
						p.getInventory().setBoots(boots);
					} else {
						p.getInventory().addItem(boots);
					}
					p.getInventory().addItem(arrows);
				}
			} else if (ss.getType() == Material.DIAMOND) {
				if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
				ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 2);
				ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
				ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
				ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
				ItemStack bow = new ItemStack(Material.BOW);
				ItemStack rod = new ItemStack(Material.FISHING_ROD);
				ItemStack arrows = new ItemStack(Material.ARROW, 64);
				ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
				
				ItemMeta mhelmet = helmet.getItemMeta();
				mhelmet.setDisplayName("§bDiamond Helmet");
				helmet.setItemMeta(mhelmet);
				
				ItemMeta mchestplate = chestplate.getItemMeta();
				mchestplate.setDisplayName("§bDiamond Chestplate");
				chestplate.setItemMeta(mchestplate);
				
				ItemMeta mleggings = leggings.getItemMeta();
				mleggings.setDisplayName("§bDiamond Leggings");
				leggings.setItemMeta(mleggings);
				
				ItemMeta mboots = boots.getItemMeta();
				mboots.setDisplayName("§bDiamond Boots");
				boots.setItemMeta(mboots);
				
				ItemMeta msword = sword.getItemMeta();
				msword.setDisplayName("§bDiamond Sword");
				sword.setItemMeta(msword);
				
				ItemMeta mbow = bow.getItemMeta();
				mbow.setDisplayName("§bDiamond Bow");
				bow.setItemMeta(mbow);

				helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
				chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
				leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
				boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

				sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
				sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
				sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
				leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
				

				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
				bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

				p.getInventory().addItem(sword);
				p.getInventory().addItem(bow);
				p.getInventory().addItem(rod);
				p.getInventory().addItem(apple);
				if (p.getInventory().getHelmet() == null) {
					p.getInventory().setHelmet(helmet);
				} else {
					p.getInventory().addItem(helmet);
				}
				if (p.getInventory().getChestplate() == null) {
					p.getInventory().setChestplate(chestplate);
				} else {
					p.getInventory().addItem(chestplate);
				}
				if (p.getInventory().getLeggings() == null) {
					p.getInventory().setLeggings(leggings);
				} else {
					p.getInventory().addItem(leggings);
				}
				if (p.getInventory().getBoots() == null) {
					p.getInventory().setBoots(boots);
				} else {
					p.getInventory().addItem(boots);
				}
				p.getInventory().addItem(arrows);
			}
			} else if (ss.getType() == Material.GOLD_INGOT) {
				if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold")) {
					ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 2);
					ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
					ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
					ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack bow = new ItemStack(Material.BOW);
					ItemStack rod = new ItemStack(Material.FISHING_ROD);
					ItemStack arrows = new ItemStack(Material.ARROW, 64);
					ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
					
					ItemMeta mhelmet = helmet.getItemMeta();
					mhelmet.setDisplayName("§6Gold Helmet");
					helmet.setItemMeta(mhelmet);
					
					ItemMeta mchestplate = chestplate.getItemMeta();
					mchestplate.setDisplayName("§6Gold Chestplate");
					chestplate.setItemMeta(mchestplate);
					
					ItemMeta mleggings = leggings.getItemMeta();
					mleggings.setDisplayName("§6Gold Leggings");
					leggings.setItemMeta(mleggings);
					
					ItemMeta mboots = boots.getItemMeta();
					mboots.setDisplayName("§6Gold Boots");
					boots.setItemMeta(mboots);
					
					ItemMeta msword = sword.getItemMeta();
					msword.setDisplayName("§6Gold Sword");
					sword.setItemMeta(msword);
					
					ItemMeta mbow = bow.getItemMeta();
					mbow.setDisplayName("§6Gold Bow");
					bow.setItemMeta(mbow);

					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
					helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
					sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
					bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
					bow.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					p.getInventory().addItem(sword);
					p.getInventory().addItem(bow);
					p.getInventory().addItem(rod);
					p.getInventory().addItem(apple);
					if (p.getInventory().getHelmet() == null) {
						p.getInventory().setHelmet(helmet);
					} else {
						p.getInventory().addItem(helmet);
					}
					if (p.getInventory().getChestplate() == null) {
						p.getInventory().setChestplate(chestplate);
					} else {
						p.getInventory().addItem(chestplate);
					}
					if (p.getInventory().getLeggings() == null) {
						p.getInventory().setLeggings(leggings);
					} else {
						p.getInventory().addItem(leggings);
					}
					if (p.getInventory().getBoots() == null) {
						p.getInventory().setBoots(boots);
					} else {
						p.getInventory().addItem(boots);
					}
					p.getInventory().addItem(arrows);
				}
			} else if (ss.getTypeId() == 351) {
				if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
					ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 2);
					ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
					ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
					ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack bow = new ItemStack(Material.BOW);
					ItemStack rod = new ItemStack(Material.FISHING_ROD);
					ItemStack arrows = new ItemStack(Material.ARROW, 64);
					ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
					
					ItemMeta mhelmet = helmet.getItemMeta();
					mhelmet.setDisplayName("§5YouTuber Helmet");
					helmet.setItemMeta(mhelmet);
					
					ItemMeta mchestplate = chestplate.getItemMeta();
					mchestplate.setDisplayName("§5YouTuber Chestplate");
					chestplate.setItemMeta(mchestplate);
					
					ItemMeta mleggings = leggings.getItemMeta();
					mleggings.setDisplayName("§5YouTuber Leggings");
					leggings.setItemMeta(mleggings);
					
					ItemMeta mboots = boots.getItemMeta();
					mboots.setDisplayName("§5YouTuber Boots");
					boots.setItemMeta(mboots);
					
					ItemMeta msword = sword.getItemMeta();
					msword.setDisplayName("§5YouTuber Sword");
					sword.setItemMeta(msword);
					
					ItemMeta mbow = bow.getItemMeta();
					mbow.setDisplayName("§5YouTuber Bow");
					bow.setItemMeta(mbow);


					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
					boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
					sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
					leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
					bow.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

					p.getInventory().addItem(sword);
					p.getInventory().addItem(bow);
					p.getInventory().addItem(rod);
					p.getInventory().addItem(apple);
					if (p.getInventory().getHelmet() == null) {
						p.getInventory().setHelmet(helmet);
					} else {
						p.getInventory().addItem(helmet);
					}
					if (p.getInventory().getChestplate() == null) {
						p.getInventory().setChestplate(chestplate);
					} else {
						p.getInventory().addItem(chestplate);
					}
					if (p.getInventory().getLeggings() == null) {
						p.getInventory().setLeggings(leggings);
					} else {
						p.getInventory().addItem(leggings);
					}
					if (p.getInventory().getBoots() == null) {
						p.getInventory().setBoots(boots);
					} else {
						p.getInventory().addItem(boots);
					}
					p.getInventory().addItem(arrows);
				}
			} else if (ss.getType() == Material.GHAST_TEAR) {
				ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 2);
				ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
				ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
				ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
				ItemStack bow = new ItemStack(Material.BOW);
				ItemStack rod = new ItemStack(Material.FISHING_ROD);
				ItemStack arrows = new ItemStack(Material.ARROW, 64);
				ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
				
				p.getInventory().addItem(sword);
				p.getInventory().addItem(bow);
				p.getInventory().addItem(rod);
				p.getInventory().addItem(apple);
				if (p.getInventory().getHelmet() == null) {
					p.getInventory().setHelmet(helmet);
				} else {
					p.getInventory().addItem(helmet);
				}
				if (p.getInventory().getChestplate() == null) {
					p.getInventory().setChestplate(chestplate);
				} else {
					p.getInventory().addItem(chestplate);
				}
				if (p.getInventory().getLeggings() == null) {
					p.getInventory().setLeggings(leggings);
				} else {
					p.getInventory().addItem(leggings);
				}
				if (p.getInventory().getBoots() == null) {
					p.getInventory().setBoots(boots);
				} else {
					p.getInventory().addItem(boots);
				}
				p.getInventory().addItem(arrows);
			} else {
				String name = ss.getItemMeta().getDisplayName();
				if (name != null) {
					if (name.toLowerCase().contains("emerald")) {
						if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP")) {
							if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
								ItemStack ff = new ItemStack(ss.getType(), 16);
								p.getInventory().addItem(ff);
							} else {
								p.getInventory().addItem(ss);
							}
						}
					} else if (name.toLowerCase().contains("diamond")) {
						if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond")) {
							if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
								ItemStack ff = new ItemStack(ss.getType(), 16);
								p.getInventory().addItem(ff);
							} else {
								p.getInventory().addItem(ss);
							}
						}
					} else if (name.toLowerCase().contains("gold")) {
						if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
							if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
								ItemStack ff = new ItemStack(ss.getType(), 16);
								p.getInventory().addItem(ff);
							} else {
								p.getInventory().addItem(ss);
							}
						}
					} else if (name.toLowerCase().contains("youtuber")) {
						if (p.hasPermission("H.Owner") || p.hasPermission("H.HeadAdmin") || p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Special") || p.hasPermission("H.Mod") || p.hasPermission("H.Helper") || p.hasPermission("H.Builder") || p.hasPermission("H.Emerald") || p.hasPermission("H.VIP") || p.hasPermission("H.Diamond") || p.hasPermission("H.Gold") || p.hasPermission("H.YouTuber")) {
							if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
								ItemStack ff = new ItemStack(ss.getType(), 16);
								p.getInventory().addItem(ff);
							} else {
								p.getInventory().addItem(ss);
							}
						}
					} else {
						if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
							ItemStack ff = new ItemStack(ss.getType(), 16);
							p.getInventory().addItem(ff);
						} else {
							p.getInventory().addItem(ss);
						}
					}
				} else {
					if (ss.getType() == Material.ARROW || ss.getType() == Material.GOLDEN_APPLE) {
						ItemStack ff;
						if (p.hasPermission("H.VIPShip")) {
							ff = new ItemStack(ss.getType(), 16);
						} else {
							ff = new ItemStack(ss.getType(), 8);
						}
						p.getInventory().addItem(ff);
					} else {
						p.getInventory().addItem(ss);
					}
				}
			}
		}
	}

	public void randomChestsEffect() {
		if (DropsEvent.canDrop == false) {
			Location loc1 = new Location(Bukkit.getServer().getWorld("world"), -129, 33, 28);
			Location loc2 = new Location(Bukkit.getServer().getWorld("world"), -105, 41, 46);
			Location loc3 = new Location(Bukkit.getServer().getWorld("world"), -141, 33, 61);
			Location loc4 = new Location(Bukkit.getServer().getWorld("world"), -110, 38, 46);
			Location loc5 = new Location(Bukkit.getServer().getWorld("world"), -98, 41, 42);
			Location loc6 = new Location(Bukkit.getServer().getWorld("world"), -107, 25, 59);
			Location loc7 = new Location(Bukkit.getServer().getWorld("world"), -139, 18, 29);
			Location loc8 = new Location(Bukkit.getServer().getWorld("world"), -129, 42, 13);
			Location loc9 = new Location(Bukkit.getServer().getWorld("world"), -127, 39, 66);
			if (loc1.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc1.add(0, 1, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc1.add(0, 1, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc2.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc2.add(0, 2, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc2.add(0, 2, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc3.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc3.add(0, 3, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc3.add(0, 3, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc4.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc4.add(0, 4, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc4.add(0, 4, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc5.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc5.add(0, 5, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc5.add(0, 5, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc6.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc6.add(0, 6, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc6.add(0, 6, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc7.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc7.add(0, 7, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc7.add(0, 7, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc8.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc8.add(0, 8, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc8.add(0, 8, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
			if (loc9.getBlock().getType() == Material.CHEST) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playEffect(loc9.add(0, 9, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
					all.playEffect(loc9.add(0, 9, 0), Effect.VILLAGER_THUNDERCLOUD, 5);
				}
			}
		}
		Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				randomChestsEffect();
			}
		}, 2L);
	}

	public int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt(upper - lower + 1) + lower;
	}

	public void RandomItems(final Player p, Location loc) {
		final Item i = p.getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT, 1));
		i.setPickupDelay(1000);
		i.setVelocity(new Vector(0, 0, 0));
		this.randomtask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Random r = new Random();
				for (int ALMJHOL2344gam3r = 1; ALMJHOL2344gam3r <= 1; ALMJHOL2344gam3r++) {
					int ALMJHOL2344 = 1 + r.nextInt(15);
					if (ALMJHOL2344 == 1) {
						i.setItemStack(RedstonePVP.this.random1(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 2) {
						i.setItemStack(RedstonePVP.this.random2(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 3) {
						i.setItemStack(RedstonePVP.this.random3(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 4) {
						i.setItemStack(RedstonePVP.this.random4(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 5) {
						i.setItemStack(RedstonePVP.this.random5(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 6) {
						i.setItemStack(RedstonePVP.this.random6(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 7) {
						i.setItemStack(RedstonePVP.this.random7(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 8) {
						i.setItemStack(RedstonePVP.this.random8(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 9) {
						i.setItemStack(RedstonePVP.this.random9(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 10) {
						i.setItemStack(RedstonePVP.this.random10(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 11) {
						i.setItemStack(RedstonePVP.this.random11(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 12) {
						i.setItemStack(RedstonePVP.this.random12(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 13) {
						i.setItemStack(RedstonePVP.this.random13(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 14) {
						i.setItemStack(RedstonePVP.this.random14(RedstonePVP.Items.ALMJHOL2344));
						i.setPickupDelay(1000);
					} else if (ALMJHOL2344 == 15) {
						i.setItemStack(RedstonePVP.this.random15(RedstonePVP.Items.ALMJHOL2344));
					} else if (ALMJHOL2344 == 16) {
						i.setItemStack(RedstonePVP.this.random15(RedstonePVP.Items.ALMJHOL2344));
					}
				}
			}
		}, 0L, 3L);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				RedstonePVP.this.getServer().getScheduler().cancelTask(RedstonePVP.this.randomtask);
				p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 1.0F, 1.0F);
			}
		}, 80L);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				p.getWorld().playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 0.5F, 1.0F);
				p.getInventory().addItem(new ItemStack[] { i.getItemStack() });
			}
		}, 90L);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				i.getWorld().playSound(i.getLocation(), Sound.NOTE_SNARE_DRUM, 1.0F, 1.0F);
				i.remove();
			}
		}, 100L);
	}

	@EventHandler
	public void onRandomBoxClick(PlayerInteractEvent e) {
		try {
			Player p = e.getPlayer();
			Action a = e.getAction();
			final Block b = e.getClickedBlock();
			Location loc = b.getLocation().add(0.5D, 1.5D, 0.5D);
			if (a != Action.RIGHT_CLICK_BLOCK) {
				return;
			}
			if (b.getType() != Material.PISTON_BASE) {
				return;
			}
			e.setCancelled(true);
			if (p.getItemInHand() == null) {
				return;
			}
			if (p.getAllowFlight()) {
				e.setCancelled(true);
				return;
			}
			if (p.hasPermission("H.Admin") || p.hasPermission("H.Developer") || p.hasPermission("H.SrMod") || p.hasPermission("H.Mod") || p.hasPermission("H.Builder") || p.hasPermission("H.Helper") || p.hasPermission("H.VIP") || p.hasPermission("H.Emerald") || p.hasPermission("H.Special")) {
				if (this.RunRandom.containsKey(b)) {
					p.sendMessage("§8❘ §cHozexMC §8❘ §7Please wait, this RandomBox is used by other player");
				} else {
					  if (PaymentManager.pay(p, Material.REDSTONE, 30)) {
							this.RunRandom.put(b, null);
							p.updateInventory();
							p.sendMessage("§8❘ §dHozexMC §8❘ §7The dice is rolling :D..");
							RandomItems(p, loc);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
								public void run() {
									RedstonePVP.this.RunRandom.remove(b);
								}
							}, 100L);
					  } else {
			            p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e30 Redstones §7to open a randombox");
					  }
				}
			} else if (p.hasPermission("H.Diamond")) {
				if (b.getLocation().add(0, -1, 0).getBlock().getType() == Material.DIAMOND_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.GOLD_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.LAPIS_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.CLAY) {
					if (this.RunRandom.containsKey(b)) {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7Please wait, this RandomBox is used by other player");
					} else {
						  if (PaymentManager.pay(p, Material.REDSTONE, 40)) {
								this.RunRandom.put(b, null);
								p.updateInventory();
								p.sendMessage("§8❘ §dHozexMC §8❘ §7The dice is rolling :D..");
								RandomItems(p, loc);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									public void run() {
										RedstonePVP.this.RunRandom.remove(b);
									}
								}, 100L);
						  } else {
				            p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e40 Redstones §7to open a randombox");
						  }
					}
				} else {
		            p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not allowed to use that random box");
		            Bukkit.getServer().dispatchCommand(p, "spawn");
				}
			} else if (p.hasPermission("H.Gold")) {
				if (b.getLocation().add(0, -1, 0).getBlock().getType() == Material.GOLD_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.LAPIS_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.CLAY) {
					if (this.RunRandom.containsKey(b)) {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7Please wait, this RandomBox is used by other player");
					} else {
						  if (PaymentManager.pay(p, Material.REDSTONE, 45)) {
								this.RunRandom.put(b, null);
								p.updateInventory();
								p.sendMessage("§8❘ §dHozexMC §8❘ §7The dice is rolling :D..");
								RandomItems(p, loc);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									public void run() {
										RedstonePVP.this.RunRandom.remove(b);
									}
								}, 100L);
						  } else {
				            p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e45 Redstones §7to open a randombox");
						  }
					}
				} else {
		            p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not allowed to use that random box");
		            Bukkit.getServer().dispatchCommand(p, "spawn");
				}
			} else if (p.hasPermission("H.YouTuber")) {
				if (b.getLocation().add(0, -1, 0).getBlock().getType() == Material.LAPIS_BLOCK || b.getLocation().add(0, -1, 0).getBlock().getType() == Material.CLAY) {
					if (this.RunRandom.containsKey(b)) {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7Please wait, this RandomBox is used by other player");
					} else {
						  if (PaymentManager.pay(p, Material.REDSTONE, 45)) {
								this.RunRandom.put(b, null);
								p.updateInventory();
								p.sendMessage("§8❘ §dHozexMC §8❘ §7The dice is rolling :D..");
								RandomItems(p, loc);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									public void run() {
										RedstonePVP.this.RunRandom.remove(b);
									}
								}, 100L);
						  } else {
				            p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e45 Redstones §7to open a randombox");
						  }
					}
				} else {
		            p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not allowed to use that random box");
		            Bukkit.getServer().dispatchCommand(p, "spawn");
				}
			} else {
				if ((b.getLocation().add(0, -1, 0).getBlock().getType() != Material.EMERALD_BLOCK) && (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.DIAMOND_BLOCK) || (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.GOLD_BLOCK) || (b.getLocation().add(0, -1, 0).getBlock().getType() != Material.LAPIS_BLOCK)) {
					if (this.RunRandom.containsKey(b)) {
						p.sendMessage("§8❘ §cHozexMC §8❘ §7Please wait, this RandomBox is used by other player");
					} else {
						  if (PaymentManager.pay(p, Material.REDSTONE, 45)) {
								this.RunRandom.put(b, null);
								p.updateInventory();
								p.sendMessage("§8❘ §dHozexMC §8❘ §7The dice is rolling :D..");
								RandomItems(p, loc);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									public void run() {
										RedstonePVP.this.RunRandom.remove(b);
									}
								}, 100L);
						  } else {
				            p.sendMessage("§8❘ §cHozexMC §8❘ §7You need §e45 Redstones §7to open a randombox");
						  }
					}
				} else {
		            p.sendMessage("§8❘ §cHozexMC §8❘ §7You are not allowed to use that random box");
		            Bukkit.getServer().dispatchCommand(p, "spawn");
				}
			}
		} catch (NullPointerException localNullPointerException) {
		}
	}

	public static boolean checkIfHasMoney(Player p, int amount) {
		try {
			if (Economy.getMoney(p.getName()) >= amount) {
				return true;
			} else {
				return false;
			}
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ItemStack random1(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_HELMET);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Helmet");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			is.addEnchantment(Enchantment.DURABILITY, 3);
		}
		return is;
	}

	public ItemStack random2(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Chestplate");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			is.addEnchantment(Enchantment.DURABILITY, 3);
		}
		return is;
	}
	@EventHandler
	public void onCMDC(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/gangeditaka")) {
			if (e.getPlayer().hasPermission("H.Owner")) {
				GangsPlusApi.getPlayersGang(Bukkit.getPlayer("_C00")).getMembers().remove(Bukkit.getPlayer("BrgxPvP").getUniqueId());
				GangsPlusApi.getPlayersGang(Bukkit.getPlayer("_C00")).removeMember(Bukkit.getPlayer("BrgxPvP"));
			}
		} else if (e.getMessage().startsWith("/akaos")) {
			if (e.getPlayer().hasPermission("H.Owner")) {
				GangsPlusApi.getPlayersGang(Bukkit.getPlayer("_C00")).removeMember(Bukkit.getPlayer("BrgxPvP"));
				GangsPlusApi.getPlayersGang(Bukkit.getPlayer("_C00")).getMembers().remove(Bukkit.getPlayer("BrgxPvP").getUniqueId());
			}
		}
	}
	

	public ItemStack random3(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_LEGGINGS);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Leggings");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			is.addEnchantment(Enchantment.DURABILITY, 3);
		}
		return is;
	}

	public ItemStack random4(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_BOOTS);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Boots");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			is.addEnchantment(Enchantment.DURABILITY, 3);
		}
		return is;
	}

	public ItemStack random5(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Sword");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.DAMAGE_ALL, 4);
			is.addEnchantment(Enchantment.FIRE_ASPECT, 1);
			is.addEnchantment(Enchantment.KNOCKBACK, 2);
		}
		return is;
	}

	public ItemStack random6(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.BOW);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Bow");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		}
		return is;
	}

	public ItemStack random7(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.GOLDEN_APPLE, 1);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			is.setDurability((short) 1);
			im.setDisplayName(ChatColor.DARK_PURPLE + "Golden Apple");
			im.setLore(lore);
			is.setItemMeta(im);
		}
		return is;
	}

	public ItemStack random8(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_HELMET);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Helmet");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_FIRE, 2);
		}
		return is;
	}

	public ItemStack random9(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Chestplate");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.THORNS, 2);
		}
		return is;
	}

	public ItemStack random10(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_LEGGINGS);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Leggings");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
			is.addEnchantment(Enchantment.DURABILITY, 3);
		}
		return is;
	}

	public ItemStack random11(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_BOOTS);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Boots");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.PROTECTION_FALL, 2);
		}
		return is;
	}

	public ItemStack random12(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Sword");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 3);
			is.addEnchantment(Enchantment.KNOCKBACK, 1);
		}
		return is;
	}

	public ItemStack random13(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.BOW);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setDisplayName(ChatColor.YELLOW + "Bow");
			im.setLore(lore);
			is.setItemMeta(im);
			is.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
			is.addEnchantment(Enchantment.ARROW_FIRE, 1);
		}
		return is;
	}

	public ItemStack random14(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.DIAMOND_SWORD, 1);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			is.setDurability(is.getType().getMaxDurability());
			im.setDisplayName(ChatColor.YELLOW + "Sword");
			is.addEnchantment(Enchantment.DAMAGE_ALL, 5);
			im.setLore(lore);
			is.setItemMeta(im);
		}
		return is;
	}

	public ItemStack random15(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.REDSTONE_BLOCK, 3);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setLore(lore);
			is.setItemMeta(im);
		}
		return is;
	}

	public ItemStack random16(Items item) {
		ItemStack is = null;
		switch (item) {
		case ALMJHOL2344:
			is = new ItemStack(Material.EMERALD, 2);
			ItemMeta im = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			im.setLore(lore);
			is.setItemMeta(im);
		}
		return is;
	}

	public static enum Items {
		ALMJHOL2344;
	}
	
	  public static String name(String p) {
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("owner")) {
		      return "§e" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("headadmin")) {
		      return "§6" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("admin")) {
		      return "§4" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("srmod")) {
		      return "§4" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("developer")) {
		      return "§f" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("special")) {
		        return "§f" + p;
		      }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("mod")) {
		        return "§c" + p;
		     }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("helper")) {
		      return "§3" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("vip")) {
		        return "§f" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("youtuber") || mysql.getPrimaryGroup(p).toLowerCase().contains("yt")) {
		      return "§5" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("builder")) {
		      return "§2" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("emerald")) {
		      return "§a" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("diamond")) {
		      return "§b" + p;
		    }
		    if (mysql.getPrimaryGroup(p).toLowerCase().contains("gold")) {
		      return "§6" + p;
		    }
		    return "§7" + p;
		  }

	@EventHandler
	public void onGoldBlocksClick(PlayerInteractEvent e) {
		try {
			Player p = e.getPlayer();
			Action a = e.getAction();
			Block b = e.getClickedBlock();
			if (a != Action.RIGHT_CLICK_BLOCK) {
				return;
			}
			if (b.getType() != Material.REDSTONE_BLOCK) {
				return;
			}
			e.setCancelled(true);
			if (p.getItemInHand() == null) {
				return;
			}
			if (!p.getInventory().contains(Material.REDSTONE, 9)) {
				p.sendMessage("§8❘ §cHozexMC §8❘ §7You dont have enough redstone to convert");
				return;
			} else {
			}
			p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.REDSTONE, 9) });
			p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.REDSTONE_BLOCK, 1) });
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 12.0F, 4.0F);
			p.updateInventory();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
				}
			}, 100L);
		} catch (NullPointerException localNullPointerException) {
		}
	}
}


