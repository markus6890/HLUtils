package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.cobweb.CobWeb;
import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {
private CobWeb cobWeb;

    public Listener(CobWeb cobWeb) {
        this.cobWeb = cobWeb;
    }

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

        if (block == Material.CACTUS || block == Material.WHEAT || block == Material.SAND || block == Material.SEEDS || block == Material.SUGAR_CANE_BLOCK) {
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
                if(Utils.isLocInRegion(block.getLocation(), "jaildoor-a") && !event.getPlayer().hasPermission("vagt")) {
                    return;
                }
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
        if (cobweb(event, player, block)) return;
        if (Utils.isLocInRegion(block.getLocation(), "c-d")) {
            if (block.getType() == Material.STAINED_CLAY || block.getType() == Material.LOG) {
                event.setCancelled(true);
                event.isCancelled();

            }
        }


    }

    private boolean cobweb(BlockBreakEvent event, Player player, Block block) {
        if (player.hasPermission("breakcobweb")) {
            if (block.getType() == Material.WEB) {
                if(cobWeb.contains(block.getLocation())){
                    player.sendMessage("§cDu har allerede smadret dette cobweb inden for de sidste 3 timer");
                    event.setCancelled(true);
                    event.isCancelled();
                    return true;
                }
                if(player.getItemInHand().getType() != Material.DIAMOND_SWORD) {
                    player.sendMessage("§cDu skal bruge et §b§lDiamond Sword §cfor at smadre cobweb");
                    event.setCancelled(true);
                    event.isCancelled();
                    return true;
                }
                if (Utils.procent(cobWeb.getChance(block))) {
                    player.getInventory().addItem(new ItemStack(Material.STRING, 1));
                    player.sendMessage("§aDu fik 1 §f§lString");
                    player.sendMessage("§7 og dit §csværd §7gik i stykker");
                    player.getInventory().removeItem(player.getItemInHand());
                } else {
                    player.sendMessage("§cDu fik ikke noget String");
                }
                int time = 3 * 60 * 60 * 20;
                cobWeb.addCobWeb(time, block.getLocation());
                event.setCancelled(true);
                event.isCancelled();
                return true;
            }
        }
        return false;
    }
}


