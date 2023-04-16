package com.gmail.markushygedombrowski.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!sender.hasPermission("admin")) {
            sender.sendMessage("§cDet har du ikke permission til!");
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu skal være en player for at bruge denne command!");
            return true;
        }
        Player p = (Player) sender;
        if(p.getItemInHand() == null) {
            p.sendMessage("§cTag en item i hånden");
            return true;
        }
        p.sendMessage("§aItem: §7" + p.getItemInHand().getType().name());
        p.sendMessage("§aItem ID: §7" + p.getItemInHand().getTypeId());
        p.sendMessage("§aItem Data: §7" + p.getItemInHand().getData());
        p.sendMessage("§aItem Durability: §7" + p.getItemInHand().getDurability());
        p.sendMessage("§aItem Amount: §7" + p.getItemInHand().getAmount());
        p.sendMessage("§aItem Name: §7" + p.getItemInHand().getItemMeta().getDisplayName());
        p.sendMessage("§aItem Lore: §7" + p.getItemInHand().getItemMeta().getLore());
        p.sendMessage("§aItem Enchantments: §7" + p.getItemInHand().getEnchantments());
        p.sendMessage("§aItem MaxStackSize: §7" + p.getItemInHand().getMaxStackSize());
        p.sendMessage("§aItem MaxDurability: §7" + p.getItemInHand().getType().getMaxDurability());

        return true;
    }
}
