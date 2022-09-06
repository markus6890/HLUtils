package com.gmail.markushygedombrowski.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("Admin")) {
            sender.sendMessage("§cDet har du ikke permission til!!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("/gm <gamemode>");
            return true;
        }
        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);
            if(p == null) {
                sender.sendMessage("den player findes ikke ");
                return true;
            }
            setGameMode(args[0],p);
            return true;
        } else {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                setGameMode(args[0], p);
                return true;
            }
        }



        return true;
    }

    public void setGameMode(String mode, Player p) {

        if(mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("0")) {
            p.setGameMode(GameMode.SURVIVAL);
            mode = "survival";
        } else if(mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("1")) {
            p.setGameMode(GameMode.CREATIVE);
            mode = "creative";
        } else if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("2")) {
            p.setGameMode(GameMode.ADVENTURE);
            mode = "adventure";
        } else if(mode.equalsIgnoreCase("spectator") || mode.equalsIgnoreCase("3")) {
            p.setGameMode(GameMode.SPECTATOR);
            mode = "spectator";
        } else return;

        p.sendMessage("Du er i gamemode: " + mode);

    }
}
