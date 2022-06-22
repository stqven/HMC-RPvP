package me.invis.hibe.dailysystem.api;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsAPI {
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
		ItemStack g = new ItemStack(Material.GOLDEN_APPLE, 2, (short)1);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eDaily Apple");
		g.setItemMeta(gm);
		return g;
	}
	public static ItemStack dailyRedstone() {
		ItemStack g = new ItemStack(Material.REDSTONE_BLOCK, 32);
		return g;
	}
	//
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
		ItemStack g = new ItemStack(Material.GOLDEN_APPLE, 10, (short)1);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName("§eWeekly Apple");
		g.setItemMeta(gm);
		return g;
	}
	public static ItemStack weeklyEmerald() {
		ItemStack g = new ItemStack(Material.EMERALD_BLOCK, 10);
		return g;
	}
}
