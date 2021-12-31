package com.bedless.privatemessage.listeners;

import com.bedless.privatemessage.Privatemessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerTellEventCommand implements Listener {

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("privmsg.tell")) {
            if (e.getMessage().toLowerCase().contentEquals("/tell")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "Incorrect Usage");
                e.getPlayer().sendMessage(ChatColor.RED + "Example Usage " + ChatColor.GRAY + "'/tell playername message'");
            }
            if (!e.getMessage().toLowerCase().startsWith("/tell"))
                return;
            String tellraw = e.getMessage().replace("/tell ", "");
            String[] tellrawS = tellraw.split(" ");
            String tellraw3 = tellraw.replace(tellrawS[0] + " ", "");
            String tellplayerName = tellrawS[0];
            String tellmessage = tellraw.replace(tellplayerName, "");
            Player tellp = e.getPlayer();
            Player tellp2 = Bukkit.getPlayerExact(tellplayerName);

            if (tellmessage.length() == 0) {
                e.setCancelled(true);
                //p.sendMessage(ChatColor.GRAY + "All messages will be transferred to the Private channel! with " + p2.getName());
            }
            if (tellmessage.length() >= 1) {
                e.setCancelled(true);
                try {
                    Privatemessage.getInstance().getConfig();
                    String tellrecieve = Privatemessage.getInstance().getConfig().getString("tellrecieve");
                    String tellsend = Privatemessage.getInstance().getConfig().getString("tellsend");
                    tellrecieve = tellrecieve.replaceAll("%player%", tellp2.getDisplayName());
                    tellrecieve = tellrecieve.replaceAll("%message%", tellmessage);
                    tellsend = tellsend.replaceAll("%player%", tellp2.getDisplayName());
                    tellsend = tellsend.replaceAll("%message%", tellmessage);
                    if (tellmessage.length() > 3)
                        tellp2.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', tellrecieve));
                    tellp.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', tellsend));
                } catch (NullPointerException ex) {
                    tellp.sendMessage(ChatColor.RED + "Couldn't find a player by the name of '" + tellplayerName + "'");
                    ex.printStackTrace();
                }

            }
        }
    }
}
