package com.gmail.markushygedombrowski.utils;

import com.gmail.markushygedombrowski.HLUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManagerUtils {
    private HLUtils plugin = HLUtils.getPlugin(HLUtils.class);

    public FileConfiguration warpscfg;
    public File warpsFile;
    public FileConfiguration itemcfg;
    public File itemFile;


    public void setup() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();

        }
        warpsFile = new File(plugin.getDataFolder(), "warps.yml");
        itemFile = new File(plugin.getDataFolder(),"blocked-items.yml");

        if(!warpsFile.exists()) {
            try {
                warpsFile.createNewFile();
            }catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not create warps.yml File");
            }
        }
        if(!itemFile.exists()) {
            try {
                itemFile.createNewFile();
            }catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not create blocked-items.yml File");
            }
        }


        itemcfg = YamlConfiguration.loadConfiguration(itemFile);
        warpscfg = YamlConfiguration.loadConfiguration(warpsFile);


    }

    public FileConfiguration getWarps() {
        return warpscfg;
    }

    public FileConfiguration getItems() {
        return itemcfg;
    }

    public void saveWarps() {
        try {
            warpscfg.save(warpsFile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not save warps.yml File");
        }
    }

    public void saveItems() {
        try {
            itemcfg.save(itemFile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not create blocked-items.yml File");
        }
    }



    public void reloadWarps() {
        warpscfg = YamlConfiguration.loadConfiguration(warpsFile);
    }
    public  void reloadItems() {
        itemcfg = YamlConfiguration.loadConfiguration(itemFile);
    }

}
