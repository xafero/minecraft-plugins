package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

import java.util.List;

public class PetNameCommand extends AbstractPetCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = getPlayer(sender);
        if (player == null) {
            return false;
        }
        World world = player.getWorld();
        Location loc = player.getLocation();
        int dist = getDist(null);
        List<Entity> res = findNearby(world, loc, dist);
        for (Entity entity : res) {
            if (!(entity instanceof Tameable))
                continue;
            Tameable animal = (Tameable) entity;
            String origName = getNameOf(animal);
            String name = args.length == 0 ? "T" + animal.getUniqueId().timestamp() : args[0];
            animal.setCustomName(name);
            if (animal.isTamed()) {
                player.sendMessage(String.format("Renamed '%s' to '%s'!", origName, name));
                continue;
            }
            animal.setOwner(player);
            player.sendMessage(String.format("Tamed %s as '%s' for you!", origName, name));
            break;
        }
        return true;
    }
}
