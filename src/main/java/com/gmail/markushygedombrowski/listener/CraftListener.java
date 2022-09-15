package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.itemblocking.ItemInfo;
import com.gmail.markushygedombrowski.itemblocking.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.HashMap;
import java.util.Map;

public class CraftListener implements Listener {
    private ItemManager itemManager;

    public CraftListener(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        Material material = e.getInventory().getResult().getType();
        if (p.hasPermission("admin")) {
            return;
        }
        if (itemManager.getItemInfo(material.name()) != null) {
            p.sendMessage("§cDet kan du ikke Craft");
            e.setCancelled(true);
            e.isCancelled();
            e.setResult(Event.Result.DENY);
            e.setResult(Event.Result.DENY);
        }
    }
}

