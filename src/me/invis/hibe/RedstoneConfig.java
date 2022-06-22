package me.invis.hibe;

import me.invis.hibe.util.Config;

public class RedstoneConfig extends Config {
  public RedstoneConfig(String name) {
    super(name);
  }
  
  public void enableFeature(RedstoneFeature feature) {
    this.config.set(feature.getConfigKey(), Boolean.valueOf(true));
    saveConfig();
  }
  
  public void disableFeature(RedstoneFeature feature) {
    this.config.set(feature.getConfigKey(), Boolean.valueOf(false));
    saveConfig();
  }
  
  public boolean isFeatureEnabled(RedstoneFeature feature) {
    return this.config.getBoolean(feature.getConfigKey(), false);
  }
  
  public void restoreDefaults() {
    this.config.options().header("Which features should be enabled? (true or false)");
    RedstoneFeature[] arrayOfRedstoneFeature;
    int j = (arrayOfRedstoneFeature = RedstoneFeature.values()).length;
    for (int i = 0; i < j; i++) {
      RedstoneFeature feature = arrayOfRedstoneFeature[i];
      enableFeature(feature);
    }
  }
}
