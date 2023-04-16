package com.gmail.markushygedombrowski.rankupsigns;

import com.gmail.markushygedombrowski.HLUtils;
import com.gmail.markushygedombrowski.utils.Settings;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Rankup implements Listener {
    private int RANKUP_INDEX = 13;
    private int pay = 0;
    private Settings settings;
    private String region;
    private HLUtils plugin;
    private Location loc;
    private WarpManager warpManager;
    private String perm;
    private String prePerm;

    public Rankup(Settings settings, HLUtils plugin, WarpManager warpManager) {
        this.settings = settings;
        this.plugin = plugin;
        this.warpManager = warpManager;
    }

    public void create(Player p, String region) {
        String pattern = "###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        this.region = region;
        Inventory inventory = Bukkit.createInventory(null, 27, "Rankup: " + region);
        setViables();
        ItemStack dia = new ItemStack(Material.DIAMOND);
        ItemMeta metaDia = dia.getItemMeta();
        metaDia.setDisplayName("§aRank up til " + region);
        List<String> lore = new ArrayList<>();
        lore.add("§7Koster: §a" + df.format(pay));
        metaDia.setLore(lore);

        dia.setItemMeta(metaDia);

        inventory.setItem(RANKUP_INDEX, dia);
        p.openInventory(inventory);


    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack clickeditem = event.getCurrentItem();
        int clickedSlot = event.getRawSlot();

        if (clickeditem == null) {
            return;
        }

        if (inventory.getTitle().equalsIgnoreCase("Rankup: " + region)) {
            if (clickedSlot == RANKUP_INDEX) {
                if (plugin.econ.has(p, pay)) {
                    p.getInventory().clear();
                    p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                    p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                    p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                    p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                    plugin.econ.withdrawPlayer(p, plugin.econ.getBalance(p));
                    plugin.econ.depositPlayer(p, 1000);
                    giveAItems(p);
                    p.teleport(loc);
                    Bukkit.broadcastMessage("§2§lRANKUP§6 " + p.getName() + " §7Har lige §aRanket up §7til " + region + " §2Tillykke!");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user " + p.getName() + " parent add " + perm + " prison");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user " + p.getName() + " parent remove " + prePerm + " prison");

                } else {
                    p.sendMessage("§cDu har ikke nok penge til Rankup til " + region);

                }
            }


            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }


    }

    private void giveAItems(Player p) {
        if(region.equalsIgnoreCase("§aA")) {
           p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 10));
           p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
        }
    }


    public void setViables() {
        if (region.equalsIgnoreCase("§aA")) {
            pay = settings.getArankup();
            loc = warpManager.getWarpInfo("a").getLocation();
            perm = "a-fange";
            prePerm = "b-fange";
        } else if (region.equalsIgnoreCase("§bB")) {
            pay = settings.getBrankup();
            loc = warpManager.getWarpInfo("b").getLocation();
            perm = "b-fange";
            prePerm = "c-fange";
        } else {
            pay = 1000000;
            loc = warpManager.getWarpInfo("c").getLocation();
            perm = "c-fange";
        }
    }
}
