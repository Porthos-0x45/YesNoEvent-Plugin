package me.pixel.yesno.models;

import com.sun.tools.javac.main.Option;
import org.bukkit.Location;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static String chat(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static Material getBlock(Player p)
    {
        for (int i = 0; i < 3; i++)
        {
            if (p.getLocation().subtract(0, i, 0).getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)
            {
                return p.getLocation().subtract(0, i, 0).getBlock().getRelative(BlockFace.DOWN).getType();
            }
        }

        return Material.AIR;
    }

    public static ItemStack makeItem()
    {
        ItemStack item = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(Util.chat("&aPos 1 - LeftClick"));
        lore.add("");
        lore.add(Util.chat("&aPos 2 - RightClick"));
        meta.setDisplayName(Util.chat("&6Position set"));
        meta.setUnbreakable(true);
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isInRect(Player player, Location loc1, Location loc2)
    {
        double[] dim = new double[2];

        dim[0] = loc1.getX();
        dim[1] = loc2.getX();
        Arrays.sort(dim);
        if(player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0])
            return false;

        dim[0] = loc1.getZ();
        dim[1] = loc2.getZ();
        Arrays.sort(dim);

        if(player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0])
            return false;

        dim[0] = loc1.getY();
        dim[1] = loc2.getY();
        Arrays.sort(dim);

        if(player.getLocation().getY() > dim[1] || player.getLocation().getY() < dim[0])
            return false;

        return true;
    }

    public static long getSize(Location lowestPos, Location highestPos) {
        int xsize = highestPos.getBlockX() - lowestPos.getBlockX() + 1;
        int ysize = highestPos.getBlockY() - lowestPos.getBlockY() + 1;
        int zsize = highestPos.getBlockZ() - lowestPos.getBlockZ() + 1;
        return (xsize * ysize * zsize);
    }
}