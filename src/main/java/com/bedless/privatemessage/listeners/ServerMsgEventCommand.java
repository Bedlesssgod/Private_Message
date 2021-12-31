package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerMsgEventCommand implements Listener {
    @EventHandler
    public void onCommandSend(ServerCommandEvent e) {
        Bukkit.getConsoleSender().sendMessage(e.getCommand());
        if (e.getCommand().toLowerCase().contentEquals("msg")) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Incorrect Usage");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'msg player-name message'");
        }
        if (!e.getCommand().toLowerCase().startsWith("msg"))
            return;
        String msgraw = e.getCommand().replace("msg ", "");
        String[] msgrawS = msgraw.split(" ");
        String msgraw3 = msgraw.replace(msgrawS[0] + " ", "");
        String msgplayerName = msgrawS[0];
        String message = msgraw.replace(msgplayerName, "");
        Player msgp2 = Bukkit.getPlayerExact(msgplayerName);
        if (message.length() == 0) {
            e.setCancelled(true);
            //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
        }
        if (message.length() >= 1) {
            e.setCancelled(true);
            try {
                //Getting Config
                Privatemessage.getInstance().getConfig();
                String msgrecieve = Privatemessage.getInstance().getConfig().getString("servermsgrecieve");
                String msgsend = Privatemessage.getInstance().getConfig().getString("servermsgsend");
                //Replacing Placeholders
                msgrecieve = msgrecieve.replaceAll("%server%", Privatemessage.getInstance().getConfig().getString("servername"));
                msgrecieve = msgrecieve.replaceAll("%message%", message);
                msgsend = msgsend.replaceAll("%server%", Privatemessage.getInstance().getConfig().getString("servername"));
                msgsend = msgsend.replaceAll("%message%", message);
                //Sending Messages
                if (message.length() > 3)
                    msgp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msgrecieve));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msgsend));
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + msgplayerName + "'");
                ex.printStackTrace();
            }
        }
    }
}
