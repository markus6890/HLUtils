package com.gmail.markushygedombrowski.warp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpCommand implements CommandExecutor {
    private final WarpManager warpManager;
    private final JavaPlugin plugin;

    public WarpCommand(WarpManager warpManager, JavaPlugin plugin) {
        this.warpManager = warpManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("warpAllow")){
            sender.sendMessage("Det har du ikke permission til!");
            return true;
        }
        if (args.length == 0) {
            if(sender.hasPermission("warpadmin")){
                sender.sendMessage("§e/hlsetwarp <name>");
                sender.sendMessage("§e/hldeletewarp <name>");
            }
            sender.sendMessage("§e/hlwarp <name>");
            sender.sendMessage("§e/hlwarplist");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cthis command can only be used by players");
            return true;
        }
        Player player = (Player) sender;
        String warpName = args[0];
        WarpInfo info = warpManager.getWarpInfo(warpName);

        if (alias.equalsIgnoreCase("hlsetwarp")) {
            setWarp(player, warpName, info);
            return true;
        }
        if (alias.equalsIgnoreCase("hldeletewarp")) {
            deleteWarp(player, warpName, info);
            return true;
        }
        if(alias.equalsIgnoreCase("hlwarplist")){
            warpList(player);
            return true;
        }

        if (info == null) {
            sender.sendMessage("§cThat warp doesn't exist");
            return true;
        }


        player.teleport(info.getLocation());
        return true;
    }

    private void setWarp(Player player, String warpName, WarpInfo info) {
        if (!player.hasPermission("warpadmin")) {
            player.sendMessage("Det har du ikke permission til!");
            return;
        }
        if (info != null) {
            player.sendMessage("§cThat warp already exist!");
            return;
        }
        Location location = player.getLocation();
        info = new WarpInfo(warpName, location);
        warpManager.save(info);
        player.sendMessage("§a§lWarp created at §e" + location.toString());
    }
    private void deleteWarp(Player player, String warpName, WarpInfo info) {
        if (!player.hasPermission("warpadmin")) {
            player.sendMessage("Det har du ikke permission til!");
            return;
        }
        if (info == null) {
            player.sendMessage("§cThat warp doesn't exist!");
            return;
        }
        warpManager.delete(warpName);
        player.sendMessage("§a§lWarp deleted");
    }
    private void warpList(Player player) {
        player.sendMessage("§a§lWarp list:");

        warpManager.getWarpMap().forEach((name, info) -> {
            player.sendMessage("§e" + name);
        });
    }
}
