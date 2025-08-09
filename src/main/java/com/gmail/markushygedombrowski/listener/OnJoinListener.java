package com.gmail.markushygedombrowski.listener;

import com.gmail.markushygedombrowski.utils.ListHolder;
import com.gmail.markushygedombrowski.utils.PlayerOnTime;
import com.gmail.markushygedombrowski.utils.Utils;
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
        listHolder.addTotal(p.getName());
        if (p.hasPermission("vagt")) {
            if(Utils.isLocInRegion(p.getLocation(), "A")) {
                listHolder.addAVagt(p.getName());
            } else if(Utils.isLocInRegion(p.getLocation(), "B")) {
                listHolder.addBVagt(p.getName());
            } else if(Utils.isLocInRegion(p.getLocation(), "C")) {
                listHolder.addCVagt(p.getName());
            }
            listHolder.addVagt(p.getName());
            return;
        }
        if(Utils.isLocInRegion(p.getLocation(), "A")) {
            listHolder.addAFange(p.getName());
        } else if(Utils.isLocInRegion(p.getLocation(), "B")) {
            listHolder.addBFange(p.getName());
        } else if(Utils.isLocInRegion(p.getLocation(), "C")) {
            listHolder.addCFange(p.getName());
        }
        listHolder.addFange(p.getName());
    }

    private void removeFromList(Player p) {
        listHolder.removeFromALL(p.getName());
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
