package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            World world = player.getWorld();

            int dist = args.length == 0 ? 30 : Integer.parseInt(args[0]);
            int bx = dist;
            int by = dist;
            int bz = dist;
            List<Entity> res = world.getNearbyEntities(loc, bx, by, bz, e -> e instanceof Mob).stream()
                    .sorted(Comparator.comparingDouble(e -> loc.distance(e.getLocation())))
                    .collect(Collectors.toList());

            int max = 9;
            int idx = 0;
            StringBuilder bld = new StringBuilder();
            for (Entity entity : res) {
                if (entity instanceof Mob) {
                    Mob animal = (Mob) entity;
                    idx++;
                    Location al = animal.getLocation();
                    String an = animal.getCustomName();
                    if (an == null || an.isEmpty()) {
                        an = animal.getName();
                    }
                    double adist = loc.distance(al);
                    int days = animal instanceof Ageable ? ((Ageable) animal).getAge() : -1;
                    double health = animal.getHealth();
                    bld.append(String.format(" #%s [%s] %.3f m, %s d, %.3f pt %n", idx, an, adist, days, health));

                    if (idx >= max)
                        break;
                }
            }
            if (bld.length() >= 1) {
                bld.insert(0, String.format("Found following animals nearby:%n"));
                player.sendMessage(bld.toString());
            }

            return true;
        }
        return false;
    }
}
