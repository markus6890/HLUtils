package com.gmail.markushygedombrowski;

import com.gmail.markushygedombrowski.utils.ConfigManager;
import com.gmail.markushygedombrowski.utils.Configreloadcommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HLUtils extends JavaPlugin {
    public Economy econ = null;
    private ConfigManager configM;

    public void onEnable() {
        loadConfigManager();
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        Configreloadcommand configreloadcommand = new Configreloadcommand(this);
        getCommand("hlreload").setExecutor(configreloadcommand);

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

    public void reload() {
        reloadConfig();
        FileConfiguration config = getConfig();

    }

    public void loadConfigManager() {
        configM = new ConfigManager();
        configM.setup();
    }


    public void onDisable() {

    }
}
