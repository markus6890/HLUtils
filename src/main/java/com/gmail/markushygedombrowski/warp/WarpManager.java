package com.gmail.markushygedombrowski.warp;

import com.gmail.markushygedombrowski.utils.ConfigManagerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class WarpManager {
    private final Map<String, WarpInfo> warpMap = new HashMap<>();
    private final JavaPlugin plugin;
    private ConfigManagerUtils configM;

    public WarpManager(JavaPlugin plugin, ConfigManagerUtils configM) {
        this.plugin = plugin;
        this.configM = configM;
    }

    public void load() {
        FileConfiguration config = configM.getWarps();
        warpMap.clear();
        for (String key : config.getConfigurationSection("warps").getKeys(false)) {
            String name = config.getString("warps." + key + ".name");
            double x = config.getDouble("warps." + key + ".location.x");
            double y = config.getDouble("warps." + key + ".location.y");
            double z = config.getDouble("warps." + key + ".location.z");
            float yawn = (float) config.getDouble("warps." + key + ".location.yawn");
            float pitch = (float) config.getDouble("warps." + key + ".location.pitch");
            String stringWorld = config.getString("warps." + key + ".location.world");
            World world = Bukkit.getWorld(stringWorld);
            Location location = new Location(world, x, y, z,yawn,pitch);
            WarpInfo warpInfo = new WarpInfo(name, location);
            warpMap.put(name, warpInfo);
        }
    }

    public void save(WarpInfo warpInfo) {
        FileConfiguration config = configM.getWarps();
        String key = "" + (warpMap.size() + 1);
        config.set("warps." + key + ".name", warpInfo.getName());
        config.set("warps." + key + ".location.x", warpInfo.getLocation().getX());
        config.set("warps." + key + ".location.y", warpInfo.getLocation().getY());
        config.set("warps." + key + ".location.z", warpInfo.getLocation().getZ());
        config.set("warps." + key + ".location.yawn", warpInfo.getLocation().getYaw());
        config.set("warps." + key + ".location.pitch", warpInfo.getLocation().getPitch());
        config.set("warps." + key + ".location.world", warpInfo.getLocation().getWorld().getName());
        configM.saveWarps();
        warpMap.put(warpInfo.getName(), warpInfo);

    }
    public void delete(String warpName) {
        FileConfiguration config = configM.getWarps();
        config.set("warps." + warpName, null);
        configM.saveWarps();
        warpMap.remove(warpName);
    }


    public WarpInfo getWarpInfo(String warpName) {
        return warpMap.get(warpName);
    }
    public Map<String, WarpInfo> getWarpMap() {
        return warpMap;
    }

}
