package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.utils.ListHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListCommand implements CommandExecutor {
    private ListHolder listHolder;

    public ListCommand(ListHolder listHolder) {
        this.listHolder = listHolder;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        sender.sendMessage("§7§l----------§6§lLIST§7§l----------");
        sender.sendMessage("§6§lVagter: §7" + listHolder.getVagtList().size());
        sender.sendMessage("§6§lFanger: §7" + listHolder.getFangeList().size());
        sender.sendMessage("§6§lTotal: §7" + listHolder.getTotalList().size());
        sender.sendMessage("§7§l----------§6§lLIST§7§l----------");
        if (args.length == 1) {
            StringBuilder message;
            if (args[0].equalsIgnoreCase("fanger")) {
                message = new StringBuilder("§6§lFanger:");
                sender.sendMessage("§7§l----------§c§lFANGER§7§l----------");
                listHolder.getFangeList().forEach(fange -> {
                    message.append(" §7").append(fange);
                });
                sender.sendMessage(message.toString());
                sender.sendMessage("§7§l----------§c§lFANGER§7§l----------");
                return true;
            }
            if (args[0].equalsIgnoreCase("vagter") || args[0].equalsIgnoreCase("vagt")) {
                message = new StringBuilder("§6§lVagter:");
                sender.sendMessage("§7§l----------§c§lVAGTER§7§l----------");
                listHolder.getVagtList().forEach(vagt -> {
                    message.append(" §7").append(vagt);
                });
                sender.sendMessage(message.toString());
                sender.sendMessage("§7§l----------§c§lVAGTER§7§l----------");
                return true;
            }
            return true;
        }
        return true;
    }
}
