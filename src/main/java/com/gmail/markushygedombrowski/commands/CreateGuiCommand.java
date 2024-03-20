package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CreateGuiCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Du skal være en spiller for at bruge denne kommando");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("gui.create")) {
            p.sendMessage("§4Du har ikke tilladelse til at bruge denne kommando");
            return true;
        }
        if(args.length == 0) {
            p.sendMessage("§4Brug /create <gui size>");
            return true;
        }
        int size = Utils.isInt(args[0]) ? Integer.parseInt(args[0]) : 9;
        create(p, size);
        return true;
    }

    public void create(Player p, int size) {
        Inventory inv = p.getServer().createInventory(null, size, "Gui");
        p.openInventory(inv);
    }
}
