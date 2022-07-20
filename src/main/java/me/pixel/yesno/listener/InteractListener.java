package me.pixel.yesno.listener;

import me.pixel.yesno.Main;
import me.pixel.yesno.models.Util;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class InteractListener implements Listener
{
    private Main plugin;

    public InteractListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player p = event.getPlayer();

        if (p.hasPermission("yesno.event"))
        {
            if (event.getItem() != null && event.getItem().getItemMeta().getLore() != null && event.getItem().getItemMeta().getDisplayName().equals(Util.chat("&6Position set")))
            {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    Location bLoc = event.getClickedBlock().getLocation();
                    plugin.getConfig().set("data.area.highX", bLoc.getBlockX());
                    plugin.getConfig().set("data.area.highY", bLoc.getBlockY());
                    plugin.getConfig().set("data.area.highZ", bLoc.getBlockZ());
                    plugin.saveConfig();
                    p.sendMessage(Util.chat("&aSecond position has been set!"));
                }
            }
        }
    }

    @EventHandler
    public void onBreakEvent(BlockBreakEvent event)
    {
        Player p = event.getPlayer();
        Location bLoc = event.getBlock().getLocation();

        if (p.hasPermission("yesno.event"))
        {
            if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getItemMeta() != null)
            {
                if (p.getInventory().getItemInMainHand().getItemMeta().getLore() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Util.chat("&6Position set")))
                {
                    plugin.getConfig().set("data.area.lowX", bLoc.getBlockX());
                    plugin.getConfig().set("data.area.lowY", bLoc.getBlockY());
                    plugin.getConfig().set("data.area.lowZ", bLoc.getBlockZ());
                    plugin.saveConfig();
                    p.sendMessage(Util.chat("&aFirst position has been set!"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
