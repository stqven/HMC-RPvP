package me.invis.hibe.bloodspray;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class ItemBleed implements Listener {
	
	@EventHandler
	public void onDeath(EntityDamageByEntityEvent e) {
		Entity p = (Player) e.getEntity();
		Entity t = (Player) e.getDamager();
		int random = getRandom(4, 11);
		int hun = getRandom(1, 150);
		int hun2 = getRandom(1, 150);
		ItemStack gold = new ItemStack(Material.GOLD_INGOT, random);
		ItemStack goldaxe = new ItemStack(Material.GOLD_AXE, 1);
		goldaxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE, 1);
		goldaxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		if (p instanceof Player) {
			if (t instanceof Player) {
		        if ((e.getDamage() <= 0.0D) || (e.isCancelled())) {
		            return;
		          }
		        
		        if (e.getDamage() < 0.0D || e.isCancelled()) {
		                return;
		              }
		        
				p.getWorld().dropItem(p.getLocation(), gold);
				if (hun == 24) {
					p.getWorld().dropItem(p.getLocation(), goldaxe);
				} else if (hun2 == 47) {
					p.getWorld().dropItem(p.getLocation(), diamondaxe);
				}
			}
		}
		
		if (e.getDamage() >= 0.2) {
			p.getWorld().dropItemNaturally(p.getLocation(), gold);
			if (hun == 24) {
				p.getWorld().dropItemNaturally(p.getLocation(), goldaxe);
			} else if (hun2 == 47) {
				p.getWorld().dropItemNaturally(p.getLocation(), diamondaxe);
			}
		}
	}
	
	  public int getRandom(int lower, int upper) {
	        Random random = new Random();
	        return random.nextInt((upper - lower) + 1) + lower;
	    }

}
