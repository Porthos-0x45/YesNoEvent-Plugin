package me.pixel.yesno;

import me.pixel.yesno.commands.YesNoCommand;
import me.pixel.yesno.listener.InteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        new YesNoCommand(this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
    }

    @Override
    public void onDisable()
    {
        saveConfig();
    }
}