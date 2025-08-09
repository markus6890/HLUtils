package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.utils.*;
import com.gmail.markushygedombrowski.warp.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoinListener implements Listener {
    private WarpManager warpManager;
    private ListHolder listHolder;
    private PlayerOnTime playerOnTime;

    public OnJoinListener(WarpManager warpManager, ListHolder listHolder, PlayerOnTime playerOnTime) {
        this.warpManager = warpManager;
        this.listHolder = listHolder;
        this.playerOnTime = playerOnTime;
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.hasPlayedBefore()) {
            return;
        }
        p.teleport(warpManager.getWarpInfo("c").getLocation());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        if(!playerOnTime.hasPlayer(p)) {
            playerOnTime.addPlayer(p.getUniqueId());
        }
        addToList(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        removeFromList(p);
    }

    private void addToList(Player p) {
        listHolder.addToTotal(p.getName());
        if (p.hasPermission("vagt")) {
            listHolder.add(Category.fromString(Utils.getRegion(p.getLocation()).toUpperCase()), Role.VAGT, p.getName());
            return;
        }
        listHolder.add(Category.fromString(Utils.getRegion(p.getLocation()).toUpperCase()), Role.FANGE, p.getName());

    }

    private void removeFromList(Player p) {
        listHolder.removeFromAll(p.getName());
    }

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
       Player p = event.getPlayer();
       if(event.getNewGameMode() == GameMode.CREATIVE) {
           removeFromList(p);
           return;
       }
       if(event.getNewGameMode() == GameMode.SPECTATOR) {
           removeFromList(p);
           return;
       }
       if (event.getNewGameMode() == GameMode.SURVIVAL) {
           addToList(p);
           return;
       }


    }


}
