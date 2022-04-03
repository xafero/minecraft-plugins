package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.util.List;

public class PetFindCommand extends AbstractPetCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = getPlayer(sender);
        if (player == null) {
            return false;
        }
        World world = player.getWorld();
        Location loc = player.getLocation();
        int dist = getDist(args);
        List<Entity> res = findNearby(world, loc, dist);
        int max = 9;
        int idx = 0;
        StringBuilder bld = new StringBuilder();
        for (Entity entity : res) {
            if (!(entity instanceof Mob))
                continue;
            Mob animal = (Mob) entity;
            idx++;
            Location al = animal.getLocation();
            String an = getNameOf(animal);
            double adist = loc.distance(al);
            int days = animal instanceof Ageable ? ((Ageable) animal).getAge() : 0;
            double health = animal.getHealth();
            bld.append(String.format(" #%s [%s] %.3f m, %s d, %.3f pt %n", idx, an, adist, days, health));
            if (idx >= max)
                break;
        }
        if (bld.length() >= 1) {
            bld.insert(0, String.format("Found following animals nearby:%n"));
            player.sendMessage(bld.toString());
        }
        return true;
    }
}
