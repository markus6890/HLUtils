package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaterRunCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("water.run")) {
            player.sendMessage("§cDu har ikke tilladelse til at bruge denne kommando");
            return true;
        }
        Utils.changeWaterRunning();
        player.sendMessage("§aDu har nu " + (Utils.isWaterRunning() ? "aktiveret" : "deaktiveret") + " vandløb");
        return true;
    }
}
