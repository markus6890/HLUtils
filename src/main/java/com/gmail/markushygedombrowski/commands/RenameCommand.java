package com.gmail.markushygedombrowski.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("rename")) {
            player.sendMessage("§cDu har ikke adgang til dette.");
            return true;
        }
        if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage("§cDu skal have et item i hånden.");
            return true;
        }
        if(args.length == 0) {
            return true;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        String name = String.join(" ",args).replace("&","§");
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(item);

        return true;
    }
}
