package com.gmail.markushygedombrowski.cobweb;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Location;
import org.bukkit.block.Block;

import javax.rmi.CORBA.Util;
import java.util.HashMap;

public class CobWeb {
    private HashMap<Location, Integer> cobweb = new HashMap<>();

    public void addCobWeb(int time, Location block) {
        cobweb.put( block,time);
    }
    public void removeCobWeb(Location block) {
        cobweb.remove(block);
    }
    public int getCobWeb(Location block) {
        return cobweb.get(block);
    }
    public boolean contains(Location block) {
        return cobweb.containsKey(block);
    }
    public double getChance(Block block) {
        double chance;
        if(Utils.isLocInRegion(block.getLocation(),"vagtvaultantiloga")) {
            if(Utils.isVagtOnline()) {
                chance = 1.5;
            } else {
                chance = 0.4;
            }
        } else {
            chance = 0.1;
        }

        return chance;
    }
    public void cobwebCooldown() {
        new HashMap<>(cobweb).forEach((key, value) -> {
            if (value > 0) {
                cobweb.put(key,value - 20);
            } else {
                cobweb.remove(key);
            }
        });
    }



}
