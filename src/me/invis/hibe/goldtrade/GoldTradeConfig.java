package me.invis.hibe.goldtrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import me.invis.hibe.util.Config;
import me.invis.hibe.util.LocationFormatter;

public class GoldTradeConfig
  extends Config
{
  public GoldTradeConfig(String name)
  {
    super(name);
  }
  
  public void restoreDefaults()
  {
    this.config.options().header("Set EXP options and which item should be used for trading");
    
    this.config.set("min-xp", Integer.valueOf(300));
    this.config.set("max-xp", Integer.valueOf(1000));
    this.config.set("item-id", Integer.valueOf(266));
    this.config.set("Signs", new ArrayList<>());
    
    saveConfig();
  }
  
  public int getMinXp()
  {
    return this.config.getInt("min-xp", 100);
  }
  
  public int getMaxXp()
  {
    return this.config.getInt("max-xp", 300);
  }
  
  public int getRandomXp()
  {
    int min = getMinXp();
    int max = getMaxXp();
    Random rnd = new Random();
    
    return rnd.nextInt(max - min) + min;
  }
  
  public int getItemId()
  {
    return this.config.getInt("item-id", 266);
  }
  
  public void storeSign(Sign sign)
  {
    Location loc = sign.getLocation();
    List<String> signs = this.config.getStringList("Signs");
    String format = LocationFormatter.formatLocation(loc);
    for (String signData : signs) {
      if (format.equalsIgnoreCase(signData)) {
        return;
      }
    }
    signs.add(format);
    this.config.set("Signs", signs);
    saveConfig();
  }
  
  public void removeSign(Sign sign)
  {
    Location loc = sign.getLocation();
    String format = LocationFormatter.formatLocation(loc);
    List<String> signs = this.config.getStringList("Signs");
    for (int i = 0; i < signs.size(); i++)
    {
      String signData = (String)signs.get(i);
      if (format.equalsIgnoreCase(signData))
      {
        signs.remove(signData);
        i--;
      }
    }
    this.config.set("Signs", signs);
    saveConfig();
  }
  
  public boolean isTradeSign(Sign sign)
  {
    Location loc = sign.getLocation();
    String format = LocationFormatter.formatLocation(loc);
    List<String> signs = this.config.getStringList("Signs");
    for (String signData : signs) {
      if (format.equalsIgnoreCase(signData)) {
        return true;
      }
    }
    return false;
  }
}
