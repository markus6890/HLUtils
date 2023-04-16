package com.gmail.markushygedombrowski.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendMessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender.hasPermission("admin")) {
            Player p = Bukkit.getPlayer(strings[0]);
            if(p == null) {
                sender.sendMessage("Den player findes ikke");
                return true;
            }
            StringBuilder message = new StringBuilder();

            List<String> list = new ArrayList<>(Arrays.asList(strings));

            for(String text : list) {
                if(text.equals(strings[0])) {
                    continue;
                }
                message.append(" §7").append(text);
            }
            p.sendMessage(message.toString());
        }
        return true;
    }
}
