package com.gmail.markushygedombrowski.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.awt.*;


public class Settings {


    private int arankup;
    private int brankup;
    private int ontimeMoney;
    private int ontimeTime;

    private int normalkitTime;

    public void load(FileConfiguration config) {
        this.arankup = config.getInt("arankup");
        this.brankup = config.getInt("brankup");
        this.normalkitTime = config.getInt("normalkitTime");
        this.ontimeMoney = config.getInt("ontimemoney");
        this.ontimeTime = config.getInt("ontimeinterval");
        RandomChanceCollection<ItemStack> rnc = new RandomChanceCollection<>();

    }
    public int getOntimeMoney() {
        return ontimeMoney;
    }
    public int getOntimeTime() {
        return (ontimeTime * 60 * 60 * 20);
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
