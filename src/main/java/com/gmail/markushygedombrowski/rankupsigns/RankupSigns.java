package com.gmail.markushygedombrowski.rankupsigns;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RankupSigns implements Listener {
    private Rankup rankup;
    private DeRank deRank;


    public RankupSigns(Rankup rankup,DeRank deRank) {
        this.rankup = rankup;
        this.deRank = deRank;
    }


    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock() instanceof Sign sign) {

                if (sign.getLine(0).equalsIgnoreCase("§8===============") && sign.getLine(1).equalsIgnoreCase("§aRankup") && sign.getLine(2).equalsIgnoreCase("§cKlik her") && sign.getLine(3).equalsIgnoreCase("§8===============")) {
                    if(!p.hasPermission("vagt") || p.isOp()) {
                        if(Utils.isLocInRegion(p.getLocation(),"B")) {
                            rankup.create(p,"§aA");
                        } else {
                            rankup.create(p,"§bB");
                        }

                    } else {
                        p.sendMessage("§cDet MÅ du ikke!!");
                    }

                }
                if (sign.getLine(0).equalsIgnoreCase("§8===============") && sign.getLine(1).equalsIgnoreCase("§4DeRank") && sign.getLine(2).equalsIgnoreCase("§cKlik her") && sign.getLine(3).equalsIgnoreCase("§8==============="))   {
                    if(!p.hasPermission("vagt") || p.isOp()) {
                        if(Utils.isLocInRegion(p.getLocation(),"a")) {
                            deRank.create(p,"§aA");
                        } else{
                            deRank.create(p,"§bB");
                        }

                    } else {
                        p.sendMessage("§cDet MÅ du ikke!!");
                    }
                }

            }
        }
    }
}
