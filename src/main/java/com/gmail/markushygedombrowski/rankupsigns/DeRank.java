package com.gmail.markushygedombrowski.rankupsigns;

import com.gmail.markushygedombrowski.HLUtils;
import com.gmail.markushygedombrowski.warp.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DeRank implements Listener {
    private int DERANK_INDEX = 13;
    private String region;
    private String message;
    private Location loc;
    private String perm;
    private String prePerm;

    private HLUtils plugin;
    private WarpManager warpManager;
    public DeRank(HLUtils plugin, WarpManager warpManager) {
        this.plugin = plugin;
        this.warpManager = warpManager;
    }
    public void create(Player p,String region) {
        this.region = region;
        Inventory inventory = Bukkit.createInventory(null, 27, "DeRank");
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta metabarrier = barrier.getItemMeta();
        metabarrier.setDisplayName("§4DeRank");
        List<String> lore = new ArrayList<>();
        lore.add("§cEr du sikker?");
        metabarrier.setLore(lore);
        barrier.setItemMeta(metabarrier);
        inventory.setItem(DERANK_INDEX, barrier);
        p.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        ItemStack clickeditem = event.getCurrentItem();
        int clickedSlot = event.getRawSlot();

        if (clickeditem == null) {
            return;
        }

        if (view.getTitle().equalsIgnoreCase("DeRank")) {
            if(clickedSlot == DERANK_INDEX) {
                setViables();
                p.getInventory().clear();
                p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                plugin.econ.withdrawPlayer(p, plugin.econ.getBalance(p));
                plugin.econ.depositPlayer(p, 400);
                p.teleport(loc);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user " + p.getName() + " parent remove " + prePerm + " prison");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user " + p.getName() + " parent add " + perm + " prison");
                Bukkit.broadcastMessage("§4§lDERANK§6 " + p.getName() + " §7Har lige §cDeRanket §7fra " + region + " §7til " + message);
            }

            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }

    public void setViables() {
        if(region.equalsIgnoreCase("§aA")) {
            loc = warpManager.getWarpInfo("b").getLocation();
            message = "§bB";
            perm = "b-fange";
            prePerm = "a-fange";
        } else {
            loc = warpManager.getWarpInfo("c").getLocation();
            message = "§cC";
            perm = "c-fange";
            prePerm = "b-fange";
        }
    }

}
