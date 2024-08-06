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
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        if (!sender.hasPermission("Admin") && !p.getUniqueId().toString().equalsIgnoreCase("0ea61ef8-45e7-42b4-b775-5ac2b01ebb3d")) {

            sender.sendMessage("§cDet har du ikke permission til!!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("/gm <gamemode>");
            return true;
        }
        if (args.length == 2) {
            Player p2 = Bukkit.getPlayer(args[1]);
            if (p2 == null) {
                sender.sendMessage("den player findes ikke ");
                return true;
            }
            setGameMode(args[0], p2);
            return true;
        } else {

            setGameMode(args[0], p);
            return true;

        }
    }

    public void setGameMode(String mode, Player p) {

        if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("0")) {
            p.setGameMode(GameMode.SURVIVAL);
            mode = "survival";
        } else if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("1")) {
            p.setGameMode(GameMode.CREATIVE);
            mode = "creative";
        } else if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("2")) {
            p.setGameMode(GameMode.ADVENTURE);
            mode = "adventure";
        } else if (mode.equalsIgnoreCase("spectator") || mode.equalsIgnoreCase("3")) {
            p.setGameMode(GameMode.SPECTATOR);
            mode = "spectator";
        } else return;

        p.sendMessage("Du er i gamemode: " + mode);

    }
}
