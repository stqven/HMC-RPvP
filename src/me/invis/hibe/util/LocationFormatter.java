package me.invis.hibe.util;

import org.bukkit.Location;

public final class LocationFormatter {
  private LocationFormatter() {
    throw new UnsupportedOperationException("Cannot instantiate this class");
  }
  
  public static String formatLocation(Location loc) {
    return loc.getWorld() + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ();
  }
  
//  public static Location parseLocation(String loc) {
//    String[] coords = loc.split(", ");
//    if (coords.length != 4) {
//      throw new IllegalArgumentException("The string is not correctly formatted as a Location");
//    }
//    World world = Bukkit.getWorld(coords[0]);
//    if (world == null) {
//      throw new IllegalArgumentException("The world does not exist");
//    } try {
//      int x = Integer.parseInt(coords[1]);
//      int y = Integer.parseInt(coords[2]);
//      int z = Integer.parseInt(coords[3]);
//    } catch (NumberFormatException e) {
//      int z;
//      throw new IllegalArgumentException("Coordinates cannot be parsed");
//    }
//    int z = 1;
//    int y = 1;
//    int x = 1;
//    return new Location(world, x, y, z);
//  }
}