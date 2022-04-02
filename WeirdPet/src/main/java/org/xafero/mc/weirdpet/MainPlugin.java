package org.xafero.mc.weirdpet;

import org.bukkit.plugin.java.JavaPlugin;
import org.xafero.mc.weirdpet.cmd.PetCommand;

import java.util.logging.Logger;

public class MainPlugin extends JavaPlugin {
    public static MainPlugin myPlugin = null;

    @Override
    public void onEnable() {
        super.onEnable();
        Logger log = getLogger();

        log.info("Starting up...");
        myPlugin = this;
        registerCommands();
        log.info("Startup done!");
    }

    private void registerCommands() {
        getCommand("petfind").setExecutor(new PetCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Logger log = getLogger();

        log.info("Shutting down...");
        myPlugin = null;
        log.info("Shutdown done!");
    }
}
