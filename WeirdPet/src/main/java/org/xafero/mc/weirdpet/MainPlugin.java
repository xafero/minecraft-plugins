package org.xafero.mc.weirdpet;

import org.bukkit.plugin.java.JavaPlugin;
import org.xafero.mc.weirdpet.cmd.*;

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
        getCommand("mobcalm").setExecutor(new MobCalmCommand());
        getCommand("petfind").setExecutor(new PetFindCommand());
        getCommand("petheal").setExecutor(new PetHealCommand());
        getCommand("petlove").setExecutor(new PetLoveCommand());
        getCommand("petname").setExecutor(new PetNameCommand());
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
