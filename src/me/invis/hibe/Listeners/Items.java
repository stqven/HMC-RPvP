package me.invis.hibe.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.invis.hibe.RedstonePVP;

public class Items implements Listener {
	
	@EventHandler
	public void onDropItems(PlayerDropItemEvent e) {
		if (e.getItemDrop() == null || e.getItemDrop().getType() != EntityType.DROPPED_ITEM) return;
		if ((e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) && (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD || e.getItemDrop().getItemStack().getType() == Material.DIAMOND_HELMET || e.getItemDrop().getItemStack().getType() == Material.DIAMOND_CHESTPLATE || e.getItemDrop().getItemStack().getType() == Material.DIAMOND_LEGGINGS || e.getItemDrop().getItemStack().getType() == Material.DIAMOND_BOOTS) && (e.getItemDrop().getItemStack().getEnchantmentLevel(Enchantment.DAMAGE_ALL) < 1)) {
			e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7You can't drop this item, use §e/trash §7to get rid of it");
			e.setCancelled(true);
		} else if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("emerald") || e.getItemDrop().getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("netherstar")) {
			e.getPlayer().sendMessage("§8❘ §cHozexMC §8❘ §7You can't drop this item, use §e/trash §7to get rid of it");
			e.setCancelled(true);
		}
	}

	public static ArrayList<String> drop = new ArrayList<String>();
	
	public static ItemStack dailyHelmet() {
		ItemStack g = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Helmet");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}
	
	public static ItemStack dailyChestplate() {
		ItemStack g = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Chestplate");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack dailyLeggings() {
		ItemStack g = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Leggings");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack dailyBoots() {
		ItemStack g = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Boots");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack dailySword() {
		ItemStack g = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Sword");
		gm.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		gm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		gm.addEnchant(Enchantment.KNOCKBACK, 1, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack dailyApple() {
		ItemStack g = new ItemStack(Material.GOLDEN_APPLE, 2, (short) 1);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Apple");
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack dailyRedstone() {
		ItemStack g = new ItemStack(Material.REDSTONE_BLOCK, 32);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Redstone");
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyHelmet() {
		ItemStack g = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Helmet");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyChestplate() {
		ItemStack g = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Chestplate");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyLeggings() {
		ItemStack g = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Leggings");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyBoots() {
		ItemStack g = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Boots");
		gm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklySword() {
		ItemStack g = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Sword");
		gm.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		gm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		gm.addEnchant(Enchantment.KNOCKBACK, 2, true);
		gm.addEnchant(Enchantment.DURABILITY, 3, true);
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyApple() {
		ItemStack g = new ItemStack(Material.GOLDEN_APPLE, 10, (short) 1);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Apple");
		g.setItemMeta(gm);
		return g;
	}

	public static ItemStack weeklyEmerald() {
		ItemStack g = new ItemStack(Material.EMERALD_BLOCK, 10);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Emerald");
		g.setItemMeta(gm);
		return g;
	}
	
	public boolean checkItem(ItemStack s) {
		if (s == null)
			return false;
		if (s.getItemMeta().getDisplayName() != null) {
			String name = s.getItemMeta().getDisplayName().toLowerCase();
			if (name.contains("emerald")) {
				return true;
			} else {
				if (s.getType() == Material.DIAMOND_AXE && name.contains("netherstar")) {
					return true;
				}
				return false;
			}
		} else {
			if (s.getType() == Material.DIAMOND_SWORD || s.getType() == Material.DIAMOND_HELMET || s.getType() == Material.DIAMOND_CHESTPLATE || s.getType() == Material.DIAMOND_LEGGINGS || s.getType() == Material.DIAMOND_BOOTS) {
				return true;
			}
			return false;
		}
	}
	@EventHandler
	public void onDeathE(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		e.getDrops().removeIf(x -> checkItem(x));
	}
//	@EventHandler // Current death
//	public void onDeath(PlayerDeathEvent e) {
//		ItemStack star = new ItemStack(Material.NETHER_STAR);
//		ItemMeta mstar = star.getItemMeta();
//		mstar.setDisplayName("§fNetherstar");
//		star.setItemMeta(mstar);
//		
//		ItemStack sword1 = new ItemStack(Material.DIAMOND_SWORD);
//		ItemMeta msword1 = sword1.getItemMeta();
//		msword1.setDisplayName("§aEmerald Sword");
//		sword1.setItemMeta(msword1);
//
//		ItemStack sword2 = new ItemStack(Material.DIAMOND_SWORD);
//		ItemMeta msword2 = sword2.getItemMeta();
//		msword2.setDisplayName("§fNetherstar");
//		sword2.setItemMeta(msword2);
//
//		ItemStack helmet1 = new ItemStack(Material.DIAMOND_HELMET);
//		ItemMeta mhelmet1 = helmet1.getItemMeta();
//		mhelmet1.setDisplayName("§aEmerald Helmet");
//		helmet1.setItemMeta(mhelmet1);
//
//		ItemStack helmet2 = new ItemStack(Material.DIAMOND_HELMET);
//		ItemMeta mhelmet2 = helmet2.getItemMeta();
//		mhelmet2.setDisplayName("§fNetherstar");
//		helmet2.setItemMeta(mhelmet2);
//
//		ItemStack chestplate1 = new ItemStack(Material.DIAMOND_CHESTPLATE);
//		ItemMeta mchestplate1 = chestplate1.getItemMeta();
//		mchestplate1.setDisplayName("§aEmerald Chestplate");
//		chestplate1.setItemMeta(mchestplate1);
//
//		ItemStack chestplate2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
//		ItemMeta mchestplate2 = chestplate2.getItemMeta();
//		mchestplate2.setDisplayName("§fNetherstar");
//		chestplate2.setItemMeta(mchestplate2);
//
//		ItemStack leggings1 = new ItemStack(Material.DIAMOND_LEGGINGS);
//		ItemMeta mleggings1 = leggings1.getItemMeta();
//		mleggings1.setDisplayName("§aEmerald Leggings");
//		leggings1.setItemMeta(mleggings1);
//
//		ItemStack leggings2 = new ItemStack(Material.DIAMOND_LEGGINGS);
//		ItemMeta mleggings2 = leggings2.getItemMeta();
//		mleggings2.setDisplayName("§fNetherstar");
//		leggings2.setItemMeta(mleggings2);
//
//		ItemStack boots1 = new ItemStack(Material.DIAMOND_BOOTS);
//		ItemMeta mboots1 = boots1.getItemMeta();
//		mboots1.setDisplayName("§aEmerald Boots");
//		boots1.setItemMeta(mboots1);
//
//		ItemStack boots2 = new ItemStack(Material.DIAMOND_BOOTS);
//		ItemMeta mboots2 = boots2.getItemMeta();
//		mboots2.setDisplayName("§fNetherstar");
//		boots2.setItemMeta(mboots2);
//
//		ItemStack bow1 = new ItemStack(Material.BOW);
//		ItemMeta mbow1 = bow1.getItemMeta();
//		mbow1.setDisplayName("§aEmerald Bow");
//		bow1.setItemMeta(mbow1);
//
//		ItemStack bow2 = new ItemStack(Material.BOW);
//		ItemMeta mbow2 = bow2.getItemMeta();
//		mbow2.setDisplayName("§fNetherstar");
//		bow2.setItemMeta(mbow2);
//		
//		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
//		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
//		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
//		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
//		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
//		ItemStack bow = new ItemStack(Material.BOW);
//		
//		ItemMeta mhelmet = helmet.getItemMeta();
//		mhelmet.setDisplayName("§aEmerald Helmet");
//		helmet.setItemMeta(mhelmet);
//		
//		ItemMeta mchestplate = chestplate.getItemMeta();
//		mchestplate.setDisplayName("§aEmerald Chestplate");
//		chestplate.setItemMeta(mchestplate);
//		
//		ItemMeta mleggings = leggings.getItemMeta();
//		mleggings.setDisplayName("§aEmerald Leggings");
//		leggings.setItemMeta(mleggings);
//		
//		ItemMeta mboots = boots.getItemMeta();
//		mboots.setDisplayName("§aEmerald Boots");
//		boots.setItemMeta(mboots);
//		
//		ItemMeta msword = sword.getItemMeta();
//		msword.setDisplayName("§aEmerald Sword");
//		sword.setItemMeta(msword);
//		
//		ItemMeta mbow = bow.getItemMeta();
//		mbow.setDisplayName("§aEmerald Bow");
//		bow.setItemMeta(mbow);
//
//		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
//		sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
//		sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
//		leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		
//
//		bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
//		bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
//		bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
//		bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
//		bow.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		sword1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
//		sword1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		sword1.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
//		sword1.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
//
//		helmet1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		helmet1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		chestplate1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
//		chestplate1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		leggings1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
//		leggings1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		boots1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
//		boots1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//
//		bow1.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
//		bow1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		bow1.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
//		bow1.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
//
//		sword2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
//		sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
//		sword2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
//		sword2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
//		sword2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 4);
//
//		helmet2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
//		helmet2.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
//		helmet2.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
//		helmet2.addUnsafeEnchantment(Enchantment.WATER_WORKER, 2);
//		helmet2.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
//
//		chestplate2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
//		chestplate2.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
//		chestplate2.addUnsafeEnchantment(Enchantment.THORNS, 2);
//		chestplate2.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
//
//		leggings2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
//		leggings2.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
//		leggings2.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
//
//		boots2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
//		boots2.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
//		boots2.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 4);
//		boots2.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
//
//		bow2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
//		bow2.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
//		bow2.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
//		bow2.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
//		bow2.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
//		e.getDrops().remove(weeklyApple());
//		e.getDrops().remove(weeklyBoots());
//		e.getDrops().remove(weeklyChestplate());
//		e.getDrops().remove(weeklyEmerald());
//		e.getDrops().remove(weeklyHelmet());
//		e.getDrops().remove(weeklyLeggings());
//		e.getDrops().remove(weeklySword());
//		e.getDrops().remove(sword1);
//		e.getDrops().remove(sword2);
//		e.getDrops().remove(helmet1);
//		e.getDrops().remove(helmet2);
//		e.getDrops().remove(chestplate1);
//		e.getDrops().remove(chestplate2);
//		e.getDrops().remove(leggings1);
//		e.getDrops().remove(leggings2);
//		e.getDrops().remove(boots1);
//		e.getDrops().remove(boots2);
//		e.getDrops().remove(bow1);
//		e.getDrops().remove(bow2);
//		e.getDrops().remove(helmet);
//		e.getDrops().remove(chestplate);
//		e.getDrops().remove(leggings);
//		e.getDrops().remove(boots);
//		e.getDrops().remove(bow);
//		if (e.getEntity().hasPermission("H.Emerald")) {
//			for (int i = 0; i < 36; i++) {
//				e.getDrops().remove(sword1);
//				e.getDrops().remove(sword2);
//				e.getDrops().remove(helmet1);
//				e.getDrops().remove(helmet2);
//				e.getDrops().remove(chestplate1);
//				e.getDrops().remove(chestplate2);
//				e.getDrops().remove(leggings1);
//				e.getDrops().remove(leggings2);
//				e.getDrops().remove(boots1);
//				e.getDrops().remove(boots2);
//				e.getDrops().remove(bow1);
//				e.getDrops().remove(bow2);
//				e.getDrops().remove(sword);
//				e.getDrops().remove(helmet);
//				e.getDrops().remove(chestplate);
//				e.getDrops().remove(leggings);
//				e.getDrops().remove(boots);
//				e.getDrops().remove(bow);
//			}
//	}
//	}

}
