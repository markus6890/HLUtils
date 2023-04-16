package com.gmail.markushygedombrowski.itemblocking;

import com.gmail.markushygedombrowski.utils.ConfigManagerUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private Map<String ,ItemInfo> itemList = new HashMap<>();
    private ConfigManagerUtils configM;



    public ItemManager(ConfigManagerUtils configM) {
        this.configM = configM;
    }

    public void load() {
        FileConfiguration config = configM.getItems();
        itemList.clear();
        for (String key : config.getConfigurationSection("items").getKeys(false)) {
            String item = config.getString("items." + key + ".name");
            Material material = Material.getMaterial(item);
            ItemInfo itemInfo = new ItemInfo(material);
            itemList.put(item,itemInfo);
        }
    }

    public void save(ItemInfo itemInfo) {
        FileConfiguration config = configM.getItems();
        Material material = itemInfo.getMaterial();
        String item = material.name();
        String key = "" + (itemList.size() + 1);
        config.set("items." + key + ".name", item);

        configM.saveItems();
        itemList.put(itemInfo.materialName(),itemInfo);
    }
    public Map<String, ItemInfo> getItemList() {
        return itemList;
    }
    public ItemInfo getItemInfo(String name) {
        return itemList.get(name);
    }


}
