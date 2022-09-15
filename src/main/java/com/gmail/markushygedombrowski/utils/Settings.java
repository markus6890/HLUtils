package com.gmail.markushygedombrowski.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Settings {



    private int arankup;
    private int brankup;

    public void load(FileConfiguration config) {
        this.arankup = config.getInt("arankup");
        this.brankup = config.getInt("brankup");
        String[] example = {"line1", "line2", "wow this is a list of strings on a default configuration"};
        config.set("bannaditems", example);
    }


    public int getArankup() {
        return arankup;
    }

    public int getBrankup() {
        return brankup;
    }
}
