package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.itemblocking.ItemManager;
import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;


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

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("bygger") || p.hasPermission("admin")) {
            return;
        }
        if ((e.getBlock().getType() == Material.SMOOTH_BRICK || e.getBlock().getType() == Material.SEA_LANTERN)) {
            p.sendMessage("§cDu kan ikke smadre dette!");
            e.setCancelled(true);
            e.isCancelled();
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("bygger") || p.hasPermission("admin")) {
            return;
        }
        if (e.getBlock().getType() == Material.SIGN || e.getBlock().getType() == Material.SIGN_POST || e.getBlock().getType() == Material.WALL_SIGN) {
            p.sendMessage("§CDet kan du ikke pladsere!");
            e.setCancelled(true);
            e.isCancelled();
        }
    }
    @EventHandler
    public void takeWater(PlayerBucketFillEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("bygger") || p.hasPermission("admin")) {
            return;
        }
        if (event.getBucket() == Material.BUCKET && Utils.isLocInRegion(event.getBlockClicked().getLocation(), "aude")) {
            p.sendMessage("§cDu kan ikke tage vand!");
            event.getBlockClicked().getState().update();
            event.setCancelled(true);
            event.isCancelled();

        }

    }
    @EventHandler
    public void onPlaceWater(PlayerBucketEmptyEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("bygger") || p.hasPermission("admin")) {
            return;
        }
        if (event.getBucket() == Material.WATER_BUCKET && Utils.isLocInRegion(event.getBlockClicked().getLocation(), "aude")) {
            p.sendMessage("§cDu kan ikke placere vand!");
            event.getBlockClicked().getState().update();
            event.setCancelled(true);
            event.isCancelled();

        }
    }

    @EventHandler
    public void stopLiquids(BlockFromToEvent event) {
        if (!event.getBlock().isLiquid()) {
            return;
        }

        event.setCancelled(true);
    }
}

