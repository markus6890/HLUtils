package com.gmail.markushygedombrowski.itemblocking;

import org.bukkit.Material;

public class ItemInfo {

    private Material material;

    public ItemInfo(Material material) {
        this.material = material;
    }
    public Material getMaterial() {
        return material;
    }
    public String materialName() {
        return material.name();
    }

}
