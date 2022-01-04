package com.bedless.privatemessage;

import co.aikar.commands.PaperCommandManager;
import com.bedless.privatemessage.commands.ReloadConfigCommand;
import com.bedless.privatemessage.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public final class Privatemessage extends JavaPlugin {
    private static Privatemessage INSTANCE;
    public static Privatemessage getInstance() {
        return INSTANCE;
    }
    @Override
    public void onEnable() {
        INSTANCE = this;
        String version1 = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1];
        if(version1.equals(18)){
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Private Messages Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(line1);
        } else {
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Private Messages Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "This Plugin is not running on the intended Spigot Version!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Plugin may not behave as expected!");
            Bukkit.getConsoleSender().sendMessage(line1);
        }
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
        getServer().getPluginManager().registerEvents(new ServerTellEventCommand(), this);
        getServer().getPluginManager().registerEvents(new ServerWhisperEventCommand(), this);
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
