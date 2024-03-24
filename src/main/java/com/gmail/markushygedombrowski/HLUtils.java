package com.gmail.markushygedombrowski;

import com.gmail.markushygedombrowski.cobweb.CobWeb;
import com.gmail.markushygedombrowski.commands.*;
import com.gmail.markushygedombrowski.itemblocking.ItemBlockCommand;
import com.gmail.markushygedombrowski.itemblocking.ItemManager;

import com.gmail.markushygedombrowski.listener.Listener;
import com.gmail.markushygedombrowski.listener.CraftListener;
import com.gmail.markushygedombrowski.listener.OnJoinListener;
import com.gmail.markushygedombrowski.listener.Testlistener;
import com.gmail.markushygedombrowski.rankupsigns.DeRank;
import com.gmail.markushygedombrowski.rankupsigns.Rankup;
import com.gmail.markushygedombrowski.rankupsigns.RankupSigns;
import com.gmail.markushygedombrowski.utils.*;

import com.gmail.markushygedombrowski.warp.WarpManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HLUtils extends JavaPlugin {
    public Economy econ = null;

    private ConfigManagerUtils configManagerUtils;

    private Settings settings;
    private ItemManager itemManager;
    private Utils utils;
    private HLWarp hlWarp;
    private ListHolder listHolder;
    private PlayerOnTime playerOnTime;
    private CobWeb cobWeb;


    public void onEnable() {
        saveDefaultConfig();
        hlWarp = HLWarp.getInstance();
        WarpManager warpManager = hlWarp.getWarpManager();


        loadConfigManager();

        FileConfiguration config = getConfig();
        settings = new Settings();
        utils = new Utils();
        listHolder = new ListHolder();
        settings.load(config);
        cobWeb = new CobWeb();
        playerOnTime = new PlayerOnTime(settings, this);
        initItems();
        initCommands();
        initListener(warpManager);



        Rankup rankup = new Rankup(settings, this, warpManager);
        Bukkit.getPluginManager().registerEvents(rankup, this);

        DeRank deRank = new DeRank(this, warpManager);
        Bukkit.getPluginManager().registerEvents(deRank, this);
        RankupSigns rankupSigns = new RankupSigns(rankup, deRank);
        Bukkit.getPluginManager().registerEvents(rankupSigns, this);
        EnderchestCommand enderchestCommand = new EnderchestCommand();
        getCommand("ecd").setExecutor(enderchestCommand);
        System.out.println("==================================");
        System.out.println("HLUtils enabled");
        System.out.println("==================================");

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                playerOnTime.cooldown();
                cobWeb.cobwebCooldown();
            }
        }, 20L, 20L);
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    private void initListener(WarpManager warpManager) {
        Listener breakBlockListener = new Listener(cobWeb);
        Bukkit.getPluginManager().registerEvents(breakBlockListener, this);
        CraftListener craftListener = new CraftListener(itemManager);
        Bukkit.getPluginManager().registerEvents(craftListener, this);
        OnJoinListener onJoinListener = new OnJoinListener(warpManager,listHolder, playerOnTime);
        Bukkit.getPluginManager().registerEvents(onJoinListener, this);
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
        loadConfigManager();
        itemManager.load();
        settings.load(config);

    }

    public void loadConfigManager() {
        configManagerUtils = new ConfigManagerUtils();
        configManagerUtils.setup();
        configManagerUtils.saveItems();
        configManagerUtils.reloadItems();


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

        ListCommand listCommand = new ListCommand(listHolder);
        getCommand("list").setExecutor(listCommand);

        CreateGuiCommand createGuiCommand = new CreateGuiCommand();
        getCommand("creategui").setExecutor(createGuiCommand);


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
