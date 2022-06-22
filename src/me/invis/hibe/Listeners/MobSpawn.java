package me.invis.hibe.Listeners;
 
import java.util.ArrayList;
import java.util.Random;
 
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.invis.hibe.RedstonePVP;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
 
public class MobSpawn implements Listener {
 
    private RedstonePVP plugin;
    public MobSpawn(RedstonePVP main) {
        this.plugin = main;
    }
    public boolean mobspawn;
    private Random rand;
    private int zx ,zy ,zz ,mx ,my ,mz ,sx ,sy ,sz ,bx ,by ,bz ,wx ,wy ,wz;
    EntitySkeleton entitySkeleton;
   
   
   
   
    public void summonMobs() {
        mobspawn = true;
        zx =    plugin.getConfig().getInt("Mobs.Zombie.X");
        zy =    plugin.getConfig().getInt("Mobs.Zombie.Y");
        zz =    plugin.getConfig().getInt("Mobs.Zombie.Z");
        mx =    plugin.getConfig().getInt("Mobs.PigZombie.X");
        my =    plugin.getConfig().getInt("Mobs.PigZombie.Y");
        mz =    plugin.getConfig().getInt("Mobs.PigZombie.Z");
        sx =    plugin.getConfig().getInt("Mobs.Skeleton.X");
        sy =    plugin.getConfig().getInt("Mobs.Skeleton.Y");
        sz =    plugin.getConfig().getInt("Mobs.Skeleton.Z");
        bx =    plugin.getConfig().getInt("Mobs.Blaze.X");
        by =    plugin.getConfig().getInt("Mobs.Blaze.Y");
        bz =    plugin.getConfig().getInt("Mobs.Blaze.Z");
        wx =    plugin.getConfig().getInt("Mobs.Witch.X");
        wy =    plugin.getConfig().getInt("Mobs.Witch.Y");
        wz =    plugin.getConfig().getInt("Mobs.Witch.Z");
        Location zLocation = new Location(Bukkit.getWorld("world"), zx, zy, zz);
        Location mLocation = new Location(Bukkit.getWorld("world"), mx, my, mz);
        Location sLocation = new Location(Bukkit.getWorld("world"), sx, sy, sz);
        Location bLocation = new Location(Bukkit.getWorld("world"), bx, by, bz);
        Location wLocation = new Location(Bukkit.getWorld("world"), wx, wy, wz);
        if(mobspawn == true) {
            spawnBlaze(bLocation);
            spawnZombie(zLocation);
            spawnSkeleton(sLocation);
            spawnWitch(wLocation);
            spawnPigZombie(mLocation);
            } else {
                stopSummonMobs();
                mobspawn = false;
            }
        }  
       
 
       
   
    public void stopSummonMobs() {
        mobspawn = false;
        if(mobspawn == false) {
            for(Entity e : Bukkit.getWorld("world").getEntities()) {
                if(!(e instanceof Player)) {
                    if(e instanceof Zombie || e instanceof Skeleton || e instanceof PigZombie || e instanceof Blaze || e instanceof Witch) {
                        Vector vec = new Vector(0,-60,0);
                        Location loc = vec.toLocation(Bukkit.getWorld("world"));
                        e.teleport(loc);
                       
                    }
                }
            }
        }
    }
 
   
   
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        rand = new Random();
        Entity damager = e.getDamager();
        Entity p = e.getEntity();
        if(p instanceof LivingEntity) {
            if(p instanceof Player) {
                if(damager instanceof Projectile) {
                    if(damager.hasMetadata("snowball")) {
                        p.getLocation().setYaw(rand.nextInt(180));
                        p.getLocation().setPitch(rand.nextInt(180));
                        ((LivingEntity) p).damage(((LivingEntity) p).getHealth() / 2);
               
                    }
                } else if(damager instanceof Projectile) {
                    if(damager.hasMetadata("fireball")) {
                        p.getLocation().getWorld().createExplosion(p.getLocation(), 5f);
                        p.getLocation().getWorld().createExplosion(p.getLocation(), 5f);
                        ((LivingEntity) p).damage(((LivingEntity) p).getHealth() / 3);
                    }
                } else if(damager instanceof Skeleton) {
                    if(damager.hasMetadata("skeleton")) {
                        ((LivingEntity) p).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 5));
                        ((LivingEntity) p).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 50, 4));
                    }
                } else if(damager instanceof Zombie) {
                    if(damager.hasMetadata("zombie")) {
                        ((LivingEntity) p).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 4));
                    }
                }
            } else if(p instanceof Zombie || p instanceof PigZombie || p instanceof Skeleton || p instanceof Blaze || p instanceof Witch) {
                if(damager instanceof Projectile) {
                    if(damager.hasMetadata("snowball") || damager.hasMetadata("fireball") || damager.hasMetadata("arrow")) {
                        e.setCancelled(true);
                    }
                }
            }
        }
        if(mobspawn == false) {
            for(Entity entity : e.getEntity().getWorld().getEntities()) {
            	if(entity.hasMetadata("witch") || entity.hasMetadata("blaze") || entity.hasMetadata("skeleton") || entity.hasMetadata("zombie") || entity.hasMetadata("pigZombie")) {
                    e.setDamage(10000);
                }
            }
        }
    }
   
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        Player killer = entity.getKiller();
        if(entity instanceof Zombie) {
            if(entity.hasMetadata("zombie")) {
                killer.getInventory().addItem(new ItemStack(Material.REDSTONE,15));
                e.getDrops().clear();
            }
        } else if (entity instanceof PigZombie) {
            if(entity.hasMetadata("pigZombie")) {
                killer.getInventory().addItem(new ItemStack(Material.REDSTONE,30));
                e.getDrops().clear();
               
            }
        } else if(entity instanceof Skeleton) {
            if(entity.hasMetadata("skeleton")) {
                killer.getInventory().addItem(new ItemStack(Material.REDSTONE,64));
                e.getDrops().clear();
            }
        } else if(entity instanceof Blaze) {
            if(entity.hasMetadata("blaze")) {
                killer.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK,2));
                e.getDrops().clear();
            }
        } else if(entity instanceof Witch) {
            if(entity.hasMetadata("witch")) {
                killer.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK,2));
                e.getDrops().clear();
            }
        }
       
    }
   
    private void spawnZombie(Location loc) {
    	int cc = 0;
    	for (Entity en : Bukkit.getWorld("world").getEntities()) {
    		if (en.getType() == EntityType.ZOMBIE) {
    			cc++;
    		}
    	}
    	if (cc >= 15) {
    		return;
    	}
        if(mobspawn) {
            new BukkitRunnable() {
           
            @Override
            public void run() {
                if(!mobspawn) {
                    this.cancel();
                } else {
                Zombie zombie = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                ItemStack sword = new ItemStack(Material.STONE_SWORD);
                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
                zombie.getEquipment().setBoots(boot);
                zombie.getEquipment().setHelmet(helmet);
                zombie.getEquipment().setItemInHand(sword);
                zombie.getEquipment().setItemInHandDropChance(0);
                zombie.getEquipment().setHelmetDropChance(0);
                zombie.getEquipment().setBootsDropChance(0);
                zombie.setMetadata("zombie", new FixedMetadataValue(plugin, "zombie"));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    for(Entity e : zombie.getNearbyEntities(25, 25, 25)) {
                        if(e instanceof Player) {
                            if(p == e) {
                                zombie.setTarget(p);
                            }
                        }
                    }
                }
                }
            }
   
            }.runTaskTimer(plugin, 20L, 100L);
        }
    }
   
    private void spawnPigZombie(Location loc) {
        if(mobspawn == true) { 
            new BukkitRunnable() {
   
                @Override
                public void run() {
                    if(mobspawn == false) {
                        this.cancel();
                    } else {
                    PigZombie pigZombie =  (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
 
                    rand = new Random();
                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
                    ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                    ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                    sword.addEnchantment(Enchantment.KNOCKBACK, 2);
                    pigZombie.getEquipment().setBoots(boots);
                    pigZombie.getEquipment().setLeggings(leggings);
                    pigZombie.getEquipment().setChestplate(chestplate);
                    pigZombie.getEquipment().setHelmet(helmet);
                    pigZombie.getEquipment().setItemInHand(sword);
                    pigZombie.getEquipment().setBootsDropChance(0);
                    pigZombie.getEquipment().setLeggingsDropChance(0);
                    pigZombie.getEquipment().setChestplateDropChance(0);
                    pigZombie.getEquipment().setHelmetDropChance(0);
                    pigZombie.getEquipment().setItemInHandDropChance(0);
                    pigZombie.setMaxHealth(100);
                    pigZombie.setHealth(100);
                    pigZombie.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000, 6));
                    pigZombie.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10000, 25));
                    pigZombie.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000, 2));
                    pigZombie.setAngry(true);
                    pigZombie.setMetadata("pigZombie", new FixedMetadataValue(plugin, "pigZombie"));
                   
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                       
                        @Override
                        public void run() {
                            if(!pigZombie.isDead()) {
                                PigAbility(pigZombie);
                            }
                           
                        }
                    }, 1L, (long)9 * 20L);
                   
                    }
                }
               
            }.runTaskTimer(plugin, 5*20L, 200L);
        }
    }
   
    private void spawnSkeleton(Location loc) {
    	int cc = 0;
    	for (Entity en : Bukkit.getWorld("world").getEntities()) {
    		if (en.getType() == EntityType.SKELETON) {
    			cc++;
    		}
    	}
    	if (cc >= 10) {
    		return;
    	}
        if(mobspawn == true) {
            new BukkitRunnable() {
               
                @Override
                public void run() {
                    if(mobspawn == false) {
                        this.cancel();
                    } else {
                    Skeleton skeleton = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                    ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                    ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
                    ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                    sword.addEnchantment(Enchantment.KNOCKBACK, 2);
                    skeleton.getEquipment().setBoots(boots);
                    skeleton.getEquipment().setLeggings(leggings);
                    skeleton.getEquipment().setChestplate(chestplate);
                    skeleton.getEquipment().setHelmet(helmet);
                    skeleton.getEquipment().setItemInHand(sword);
                    skeleton.getEquipment().setBootsDropChance(0);
                    skeleton.getEquipment().setLeggingsDropChance(0);
                    skeleton.getEquipment().setChestplateDropChance(0);
                    skeleton.getEquipment().setHelmetDropChance(0);
                    skeleton.getEquipment().setItemInHandDropChance(0);
                    skeleton.setMetadata("skeleton", new FixedMetadataValue(plugin, "skeleton"));
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        for(Entity e : skeleton.getNearbyEntities(15, 15, 15)) {
                            if(e instanceof Player) {
                                if(p == e) {
                                    skeleton.setTarget(p);
                                }
                            }
                        }
                    }
 
                    }
                }
            }.runTaskTimer(plugin, 5*20L, 300L);
        }
       
    }
    private void spawnBlaze(Location loc) {
        if(mobspawn == true) { 
            new BukkitRunnable() {
   
                @Override
                public void run() {
                    if(mobspawn == false) {
                        this.cancel();
                    } else {
                    Blaze blaze = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    blaze.setMaxHealth(100);
                    blaze.setHealth(100);
                    blaze.setMetadata("blaze", new FixedMetadataValue(plugin, "blaze"));
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        for(Entity e : blaze.getNearbyEntities(10, 10, 10)) {
                            if(e instanceof Player) {
                                if(p == e) {
                                    blaze.setTarget(p);
                                }
                            }
                        }
                    }
                               
                           
                       
                   
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                       
                        @Override
                        public void run() {
                            if(!blaze.isDead()) {
                                BlazeAbility(blaze);
                            }
                           
                        }
                    }, 1L, (long)3 * 20L);
                    }
                }
               
            }.runTaskTimer(plugin, 20L, 400L);
        }
    }
    private void spawnWither(Location loc,Witch witch) {
    	int cc = 0;
    	for (Entity en : Bukkit.getWorld("world").getEntities()) {
    		if (en.getType() == EntityType.WITHER) {
    			cc++;
    		}
    	}
    	if (cc >= 5) {
    		return;
    	}
        if(mobspawn == true) { 
            if(witch.hasMetadata("witch")) {
                Skeleton skeleton = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
                skeleton.setSkeletonType(SkeletonType.WITHER);
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 4);
                sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                sword.addEnchantment(Enchantment.KNOCKBACK, 2);
                skeleton.getEquipment().setBoots(boots);
                skeleton.getEquipment().setLeggings(leggings);
                skeleton.getEquipment().setChestplate(chestplate);
                skeleton.getEquipment().setHelmet(helmet);
                skeleton.getEquipment().setItemInHand(sword);
                skeleton.getEquipment().setBootsDropChance(0);
                skeleton.getEquipment().setLeggingsDropChance(0);
                skeleton.getEquipment().setChestplateDropChance(0);
                skeleton.getEquipment().setHelmetDropChance(0);
                skeleton.getEquipment().setItemInHandDropChance(0);
                for(Player p : Bukkit.getOnlinePlayers()) {
                    for(Entity e : skeleton.getNearbyEntities(25, 25, 25)) {
                        if(e instanceof Player) {
                            if(p == e) {
                                skeleton.setTarget(p);
                            }
                        }
                    }
                }
            }  
        }
    }
       
   
   
    private void spawnWitch(Location loc) {
    	int cc = 0;
    	for (Entity en : Bukkit.getWorld("world").getEntities()) {
    		if (en.getType() == EntityType.WITCH) {
    			cc++;
    		}
    	}
    	if (cc >= 5) {
    		return;
    	}
        if(mobspawn == true) { 
            new BukkitRunnable() {
   
                @Override
                public void run() {
                    if(mobspawn == false) {
                        this.cancel();
                    } else {
                    Witch witch = (Witch) loc.getWorld().spawnEntity(loc, EntityType.WITCH);
                    witch.setMaxHealth(300);
                    witch.setHealth(300);
                    witch.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000, 5));
                    witch.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 7));
                    witch.setMetadata("witch", new FixedMetadataValue(plugin, "witch"));
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        for(Entity e : witch.getNearbyEntities(20, 20, 20)) {
                            if(e instanceof Player) {
                                if(p == e) {
                                    witch.setTarget(p);
                                }
                            }
                        }
                    }  
                   
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                       
                        @Override
                        public void run() {
                            if(!witch.isDead()) {
                                for(Player p : Bukkit.getOnlinePlayers()) {
                                    for(Entity e : witch.getNearbyEntities(25, 25, 25)) {
                                        if(e instanceof Player) {
                                            if(p == e) {
                                                WitchAbility(witch, p);
                                            }
                                        }
                                   
                                }
                            }
                           
                        }
                       
                    }
                    }, 1L, (long)120 * 20L);
                   
                    new BukkitRunnable() {
   
                        @Override
                        public void run() {
                            if(!witch.isDead()) {
                                spawnWither(loc, witch);
                            }
                           
                        }
                       
                    }.runTaskTimer(plugin, 40L, 40L);
                   
                }
                }
            }.runTaskTimer(plugin, 20, (20*60)*30);
        }
           
    }
   
    private void WitchAbility(Witch witch, Player p) {
        if(witch.hasMetadata("witch")) {
            Location loc = new Location(witch.getWorld(), witch.getLocation().getDirection().getX(), witch.getLocation().getDirection().getY() + 1, witch.getLocation().getDirection().getZ());
            Arrow arrow = (Arrow) witch.getLocation().getWorld().spawnEntity(loc, EntityType.ARROW);
            arrow.setShooter(witch);
            arrow.setVelocity(arrow.getVelocity().multiply(2));
            arrow.setMetadata("arrow", new FixedMetadataValue(plugin, "arrow"));
        }
    }
   
    private void BlazeAbility(Blaze blaze) {
        if(blaze.hasMetadata("blaze")) {
            if(!blaze.isDead()) {
                Location loc = new Location(blaze.getWorld(), blaze.getLocation().getDirection().getX(), blaze.getLocation().getDirection().getY() + 1, blaze.getLocation().getDirection().getZ());
                Snowball snowball = (Snowball) blaze.getLocation().getWorld().spawnEntity(loc, EntityType.SNOWBALL);
                snowball.setShooter(blaze);
                snowball.setVelocity(snowball.getVelocity().multiply(2));
                snowball.setMetadata("snowball", new FixedMetadataValue(plugin, "snowball"));
                }
            }
    }
   
    @EventHandler
    private void PigAbility(PigZombie pigZombie) {
       
            if(pigZombie.hasMetadata("pigZombie")) {
                if(!pigZombie.isDead()) {
                    Location loc = new Location(pigZombie.getWorld(), pigZombie.getLocation().getDirection().getX(), pigZombie.getLocation().getDirection().getY() + 1, pigZombie.getLocation().getDirection().getZ());
                    Fireball fireball = (Fireball) pigZombie.getLocation().getWorld().spawnEntity(loc, EntityType.FIREBALL);
                    fireball.setShooter(pigZombie);
                    fireball.setVelocity(fireball.getVelocity().multiply(2));
                    fireball.setMetadata("fireball", new FixedMetadataValue(plugin, "fireball"));
                    Vector velo = new Vector(pigZombie.getVelocity().getX() + rand.nextDouble() , pigZombie.getVelocity().getY() + rand.nextDouble(),pigZombie.getVelocity().getZ() + rand.nextDouble());
                    pigZombie.setVelocity(velo.multiply(2));
                }
        }
    }
}