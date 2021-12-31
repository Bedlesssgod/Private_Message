package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerTellEventCommand implements Listener {
    @EventHandler
    public void onCommandSend(ServerCommandEvent e) {
        Bukkit.getConsoleSender().sendMessage(e.getCommand());
        if (e.getCommand().toLowerCase().contentEquals("tell")) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Incorrect Usage");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'tell player-name message'");
        }
        if (!e.getCommand().toLowerCase().startsWith("tell"))
            return;
        String tellraw = e.getCommand().replace("tell ", "");
        String[] tellrawS = tellraw.split(" ");
        String tellraw3 = tellraw.replace(tellrawS[0] + " ", "");
        String tellplayerName = tellrawS[0];
        String message = tellraw.replace(tellplayerName, "");
        Player tellp2 = Bukkit.getPlayerExact(tellplayerName);
        if (message.length() == 0) {
            e.setCancelled(true);
            //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
        }
        if (message.length() >= 1) {
            e.setCancelled(true);
            try {
                //Getting Config
                Privatemessage.getInstance().getConfig();
                String tellrecieve = Privatemessage.getInstance().getConfig().getString("servertellrecieve");
                String tellsend = Privatemessage.getInstance().getConfig().getString("servertellsend");
                //Replacing Placeholders
                tellrecieve = tellrecieve.replaceAll("%server%", Privatemessage.getInstance().getConfig().getString("servername"));
                tellrecieve = tellrecieve.replaceAll("%message%", message);
                tellsend = tellsend.replaceAll("%server%", Privatemessage.getInstance().getConfig().getString("servername"));
                tellsend = tellsend.replaceAll("%message%", message);
                //Sending Messages
                if (message.length() > 3)
                    tellp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', tellrecieve));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', tellsend));
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + tellplayerName + "'");
                ex.printStackTrace();
            }
        }
    }
}
