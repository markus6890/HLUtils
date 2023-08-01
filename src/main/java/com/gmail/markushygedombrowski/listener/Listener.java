package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;
import org.bukkit.material.Redstone;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onPortalBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.PORTAL) {
            e.setCancelled(true);
            e.isCancelled();
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {

        Player p = event.getPlayer();
        if (!p.hasPermission("vagtbreak")) return;

        if (p.isOp()) return;
        Material block = event.getBlock().getType();

        if (block == Material.CACTUS || block == Material.WHEAT || block == Material.SAND || block == Material.SEEDS) {
            p.sendMessage("§cHOV det må du ikke!!");
            event.setCancelled(true);
            event.isCancelled();
        }


    }

    @EventHandler
    public void pvpMinen(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPermission("vagt")) return;
        if (p.isOp()) return;
        Location loc = event.getBlock().getLocation();
        if (Utils.isLocInRegion(loc, "bpvpmine")) {
            p.sendMessage("§cHOV det må du ikke!!");
            event.setCancelled(true);
            event.isCancelled();
        }
    }

    @EventHandler
    public void ironDoor(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.IRON_DOOR_BLOCK) {
                Block block = event.getClickedBlock();
                if (Utils.getWorldGuard().canBuild(event.getPlayer(), block.getLocation()) || event.getPlayer().hasPermission("irondoor")) {
                    if (block.getType() == Material.IRON_DOOR_BLOCK) {
                        if (block.getData() >= 8) {
                            block = block.getRelative(BlockFace.DOWN);
                        }
                        if (block.getType() == Material.IRON_DOOR_BLOCK) {
                            if (block.getData() < 4) {
                                block.setData((byte) (block.getData() + 4));
                            } else {
                                block.setData((byte) (block.getData() - 4));
                            }
                        }
                    }


                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();


        if (player.hasPermission("bygger")) {
            return;
        }
        if(Utils.isLocInRegion(block.getLocation(), "c-d")) {
            if (block.getType() == Material.STAINED_CLAY || block.getType() == Material.LOG) {
                event.setCancelled(true);
                event.isCancelled();

            }
        }


    }
}

