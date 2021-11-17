package net.wdlvn.multicraft.Gui;

import net.wdlvn.multicraft.Creator;
import net.wdlvn.multicraft.Utilities.API;
import net.wdlvn.multicraft.Utilities.InvAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class RecipeInfo {

    public static String TITLE = API.Color("&6&lRecipes of &r");

    public static int rows = 6;

    public static int size = rows*9;


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


    private static ItemStack info(List<String> lore){
        ItemStack delete = API.IS(Material.PAPER,
                1,
                lore,
                API.Color("&4&lInfo"),
                Enchantment.ARROW_DAMAGE,
                2,
                ItemFlag.HIDE_ENCHANTS);
        return delete;
    }



    public static Inventory getInventory(Player player, Creator creator){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        List<Integer> except = Arrays.stream(IntStream.range(0, 27).toArray()).boxed().collect(Collectors.toList());

        List<String> lore = new ArrayList<String>();

        inv.setContents(creator.getContents());

        InvAPI.fillExcept(fill(),inv,except);
        InvAPI.fillBorders(3, 0, rows - 1, 9 - 1,border(), inv);

        lore.add(API.Color("&f- Chance: &e"+creator.getChance()+"%"));
        lore.add(API.Color("&a&l<---------Success"));
        lore.add(API.Color("&4&lFail--------->"));

        inv.setItem(40,info(lore));

        inv.setItem(39,creator.getItem().getItemStack());

        inv.setItem(41,creator.getFailItem());

        return inv;
    }

    public static void openInvetory(Player p, Creator creator){
        if (creator == null){
            return;
        }
        TITLE = TITLE + API.Color(creator.getItem().getName());
        p.openInventory(getInventory(p,creator));
    }



}
