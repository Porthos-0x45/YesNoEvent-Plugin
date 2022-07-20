package me.pixel.yesno.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixel.yesno.Main;
import me.pixel.yesno.models.Util;

public class YesNoCommand implements CommandExecutor
{
    private Main plugin;
    private String red, green;

    public YesNoCommand(Main plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("yesno").setExecutor(this);
        this.red = plugin.getConfig().getString("red");
        this.green = plugin.getConfig().getString("green");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        if (!(sender instanceof Player))
        {
            sender.sendMessage(Util.chat("&cYou have to been player to execute this command."));
            return true;
        }

        if (player.hasPermission("yesno.event"))
        {
            if (args.length == 0)
            {
                sender.sendMessage(Util.chat("&cOops, wrong command! Try one of these: \n"
                        + "&r&e/yesno help"));
                return true;
            }

            if (args[0].equalsIgnoreCase("green"))
            {
                Material gMat = Material.getMaterial(plugin.getConfig().getString("data.material.green"));
                Material cMat = Material.getMaterial(plugin.getConfig().getString("data.material.center"));

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    if (p.getWorld().getName().equals(plugin.getConfig().getString("world")))
                    {
                        Location lowestLoc = new Location(player.getWorld(), plugin.getConfig().getDouble("data.lowX"),
                                plugin.getConfig().getDouble("data.area.lowY"),
                                plugin.getConfig().getDouble("data.lowZ"));
                        Location highestLoc = new Location(player.getWorld(), plugin.getConfig().getDouble("data.area.highX"),
                                plugin.getConfig().getDouble("data.area.highY"),
                                plugin.getConfig().getDouble("data.area.highZ"));

                        double x = plugin.getConfig().getDouble("data.spawn.x"), y = plugin.getConfig().getDouble("data.spawn.y"),
                                z = plugin.getConfig().getDouble("data.spawn.z");

                        Bukkit.broadcastMessage(Util.chat(green));

                        if (Util.isInRect(p, lowestLoc, highestLoc))
                        {
                            if (Util.getBlock(p) == gMat || Util.getBlock(p) == cMat)
                            {
                                World world = p.getWorld();
                                p.teleport(new Location(world, x, y, z));
                            }
                        }
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("red"))
            {
                Material rMat = Material.getMaterial(plugin.getConfig().getString("data.material.red"));
                Material cMat = Material.getMaterial(plugin.getConfig().getString("data.material.center"));

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    if (p.getWorld().getName().equals(plugin.getConfig().getString("world")))
                    {
                        Location lowestLoc = new Location(p.getWorld(), plugin.getConfig().getDouble("data.area.lowX"),
                                plugin.getConfig().getDouble("data.area.lowY"),
                                plugin.getConfig().getDouble("data.lowZ"));
                        Location highestLoc = new Location(p.getWorld(), plugin.getConfig().getDouble("data.area.highX"),
                                plugin.getConfig().getDouble("data.area.highY"),
                                plugin.getConfig().getDouble("data.area.highZ"));
                        double x = plugin.getConfig().getDouble("data.spawn.x"), y = plugin.getConfig().getDouble("data.spawn.y"),
                                z = plugin.getConfig().getDouble("data.spawn.z");

                        Bukkit.broadcastMessage(Util.chat(red));

                        if (Util.isInRect(p, lowestLoc, highestLoc))
                        {
                            if (Util.getBlock(p) == Material.RED_TERRACOTTA || Util.getBlock(p) == Material.BROWN_CONCRETE)
                            {
                                World world = p.getWorld();
                                p.teleport(new Location(world, x, y, z));
                            }
                        }
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("center"))
            {
                Material cMat = Material.getMaterial(plugin.getConfig().getString("data.material.center"));
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    if (p.getWorld().getName().equals(plugin.getConfig().getString("world")))
                    {
                        Location lowestLoc = new Location(player.getWorld(), plugin.getConfig().getDouble("data.area.lowX"),
                                plugin.getConfig().getDouble("data.area.lowY"),
                                plugin.getConfig().getDouble("data.area.lowZ"));
                        Location highestLoc = new Location(player.getWorld(), plugin.getConfig().getDouble("data.area.highX"),
                                plugin.getConfig().getDouble("data.area.highY"),
                                plugin.getConfig().getDouble("data.area.highZ"));
                        double x = plugin.getConfig().getDouble("data.spawn.x"), y = plugin.getConfig().getDouble("data.spawn.y"),
                                z = plugin.getConfig().getDouble("data.spawn.z");
                        if (Util.isInRect(p, lowestLoc, highestLoc))
                        {
                            if (Util.getBlock(p) == Material.BROWN_CONCRETE)
                            {
                                World world = p.getWorld();
                                p.teleport(new Location(world, x, y, z));
                            }
                        }
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("pos1"))
            {
                Location loc = player.getLocation();
                plugin.getConfig().set("data.area.lowX", loc.getBlockX());
                plugin.getConfig().set("data.area.lowY", loc.getBlockY());
                plugin.getConfig().set("data.area.lowZ", loc.getBlockZ());
                plugin.saveConfig();
                sender.sendMessage(Util.chat("&aFirst position has been set!"));
                return true;
            }
            else if (args[0].equalsIgnoreCase("pos2"))
            {
                Location loc = player.getLocation();
                plugin.getConfig().set("data.area.highX", loc.getBlockX());
                plugin.getConfig().set("data.area.highY", loc.getBlockY());
                plugin.getConfig().set("data.area.highZ", loc.getBlockZ());
                plugin.saveConfig();
                sender.sendMessage(Util.chat("&aSecond position has been set!"));
                return true;
            }
            else if (args[0].equalsIgnoreCase("spawn"))
            {
                Location loc = player.getLocation();
                plugin.getConfig().set("data.spawn.x", loc.getX());
                plugin.getConfig().set("data.spawn.y", loc.getY());
                plugin.getConfig().set("data.spawn.z", loc.getZ());
                plugin.saveConfig();
                sender.sendMessage(Util.chat("&aSpawn has been successfully set."));
            }
            else if (args[0].equalsIgnoreCase("item"))
            {
                player.getInventory().addItem(Util.makeItem());
            }
            else if (args[0].equalsIgnoreCase("help"))
            {
                if (args.length == 1)
                {
                    sender.sendMessage("");
                    sender.sendMessage(Util.chat("&6&lHelp commands: &r\n"
                            + "&a> &r&e/yesno help\n"
                            + "&a> &r&e/yesno green  &r&7(when green is wrong)\n"
                            + "&a> &r&e/yesno red     &r&7(when red is wrong)\n"
                            + "&a> &r&e/yesno center &r&7(when center is wrong)\n"
                            + "&a> &r&e/yesno reload &r&7(reloads the config)\n"
                            + "&a> &r&6/yesno help 1-2"));
                    return true;
                }
                else if (args.length > 1)
                {
                    int page = Integer.parseInt(args[1]);

                    switch (page)
                    {
                        case 1:
                        {
                            sender.sendMessage("");
                            sender.sendMessage(Util.chat("&6&lHelp commands (Page 1): &r\n"
                                    + "&a> &r&e/yesno help\n"
                                    + "&a> &r&e/yesno green  &r&7(when green is wrong)\n"
                                    + "&a> &r&e/yesno red     &r&7(when red is wrong)\n"
                                    + "&a> &r&e/yesno center &r&7(when center is wrong)\n"
                                    + "&a> &r&e/yesno reload &r&7(reloads the config)"
                                    ));
                            return true;
                        }
                        case 2:
                        {
                            sender.sendMessage("");
                            sender.sendMessage(Util.chat("&6&lHelp commands (Page 2): &r\n"
                                    + "&a> &r&e/yesno pos1   &r&7(Manual setting of 1. position)\n"
                                    + "&a> &r&e/yesno pos2   &r&7(Manual setting of 2. position)\n"
                                    + "&a> &r&e/yesno spawn &r&7(Where the players should be teleported)\n"
                                    + "&a> &r&e/yesno item    &r&7(Gives you an axe to set the positions)"));
                            return true;
                        }
                        default:
                        {
                            sender.sendMessage("");
                            sender.sendMessage(Util.chat("&cNo such a page number."));
                            return true;
                        }
                    }
                }
                else
                {
                    sender.sendMessage(Util.chat("&cOops, wrong command try one of these: &r&e/yesno help"));
                }
            }
            else if (args[0].equalsIgnoreCase("reload"))
            {
                plugin.reloadConfig();
                sender.sendMessage(Util.chat("&6Config reloaded!"));
            }
        }
        return true;
    }
}