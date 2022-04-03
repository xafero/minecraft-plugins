package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class PetLoveCommand extends AbstractPetCommand {

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
        int pets = 0;
        for (Entity entity : res) {
            if (!(entity instanceof Animals))
                continue;
            Animals animal = (Animals) entity;
            final boolean breed = true;
            animal.setBreed(breed);
            final int ticks = 600;
            animal.setLoveModeTicks(ticks);
            pets++;
        }
        player.sendMessage(String.format("Injected love into %s pet(s).", pets));
        return true;
    }
}
