package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerWhisperEventCommand implements Listener {
    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().contentEquals("/w")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Incorrect Usage");
            e.getPlayer().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'/w playername message'");
        }
        if (!e.getMessage().toLowerCase().startsWith("/w"))
            return;
        String wraw = e.getMessage().replace("/w ", "");
        String[] wrawS = wraw.split(" ");
        String wraw3 = wraw.replace(wrawS[0] + " ", "");
        String wplayerName = wrawS[0];
        String wmessage = wraw.replace(wplayerName, "");
        Player wp = e.getPlayer();
        Player wp2 = Bukkit.getPlayerExact(wplayerName);
        if (wmessage.length() == 0) {
            e.setCancelled(true);
            //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
        }
        if (wmessage.length() >= 1) {
            e.setCancelled(true);
            try {
                Privatemessage.getInstance().getConfig();
                String wrecieve = Privatemessage.getInstance().getConfig().getString("wrecieve");
                String wsend = Privatemessage.getInstance().getConfig().getString("wsend");
                wrecieve = wrecieve.replaceAll("%player%", wp2.getDisplayName());
                wrecieve = wrecieve.replaceAll("%message%", wmessage);
                wsend = wsend.replaceAll("%player%", wp2.getDisplayName());
                wsend = wsend.replaceAll("%message%", wmessage);
                if (wmessage.length() > 3)
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', wsend));
                    wp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', wrecieve));
            } catch (NullPointerException ex) {
                wp.sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + wplayerName + "'");
                ex.printStackTrace();
            }

        }






    }
}
