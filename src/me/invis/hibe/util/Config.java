package me.invis.hibe.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.invis.hibe.RedstonePVP;

public abstract class Config
{
  protected FileConfiguration config;
  protected File path;
  private Logger log;
  
  protected Config(String name)
  {
    this.log = RedstonePVP.getInstance().getLogger();
    
    this.path = new File(RedstonePVP.getInstance().getDataFolder(), name);
    try
    {
      this.config = YamlConfiguration.loadConfiguration(this.path);
    }
    catch (IllegalArgumentException e)
    {
      createNewConfig();
    }
    if (this.config.getKeys(true).size() == 0) {
      createNewConfig();
    }
  }
  
  public void createNewConfig()
  {
    this.config = new YamlConfiguration();
    restoreDefaults();
  }
  
  public final void saveConfig()
  {
    try
    {
      this.config.save(this.path);
    }
    catch (IOException e)
    {
      this.log.warning("Config couldn't be saved: " + this.path.getPath());
    }
  }
  
  public abstract void restoreDefaults();
}
