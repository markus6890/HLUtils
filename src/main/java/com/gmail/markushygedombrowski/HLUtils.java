package com.gmail.markushygedombrowski;

import com.gmail.markushygedombrowski.commands.*;
import com.gmail.markushygedombrowski.itemblocking.ItemBlockCommand;
import com.gmail.markushygedombrowski.itemblocking.ItemManager;

import com.gmail.markushygedombrowski.listener.Listener;
import com.gmail.markushygedombrowski.listener.CraftListener;
import com.gmail.markushygedombrowski.rankupsigns.DeRank;
import com.gmail.markushygedombrowski.rankupsigns.Rankup;
import com.gmail.markushygedombrowski.rankupsigns.RankupSigns;
import com.gmail.markushygedombrowski.utils.ConfigManagerUtils;
import com.gmail.markushygedombrowski.utils.Settings;
import com.gmail.markushygedombrowski.utils.Utils;

import com.gmail.markushygedombrowski.warp.WarpCommand;
import com.gmail.markushygedombrowski.warp.WarpManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HLUtils extends JavaPlugin {
    public Economy econ = null;

    private ConfigManagerUtils configManagerUtils;
    private WarpManager warpManager;
    private Settings settings;
    private ItemManager itemManager;
    private Utils utils;


    public void onEnable() {
        saveDefaultConfig();

        loadConfigManager();
        FileConfiguration config = getConfig();
        settings = new Settings();
        utils = new Utils();

        settings.load(config);


        initWarps();
        initItems();
        initCommands();
        Listener breakBlockListener = new Listener();
        Bukkit.getPluginManager().registerEvents(breakBlockListener, this);
        CraftListener craftListener = new CraftListener(itemManager);
        Bukkit.getPluginManager().registerEvents(craftListener, this);

        Rankup rankup = new Rankup(settings, this, warpManager);
        Bukkit.getPluginManager().registerEvents(rankup, this);

        DeRank deRank = new DeRank(this, warpManager);
        Bukkit.getPluginManager().registerEvents(deRank, this);
        RankupSigns rankupSigns = new RankupSigns(rankup, deRank);
        Bukkit.getPluginManager().registerEvents(rankupSigns, this);





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
        warpManager = new WarpManager(this, configManagerUtils);
        warpManager.load();
        WarpCommand warpCommand = new WarpCommand(warpManager, this);
        getCommand("hlwarp").setExecutor(warpCommand);
    }

    public void reload() {
        reloadConfig();
        FileConfiguration config = getConfig();
        loadConfigManager();
        warpManager.load();
        itemManager.load();
        settings.load(config);

    }

    public void loadConfigManager() {
        configManagerUtils = new ConfigManagerUtils();
        configManagerUtils.setup();
        configManagerUtils.saveWarps();
        configManagerUtils.saveItems();
        configManagerUtils.reloadItems();
        configManagerUtils.reloadWarps();

    }

    private void initCommands() {
        GamemodeCommand gamemodeCommand = new GamemodeCommand();
        getCommand("hlgamemode").setExecutor(gamemodeCommand);

        ItemInfoCommand itemInfoCommand = new ItemInfoCommand();
        getCommand("iteminfo").setExecutor(itemInfoCommand);

        SendMessageCommand sendMessageCommand = new SendMessageCommand();
        getCommand("sendmessage").setExecutor(sendMessageCommand);

        TpCommands tpCommands = new TpCommands();
        getCommand("hltp").setExecutor(tpCommands);

        StaffChat staffChat = new StaffChat();
        getCommand("st").setExecutor(staffChat);


    }

    public void initItems() {
        itemManager = new ItemManager(configManagerUtils);
        itemManager.load();

        ItemBlockCommand itemBlockCommand = new ItemBlockCommand(itemManager);
        getCommand("blockitem").setExecutor(itemBlockCommand);
    }




    public void onDisable() {
        System.out.println("==================================");
        System.out.println("HLUtils disabled");
        System.out.println("==================================");


    }



}
