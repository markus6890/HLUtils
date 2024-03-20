package com.gmail.markushygedombrowski.utils;


import com.gmail.markushygedombrowski.HLUtils;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

import java.text.DecimalFormat;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Utils {

    public static boolean isLocInRegion(Location loc, String regionName) {
        if (regionName == null) {
            return true;
        }
        ApplicableRegionSet set = getWGSet(loc);
        if (set == null) {
            return false;
        }
        for (ProtectedRegion r : set) {
            if (r.getId().equalsIgnoreCase(regionName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static ApplicableRegionSet getWGSet(Location loc) {
        WorldGuardPlugin wg = getWorldGuard();
        if (wg == null) {
            return null;
        }
        RegionManager rm = wg.getRegionManager(loc.getWorld());
        if (rm == null) {
            return null;
        }
        return rm.getApplicableRegions(com.sk89q.worldguard.bukkit.BukkitUtil.toVector(loc));
    }
    public static Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

    public static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
    public static boolean regionHasFlag(Location loc, StateFlag flag,StateFlag.State flagState) {
        ApplicableRegionSet set = getWGSet(loc);
        if (set == null) {
            return false;
        }

        for (ProtectedRegion r : set.getRegions()) {
            if (r.getFlag(flag) != null) {
                StateFlag.State state = r.getFlag(flag);
                if (state == flagState) {
                    return true;
                }
            }
        }
        return false;
    }
    public static StateFlag getFlag(String flag) {
        WorldGuardPlugin wg = getWorldGuard();
        if (wg == null) {
            return null;
        }
        FlagRegistry registry = wg.getFlagRegistry();
        Flag<?> exiting = registry.get(flag);
        if (exiting instanceof StateFlag) {
            return (StateFlag) exiting;
        } else {
            return null;
        }
    }
    public static boolean isVagtOnline() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("vagt")) {
                return true;
            }
        }
        return false;
    }
    public static boolean procent(double pro) {
        Random r = new Random();
        double num = r.nextInt(100);
        return num <= pro;

    }





}
















