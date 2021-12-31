package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerWhisperEventCommand implements Listener {
    @EventHandler
    public void onCommandSend(ServerCommandEvent e) {
        if (e.getCommand().toLowerCase().contentEquals("w")) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Incorrect Usage");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'w player-name message'");
        }
        if (!e.getCommand().toLowerCase().startsWith("w"))
            return;
        String wraw = e.getCommand().replace("w ", "");
        String[] wrawS = wraw.split(" ");
        String wraw3 = wraw.replace(wrawS[0] + " ", "");
        String wplayerName = wrawS[0];
        String message = wraw.replace(wplayerName, "");
        Player wp2 = Bukkit.getPlayerExact(wplayerName);
        if (message.length() == 0) {
            e.setCancelled(true);
            //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
        }
        if (message.length() >= 1) {
            e.setCancelled(true);
            try {
                //Getting Config
                Privatemessage.getInstance().getConfig();
                String wrecieve = Privatemessage.getInstance().getConfig().getString("serverwrecieve");
                String wsend = Privatemessage.getInstance().getConfig().getString("serverwsend");
                //Replacing Placeholders
                wsend = wsend.replaceAll("%player%", wp2.getDisplayName());
                wsend = wsend.replaceAll("%message%", message);
                wrecieve = wrecieve.replaceAll("%server%", Privatemessage.getInstance().getConfig().getString("servername"));
                wrecieve = wrecieve.replaceAll("%message%", message);
                //Sending Messages
                if (message.length() > 3)
                    wp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', wrecieve));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', wsend));
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + wplayerName + "'");
                ex.printStackTrace();
            }
        }
    }
}
