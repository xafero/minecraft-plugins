package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class PetHealCommand extends AbstractPetCommand {

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
            AttributeInstance mh = animal.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            double maxHealth = mh.getValue();
            animal.setHealth(maxHealth);
            pets++;
        }
        player.sendMessage(String.format("Healed up %s pet(s).", pets));
        return true;
    }
}
