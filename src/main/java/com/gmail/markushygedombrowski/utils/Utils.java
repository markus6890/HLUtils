package com.gmail.markushygedombrowski.utils;


import com.gmail.markushygedombrowski.HLUtils;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Utils {
    private static boolean waterRunning = false;

    public static boolean isWaterRunning() {
        return waterRunning;
    }

    public static void changeWaterRunning() {
        Utils.waterRunning = !Utils.waterRunning;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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

    private static ApplicableRegionSet getWGSet(Location loc) {
        RegionContainer rc = WorldGuard.getInstance().getPlatform().getRegionContainer();
        if (rc == null) {
            return null;
        }
        World word = BukkitAdapter.adapt(Objects.requireNonNull(loc.getWorld()));
        RegionManager rm = rc.get(word);
        if (rm == null) {
            return null;
        }
        return rm.getApplicableRegions(BukkitAdapter.asBlockVector(loc));
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

        if (!(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }

    public static boolean regionHasFlag(Location loc, StateFlag flag, StateFlag.State flagState) {
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
        WorldGuard wg = WorldGuard.getInstance();
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
        double num = r.nextDouble() * 100;  // Returns a double between 0.0 and 100.0
        return num <= pro;
    }


    public static int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void sendActionbar(Player player, String message) {
        if (player == null || message == null) return;
        /*String nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);


        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);

            Class<?> ppoc = Class.forName("net.minecraft.server." + nmsVersion + ".PacketPlayOutChat");
            Class<?> packet = Class.forName("net.minecraft.server." + nmsVersion + ".Packet");
            Object packetPlayOutChat;
            Class<?> chat = Class.forName("net.minecraft.server." + nmsVersion + (nmsVersion.equalsIgnoreCase("v1_8_R1") ? ".ChatSerializer" : ".ChatComponentText"));
            Class<?> chatBaseComponent = Class.forName("net.minecraft.server." + nmsVersion + ".IChatBaseComponent");

            Method method = null;
            if (nmsVersion.equalsIgnoreCase("v1_8_R1")) method = chat.getDeclaredMethod("a", String.class);

            Object object = nmsVersion.equalsIgnoreCase("v1_8_R1") ? chatBaseComponent.cast(method.invoke(chat, "{'text': '" + message + "'}")) : chat.getConstructor(new Class[]{String.class}).newInstance(message);
            packetPlayOutChat = ppoc.getConstructor(new Class[]{chatBaseComponent, Byte.TYPE}).newInstance(object, (byte) 2);

            Method handle = craftPlayerClass.getDeclaredMethod("getHandle");
            Object iCraftPlayer = handle.invoke(craftPlayer);
            Field playerConnectionField = iCraftPlayer.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(iCraftPlayer);
            Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", packet);
            sendPacket.invoke(playerConnection, packetPlayOutChat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(message).create());
    }

    public static String getRegion(Location location) {

        if (Utils.isLocInRegion(location, "a")) {
            return "a";
        } else if (Utils.isLocInRegion(location, "b")) {
            return "b";
        } else if (Utils.isLocInRegion(location, "a+")) {
            return "a+";
        } else {
            return "c";
        }
    }

    public static void sendCooldownBar(Player player, int seconds, String frontMessage, String backMessage, String frontBarColor, String backBarColor) {
        String[] finalMessage = new String[1];
        String squares = "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛";
        int[] c = {0};
        Bukkit.getScheduler().runTaskTimer(HLUtils.getInstance(), () -> {
            if (c[0] < seconds) {
                finalMessage[0] = frontMessage + frontBarColor + squares.substring(0, c[0]) + backBarColor + squares.substring(c[0]) + backMessage;
                sendActionbar(player, finalMessage[0]);
                c[0]++;
            } else {
                finalMessage[0] = frontMessage + backBarColor + squares + backMessage;
                sendActionbar(player, finalMessage[0]);
                c[0] = 0;
            }
        }, 0, seconds * 20L);


    }

    public static boolean canBuild(Player player, Location loc) {
        if (player == null || loc == null) return false;
        WorldGuard wg = WorldGuard.getInstance();
        if (wg == null) return false;
        RegionContainer rc = wg.getPlatform().getRegionContainer();
        if (rc == null) return false;
        World word = BukkitAdapter.adapt(loc.getWorld());
        RegionManager rm = rc.get(word);
        if (rm == null) return false;
        ProtectedRegion r = rm.getApplicableRegions(BukkitAdapter.asBlockVector(loc)).getRegions().iterator().next();
        if (r == null) return false;
        return r.getMembers().contains(player.getUniqueId()) || r.getOwners().contains(player.getUniqueId());
    }


}
















