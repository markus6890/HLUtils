package com.gmail.markushygedombrowski.utils;

import com.gmail.markushygedombrowski.HLUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerOnTime {
    private Settings settings;
    private Map<UUID, Integer> playerMap =  new HashMap<>();

    private HLUtils plugin;

    public PlayerOnTime(Settings settings, HLUtils plugin) {
        this.settings = settings;
        this.plugin = plugin;
    }

    public boolean hasPlayer(Player player) {
        return playerMap.containsKey(player.getUniqueId());
    }
    public void addPlayer(UUID player) {

        playerMap.put(player, settings.getOntimeTime());
    }

    public void cooldown() {
        new HashMap<>(playerMap).forEach((key, value) -> {
            Player player = plugin.getServer().getPlayer(key);
            if(player == null) {
                return;
            }
            if (value > 0) {
                playerMap.put(key,value - 20);
            } else {
                plugin.econ.depositPlayer(player, settings.getOntimeMoney());
                player.sendMessage("§7Du har fået §a" + settings.getOntimeMoney() + "$ §7 for at være online i §a" + settings.getOntimeTime() + " §7minutter");
                playerMap.remove(key);
                playerMap.put(key, settings.getOntimeTime());
            }
        });

    }


}
