package com.gmail.markushygedombrowski.utils;

import com.gmail.markushygedombrowski.HLUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Configreloadcommand implements CommandExecutor {

    private HLUtils plugin;
    public Configreloadcommand(HLUtils plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender.hasPermission("HLreload"))) {
            sender.sendMessage("§aYou do not have permission to do that");
            return true;
        }
        plugin.reload();

        sender.sendMessage("§a§lPlugin HLUtils reloaded!");

        return true;
    }
}
