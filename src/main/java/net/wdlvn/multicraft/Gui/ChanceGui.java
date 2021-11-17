package net.wdlvn.multicraft.Gui;

import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.InvAPI;
import net.wdlvn.multicraft.MultiCraft;
import net.wdlvn.multicraft.Utilities.HeadTexture;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChanceGui {



    public static String TITLE = API.Color("&6&lType the chance");

    public static int rows = 6;

    public static int size = rows*9;

    static HashMap<String, String> hashMap = new HashMap<String, String>() {
        {
            put("0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGViZTdlNTIxNTE2OWE2OTlhY2M2Y2VmYTdiNzNmZGIxMDhkYjg3YmI2ZGFlMjg0OWZiZTI0NzE0YjI3In19fQ==");
            put("1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0=");
            put("2", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkOWVlZWU4ODM0Njg4ODFkODM4NDhhNDZiZjMwMTI0ODVjMjNmNzU3NTNiOGZiZTg0ODczNDE0MTk4NDcifX19");
            put("3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZWFlMTM5MzM4NjBhNmRmNWU4ZTk1NTY5M2I5NWE4YzNiMTVjMzZiOGI1ODc1MzJhYzA5OTZiYzM3ZTUifX19");
            put("4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlNzhmYjIyNDI0MjMyZGMyN2I4MWZiY2I0N2ZkMjRjMWFjZjc2MDk4NzUzZjJkOWMyODU5ODI4N2RiNSJ9fX0=");
            put("5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ1N2UzYmM4OGE2NTczMGUzMWExNGUzZjQxZTAzOGE1ZWNmMDg5MWE2YzI0MzY0M2I4ZTU0NzZhZTIifX19");
            put("6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0YjM2ZGU3ZDY3OWI4YmJjNzI1NDk5YWRhZWYyNGRjNTE4ZjVhZTIzZTcxNjk4MWUxZGNjNmIyNzIwYWIifX19");
            put("7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiNmViMjVkMWZhYWJlMzBjZjQ0NGRjNjMzYjU4MzI0NzVlMzgwOTZiN2UyNDAyYTNlYzQ3NmRkN2I5In19fQ==");
            put("8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxOTQ5NzNhM2YxN2JkYTk5NzhlZDYyNzMzODM5OTcyMjI3NzRiNDU0Mzg2YzgzMTljMDRmMWY0Zjc0YzJiNSJ9fX0=");
            put("9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Y2FmNzU5MWIzOGUxMjVhODAxN2Q1OGNmYzY0MzNiZmFmODRjZDQ5OWQ3OTRmNDFkMTBiZmYyZTViODQwIn19fQ==");
            put(".", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMzYWEyNDkxNmM4ODY5NmVlNzFkYjdhYzhjZDMwNmFkNzMwOTZiNWI2ZmZkODY4ZTFjMzg0YjFkNjJjZmIzYyJ9fX0=");
        }
    };

    private static ItemStack button(String buttonName){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(API.Color("&6&l"+buttonName));
        HeadTexture.buildSkull(meta,hashMap.get(buttonName));
        skull.setItemMeta(meta);
        return skull;
    }
    private static ItemStack submit(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to comfirm the chance")),
                API.Color("&a&lConfirm").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack clear(){
        ItemStack listOfItem = API.IS(Material.BARRIER,
                1,
                Arrays.asList(API.Color("&aClick here to comfirm the chance")),
                API.Color("&a&lClear").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    public static ItemStack info(Player player){
        ItemStack listOfItem = API.IS(Material.SIGN,
                1,
                Arrays.asList(API.Color("&aThe chance is "+MultiCraft.chance.get(player).getChance() +"%")),
                API.Color("&a&lChance").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack border(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)11,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    private static ItemStack fill(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)7,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    public static Inventory getInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        InvAPI.fillAll(fill(),inv);
        InvAPI.fillBorders(3, 0, rows - 1, 9 - 1,border(), inv);

        inv.setItem(4,info(player));

        inv.setItem(12,button("1"));
        inv.setItem(13,button("2"));
        inv.setItem(14,button("3"));

        inv.setItem(21,button("4"));
        inv.setItem(22,button("5"));
        inv.setItem(23,button("6"));

        inv.setItem(30,button("7"));
        inv.setItem(31,button("8"));
        inv.setItem(32,button("9"));

        inv.setItem(39,button("."));
        inv.setItem(40,button("0"));
        inv.setItem(41,clear());

        inv.setItem(49,submit());



        return inv;
    }

    public static void openInvetory(Player p){
        p.openInventory(getInventory(p));
    }


}
