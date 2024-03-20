package com.gmail.markushygedombrowski.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Testlistener implements Listener {

    @EventHandler
    public void onTestInventoryClick(InventoryClickEvent inventoryClickEvent) {
        System.out.println("Test inventory click event");

    }
    @EventHandler
    public void onTestInventoryDrag(InventoryDragEvent inventoryClickEvent) {
        System.out.println("Test inventory drag event ");

    }
    @EventHandler
    public void onTestInventoryMove(InventoryMoveItemEvent inventoryClickEvent) {
        //System.out.println("Test inventory Move event");

    }
    @EventHandler
    public void onTestInventoryIntercet(InventoryInteractEvent inventoryClickEvent) {
        System.out.println("Test inventory interact event ");

    }
    @EventHandler
    public void onTestInventoryMove(PlayerInteractEntityEvent entityEvent) {
        System.out.println("Test armor stand interact event ");

    }
}
