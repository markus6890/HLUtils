package com.gmail.markushygedombrowski.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            System.out.println("Kan kun bruges af Players");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("admin")) {
            p.sendMessage("§cDet har du ikke permission til!");
            return true;
            
        }
        if(args.length == 0) {
            p.sendMessage("§7§l----------§4§lSTAFF§7§l----------");
            p.sendMessage("§c /ecd §7<UUID>");
            p.sendMessage("§7§l----------§4§lSTAFF§7§l----------");
            return true;
        }
        UUID uuid = UUID.fromString(args[0]);
        OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
        if(target == null) {
            p.sendMessage("§cDenne spiller eksisterer ikke!");
            return true;
        }
        p.openInventory(target.getPlayer().getEnderChest());
        p.sendMessage("§7Du har nu åbnet §4" + target.getName() + "§7's enderchest");
        return true;
    }
}
