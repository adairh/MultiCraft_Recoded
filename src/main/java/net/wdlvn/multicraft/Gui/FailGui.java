package net.wdlvn.multicraft.Gui;

import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.InvAPI;
import net.wdlvn.multicraft.MultiCraft;
import net.wdlvn.multicraft.Utilities.HeadTexture;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;

public class FailGui {



    public static String TITLE = API.Color("&6&lFailGui");

    public static int rows = 4;

    public static int size = rows*9;


    private static ItemStack submit(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to comfirm the fail item")),
                API.Color("&a&lConfirm").toUpperCase(),
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

    public static Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        InvAPI.fillExcept(fill(),inv, Arrays.asList(13));
        InvAPI.fillBorders(0, 0, rows - 1, 9 - 1,border(), inv);


        inv.setItem(31,submit());

        return inv;
    }

    public static void openInvetory(Player p){
        p.openInventory(getInventory());
    }


}
