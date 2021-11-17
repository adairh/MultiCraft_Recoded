package net.wdlvn.multicraft.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class API {

     public static double toPositiveNumber(double d){
         if (d > 0) return d; else return 0;
     }
     public static ItemStack CopyIS(ItemStack is, Integer i) {
        ItemStack items = new ItemStack(is.getType(),i);
        items.setItemMeta(is.getItemMeta());
        return items;
    }
    public static ItemStack IS(Material m, int i, List<String> l, String name, Enchantment en, int enlv) {
        try {
            ItemStack is = new ItemStack(m,i);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m, int i, List<String> l,String name) {
        try {
            ItemStack is = new ItemStack(m,i);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m, int i, byte d, List<String> l,String name,Enchantment en,int enlv,ItemFlag ifs) {
        try {
            ItemStack is = new ItemStack(m,i,d);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            im.addItemFlags(ifs);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m, int i, byte d, List<String> l,String name,Enchantment en,int enlv,List<ItemFlag> ifs) {
        try {
            ItemStack is = new ItemStack(m,i,d);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            for (ItemFlag itfs:ifs) {
                im.addItemFlags(itfs);
            }
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m, int i, List<String> l,String name,Enchantment en,int enlv,ItemFlag ifs) {
        try {
            ItemStack is = new ItemStack(m,i);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            im.addItemFlags(ifs);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m, int i, List<String> l,String name,Enchantment en,int enlv,List<ItemFlag> ifs) {
        try {
            ItemStack is = new ItemStack(m,i);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            for (ItemFlag itfs:ifs) {
                im.addItemFlags(itfs);
            }
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m,byte d, int i, List<String> l,String name,Enchantment en,int enlv) {
        try {
            ItemStack is = new ItemStack(m,i,d);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            im.addEnchant(en, enlv, true);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static ItemStack IS(Material m,byte d, int i, List<String> l,String name) {
        try {
            ItemStack is = new ItemStack(m,i,d);
            ItemMeta im = is.getItemMeta();
            im.setLore(l);
            im.setDisplayName(name);
            is.setItemMeta(im);
            return is;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static String Color(String str) {
        try {
            String s = ChatColor.translateAlternateColorCodes('&', str);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean compare(ItemStack stack,ItemStack stack1) {
        if (stack == null) {
            return false;
        } else if (stack == stack1) {
            return true;
        } else {
            return stack1.getTypeId() == stack.getTypeId() && stack1.hasItemMeta() == stack.hasItemMeta() && (!stack1.hasItemMeta() || Bukkit.getItemFactory().equals(stack1.getItemMeta(), stack.getItemMeta()));
        }
    }

    public static boolean isInteger(String paramString)
    {
        try
        {
            Integer.parseInt(paramString);
            return true;
        }
        catch (Exception localException) {}
        return false;
    }

    public static boolean isDouble(String paramString)
    {
        try
        {
            Double.parseDouble(paramString);
            return true;
        }
        catch (Exception localException) {}
        return false;
    }

    public static boolean random(double rate)
    {
        int i = new Random().nextInt(10000);
        boolean fire = false;
        if (i < rate * 100.0D) {
            fire = true;
        }
        return fire;
    }
    public static boolean isWeapon(Material m) {
        if ((
                (m == Material.DIAMOND_AXE)
                        ||
                        (m == Material.DIAMOND_SWORD)
                        ||
                        (m == Material.DIAMOND_PICKAXE)
                        ||
                        (m == Material.DIAMOND_HOE)
                        ||
                        (m == Material.DIAMOND_SPADE)
                        ||
                        (m == Material.GOLD_AXE)
                        ||
                        (m == Material.GOLD_SWORD)
                        ||
                        (m == Material.GOLD_PICKAXE)
                        ||
                        (m == Material.GOLD_HOE)
                        ||
                        (m == Material.GOLD_SPADE)
                        ||
                        (m == Material.IRON_AXE)
                        ||
                        (m == Material.IRON_SWORD)
                        ||
                        (m == Material.IRON_PICKAXE)
                        ||
                        (m == Material.IRON_HOE)
                        ||
                        (m == Material.IRON_SPADE)
                        ||
                        (m == Material.STONE_AXE)
                        ||
                        (m == Material.STONE_SWORD)
                        ||
                        (m == Material.STONE_PICKAXE)
                        ||
                        (m == Material.STONE_HOE)
                        ||
                        (m == Material.STONE_SPADE)
                        ||
                        (m == Material.WOOD_AXE)
                        ||
                        (m == Material.WOOD_SWORD)
                        ||
                        (m == Material.WOOD_PICKAXE)
                        ||
                        (m == Material.WOOD_HOE)
                        ||
                        (m == Material.WOOD_SPADE)
                        ||
                        (m == Material.FISHING_ROD)
                        ||
                        (m == Material.BOW)


        )&& m != Material.AIR) {
            return true;
        }
        else { return false;}
    }
    public static boolean isArmor(Material m) {

        if(((m == Material.CHAINMAIL_CHESTPLATE)
                ||
                (m == Material.DIAMOND_CHESTPLATE)
                ||
                (m == Material.GOLD_CHESTPLATE)
                ||
                (m == Material.IRON_CHESTPLATE)
                ||
                (m == Material.LEATHER_CHESTPLATE)
                ||
                (m == Material.CHAINMAIL_HELMET)
                ||
                (m == Material.DIAMOND_HELMET)
                ||
                (m == Material.GOLD_HELMET)
                ||
                (m == Material.IRON_HELMET)
                ||
                (m == Material.LEATHER_HELMET)
                ||
                (m == Material.CHAINMAIL_LEGGINGS)
                ||
                (m == Material.DIAMOND_LEGGINGS)
                ||
                (m == Material.GOLD_LEGGINGS)
                ||
                (m == Material.IRON_LEGGINGS)
                ||
                (m == Material.LEATHER_LEGGINGS)
                ||
                (m == Material.CHAINMAIL_BOOTS)
                ||
                (m == Material.DIAMOND_BOOTS)
                ||
                (m == Material.GOLD_BOOTS)
                ||
                (m == Material.IRON_BOOTS)
                ||
                (m == Material.LEATHER_BOOTS)
                ||
                (m == Material.SHIELD)
                ||
                (m == Material.ELYTRA)

        ) &&
                m != Material.AIR
                ){
            return true;
        }
        else {
            return false;
        }
    }
}
