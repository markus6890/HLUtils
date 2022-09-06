package com.gmail.markushygedombrowski;

import com.gmail.markushygedombrowski.commands.GamemodeCommand;
import com.gmail.markushygedombrowski.utils.ConfigManager;
import com.gmail.markushygedombrowski.utils.Configreloadcommand;
import com.gmail.markushygedombrowski.warp.WarpCommand;
import com.gmail.markushygedombrowski.warp.WarpManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HLUtils extends JavaPlugin {
    public Economy econ = null;
    private ConfigManager configM;
    private WarpManager warpManager;

    public void onEnable() {
        loadConfigManager();
        saveDefaultConfig();
        FileConfiguration config = getConfig();


        GamemodeCommand gamemodeCommand = new GamemodeCommand();
        getCommand("hlgamemode").setExecutor(gamemodeCommand);
        Configreloadcommand configreloadcommand = new Configreloadcommand(this);
        getCommand("hlutilsreload").setExecutor(configreloadcommand);

        initWarps();
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private void initWarps() {
        warpManager = new WarpManager(this,configM);
        warpManager.load();
        WarpCommand warpCommand = new WarpCommand(warpManager, this);
        getCommand("hlutilwarp").setExecutor(warpCommand);
    }

    public void reload() {
        reloadConfig();
        FileConfiguration config = getConfig();
        warpManager.load();


    }

    public void loadConfigManager() {
        configM = new ConfigManager();
        configM.setup();
        configM.saveWarps();
        configM.reloadWarps();
    }


    public void onDisable() {

    }
}
