package org.xafero.mc.weirdpet.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.List;

public class MobCalmCommand extends AbstractPetCommand {

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
        int monsters = 0;
        for (Entity entity : res) {
            if (!(entity instanceof Monster))
                continue;
            Monster monster = (Monster) entity;
            final boolean aware = false;
            monster.setAware(aware);
            final double amount = monster.getHealth() - 1;
            monster.damage(amount);
            monsters++;
        }
        player.sendMessage(String.format("Calmed down %s monster(s).", monsters));
        return true;
    }
}
