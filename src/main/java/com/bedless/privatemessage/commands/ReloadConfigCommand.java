package com.bedless.privatemessage.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@CommandAlias("reloadpm")
@CommandPermission("privmsg.reloadcf")
public class ReloadConfigCommand extends BaseCommand implements Listener {

    @Default
    public void onCommand(Player p, String playerName, String[] args) {
        Privatemessage.getInstance().reloadConfig();
        p.sendMessage(ChatColor.DARK_RED + "Reloaded Configuration from Private Message");
    }
}
