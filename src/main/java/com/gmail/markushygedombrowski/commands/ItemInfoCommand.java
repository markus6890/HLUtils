package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        ItemStack item = p.getItemInHand();
        if(item == null || item.getType() == Material.AIR) {
            Block block = Utils.getTargetBlock(p, 5);
            if(block == null) {
                p.sendMessage("§cDu skal holde et item i hånden eller kigge på en block!");
                return true;
            }
            item = block.getState().getData().toItemStack();
        }

        p.sendMessage("§aItem: §7" + item.getType().name());
        p.sendMessage("§aItem Data: §7" + item.getData());
        p.sendMessage("§aItem Durability: §7" + item.getDurability());
        p.sendMessage("§aItem Amount: §7" + item.getAmount());
        p.sendMessage("§aItem Name: §7" + item.getItemMeta().getDisplayName());
        p.sendMessage("§aItem Lore: §7" + item.getItemMeta().getLore());
        p.sendMessage("§aItem Enchantments: §7" + item.getEnchantments());
        p.sendMessage("§aItem MaxStackSize: §7" + item.getMaxStackSize());
        p.sendMessage("§aItem MaxDurability: §7" + item.getType().getMaxDurability());

        return true;
    }
}
