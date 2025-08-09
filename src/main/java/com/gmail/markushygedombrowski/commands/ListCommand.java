package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.utils.Category;
import com.gmail.markushygedombrowski.utils.ListHolder;
import com.gmail.markushygedombrowski.utils.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements CommandExecutor {
    private ListHolder listHolder;

    public ListCommand(ListHolder listHolder) {
        this.listHolder = listHolder;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        sender.sendMessage("§7§l----------§6§lLIST§7§l----------");
        sender.sendMessage(getMessages().toArray(new String[0]));
        sender.sendMessage("§7§l----------§6§lLIST§7§l----------");

        return true;
    }

    public List<String> getMessages() {
        List<String> messages = new ArrayList<>();
        messages.add("§cC: §6Fanger§7: " + listHolder.getList(Category.fromString("C"), Role.FANGE).size() + " §cVagter§7: " + listHolder.getList(Category.fromString("C"), Role.VAGT).size());
        messages.add("§bB: §6Fanger§7: " + listHolder.getList(Category.fromString("B"), Role.FANGE).size() + " §bVagter§7: " + listHolder.getList(Category.fromString("B"), Role.VAGT).size());
        messages.add("§aA: §6Fanger§7: " + listHolder.getList(Category.fromString("A"), Role.FANGE).size() + " §aVagter§7: " + listHolder.getList(Category.fromString("A"), Role.VAGT).size());
        messages.add("§6§lTotal§7: " + listHolder.getTotalList().size());
        return messages;
    }
}
