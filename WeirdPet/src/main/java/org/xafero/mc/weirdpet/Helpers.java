package org.xafero.mc.weirdpet;

import org.bukkit.Location;

public class Helpers {

    public static String toString(Location loc) {
        return String.format("(%s|%s|%s)", loc.getX(), loc.getY(), loc.getZ());
    }
}
