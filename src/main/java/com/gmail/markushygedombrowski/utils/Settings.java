package com.gmail.markushygedombrowski.utils;

import org.bukkit.configuration.file.FileConfiguration;


public class Settings {


    private int arankup;
    private int brankup;


    private int normalkitTime;

    public void load(FileConfiguration config) {
        this.arankup = config.getInt("arankup");
        this.brankup = config.getInt("brankup");
        this.normalkitTime = config.getInt("normalkitTime");
    }


    public int getArankup() {
        return arankup;
    }

    public int getBrankup() {
        return brankup;
    }

    public int getNormalkitTime() {
        return (normalkitTime * 60 * 60);

    }
}
