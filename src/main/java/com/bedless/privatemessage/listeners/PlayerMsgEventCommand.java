package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerMsgEventCommand implements Listener {
    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("privmsg.msg")) {
            if (e.getMessage().toLowerCase().contentEquals("/msg")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "Incorrect Usage");
                e.getPlayer().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'/msg playername message'");
            }
            if (!e.getMessage().toLowerCase().startsWith("/msg"))
                return;
            String msgraw = e.getMessage().replace("/msg ", "");
            String[] msgrawS = msgraw.split(" ");
            String msgraw3 = msgraw.replace(msgrawS[0] + " ", "");
            String msgplayerName = msgrawS[0];
            String message = msgraw.replace(msgplayerName, "");
            Player msgp = e.getPlayer();
            Player msgp2 = Bukkit.getPlayerExact(msgplayerName);
            if (message.length() == 0) {
                e.setCancelled(true);
                //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
            }
            if (message.length() >= 1) {
                e.setCancelled(true);
                try {
                    Privatemessage.getInstance().getConfig();
                    String msgrecieve = Privatemessage.getInstance().getConfig().getString("playermsgrecieve");
                    String msgsend = Privatemessage.getInstance().getConfig().getString("playermsgsend");
                    ;
                    msgrecieve = msgrecieve.replaceAll("%player%", msgp2.getDisplayName());
                    msgrecieve = msgrecieve.replaceAll("%message%", message);
                    msgsend = msgsend.replaceAll("%player%", msgp2.getDisplayName());
                    msgsend = msgsend.replaceAll("%message%", message);
                    if (message.length() > 3)
                        msgp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msgrecieve));
                    msgp.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msgsend));
                } catch (NullPointerException ex) {
                    msgp.sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + msgplayerName + "'");
                    ex.printStackTrace();
                }
            }
        }
    }
}
