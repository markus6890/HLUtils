package com.gmail.markushygedombrowski.itemblocking;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemBlockCommand implements CommandExecutor {
    private ItemManager itemManager;

    public ItemBlockCommand(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Kan kun bruges af Players");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("admin")) {
            p.sendMessage("§cDet har du ikke permission til!");
            return true;
        }
        if (p.getItemInHand() == null) {
            p.sendMessage("Tag en item i hånden");
            return true;
        }
        Material item = p.getItemInHand().getType();
        ItemInfo itemInfo =  itemManager.getItemInfo(item.name());

        if (itemInfo != null) {
            p.sendMessage("Det er allerede Blocked");
            return true;
        }
        p.sendMessage("item Blocked: " + item.name());
        itemInfo = new ItemInfo(item);

        itemManager.save(itemInfo);

        return true;
    }
}
