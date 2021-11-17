package net.wdlvn.multicraft.Gui;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.InvAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateCraftGui {

    public static String TITLE = API.Color("&6&lMultiCraft");

    public static int rows = 6;

    public static int size = rows*9;


    private static ItemStack submit(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to create new Recipe")),
                API.Color("&a&lSubmit").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    public static ItemStack result(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)1,
                Arrays.asList(API.Color("&aGenerate")),
                API.Color("&a&lClick to generate the item").toUpperCase(),
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

    private static ItemStack empty(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)14,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    public static Inventory getInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        List<Integer> except = Arrays.stream(IntStream.range(0, 27).toArray()).boxed().collect(Collectors.toList());

       // InvAPI.fillOnly(fill(),inv,except);
        InvAPI.fillExcept(fill(),inv,except);
        InvAPI.fillBorders(3, 0, rows - 1, 9 - 1,border(), inv);
        if (player.hasMetadata("MultiCraft_Creator")) {
            inv.setItem(40, submit());
        }
        else {
            inv.setItem(40, result());
        }

        return inv;
    }
    public static void openInvetory(Player p){
        p.openInventory(getInventory(p));
    }



}
