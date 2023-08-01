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


    public FileConfiguration itemcfg;
    public File itemFile;


    public void setup() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();

        }

        itemFile = new File(plugin.getDataFolder(),"blocked-items.yml");

        if(!itemFile.exists()) {
            try {
                itemFile.createNewFile();
            }catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not create blocked-items.yml File");
            }
        }


        itemcfg = YamlConfiguration.loadConfiguration(itemFile);


    }




    public FileConfiguration getItems() {
        return itemcfg;
    }


    public void saveItems() {
        try {
            itemcfg.save(itemFile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "could not create blocked-items.yml File");
        }
    }



    public  void reloadItems() {
        itemcfg = YamlConfiguration.loadConfiguration(itemFile);
    }

}
