package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractPetCommand implements CommandExecutor {

    protected Player getPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return (Player) sender;
        }
        return null;
    }

    protected List<Entity> findNearby(World world, Location loc, int dist) {
        int bx = dist;
        int by = dist;
        int bz = dist;
        return world.getNearbyEntities(loc, bx, by, bz, e -> e instanceof Mob).stream()
                .sorted(Comparator.comparingDouble(e -> loc.distance(e.getLocation())))
                .collect(Collectors.toList());
    }

    protected int getDist(String[] args) {
        return (args == null || args.length == 0) ? 30 : Integer.parseInt(args[0]);
    }

    protected String getNameOf(Mob animal) {
        String an = animal.getCustomName();
        if (an == null || an.isEmpty()) {
            return animal.getName();
        }
        return an;
    }
}
