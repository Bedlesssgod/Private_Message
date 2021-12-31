package com.bedless.privatemessage;

import co.aikar.commands.PaperCommandManager;
import com.bedless.privatemessage.commands.ReloadConfigCommand;
import com.bedless.privatemessage.listeners.PlayerMsgEventCommand;
import com.bedless.privatemessage.listeners.PlayerTellEventCommand;
import com.bedless.privatemessage.listeners.PlayerWhisperEventCommand;
import com.bedless.privatemessage.listeners.ServerMsgEventCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Privatemessage extends JavaPlugin {
    private static Privatemessage INSTANCE;
    public static Privatemessage getInstance() {
        return INSTANCE;
    }
    @Override
    public void onEnable() {
        INSTANCE = this;
        String line1 = ChatColor.GREEN + "===================";
        Bukkit.getConsoleSender().sendMessage(line1);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Private Messages Plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
        Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(line1);
        registerCommands();
        registerEvents();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void registerCommands(){
        PaperCommandManager cdm = new PaperCommandManager(this);
        cdm.registerCommand(new ReloadConfigCommand());
        //cdm.registerCommand(new MessageCommand());
    }
    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerTellEventCommand(), this);
        getServer().getPluginManager().registerEvents(new PlayerMsgEventCommand(), this);
        getServer().getPluginManager().registerEvents(new PlayerWhisperEventCommand(), this);
        getServer().getPluginManager().registerEvents(new ServerMsgEventCommand(), this);
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        String line2 = ChatColor.RED + "===================";
        Bukkit.getConsoleSender().sendMessage(line2);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Private Messages Plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabled");
        Bukkit.getConsoleSender().sendMessage(line2);
    }
}
