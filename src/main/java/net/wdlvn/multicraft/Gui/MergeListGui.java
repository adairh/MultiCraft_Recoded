package net.wdlvn.multicraft.Gui;

import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.multicraft.Creator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MergeListGui {



    public static String TITLE = API.Color("&6&lRecipes list");

    public static int rows = 6;

    public static int size = rows*9;


    public static HashMap<Player,List<Inventory>> pis = new HashMap<Player,List<Inventory>>();


    public static HashMap<Player,Integer> pi = new HashMap<Player,Integer>();


    public static void setUp(Player p) {
        List<ItemStack> items = new ArrayList<ItemStack>();
        for (Item item : Item.getAllItem()){
            for (Creator creator:Creator.getAllCreators()) {
                if (creator.getItem().getPath().equals(item.getPath())) {
                    if (!creator.getPermission().equalsIgnoreCase("none")){
                        if (p.hasPermission(creator.getPermission())){
                            ItemStack is = item.getItemStack();
                            ItemMeta im = is.getItemMeta();
                            List<String> lore = im.getLore();
                            lore.add(API.Color("&6&l----------------------------"));
                            for (Attributes a : item.getAttr().keySet()) {
                                lore.add(API.Color("&a" + a.toString() + ":&e " + item.getAttrValue(a)));
                            }
                            lore.add(API.Color("&b&lLEFT CLICK TO VIEW"));
                            im.setLore(lore);
                            is.setItemMeta(im);
                            items.add(is);
                        }
                    } else {
                        ItemStack is = item.getItemStack();
                        ItemMeta im = is.getItemMeta();
                        List<String> lore = im.getLore();
                        lore.add(API.Color("&6&l----------------------------"));
                        for (Attributes a : item.getAttr().keySet()) {
                            lore.add(API.Color("&a" + a.toString() + ":&e " + item.getAttrValue(a)));
                        }
                        lore.add(API.Color("&b&lLEFT CLICK TO VIEW"));
                        im.setLore(lore);
                        is.setItemMeta(im);
                        items.add(is);
                    }
                }
            }
        }

        int invSize = 54;
        List<Inventory> inventories = new ArrayList<Inventory>();
        int neededInventories = ((int) items.size() / (invSize-9))+1;

        ItemStack fill = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)8);
        ItemMeta fillMeta = fill.getItemMeta();
        fillMeta.setDisplayName(ChatColor.GOLD + " ");
        fill.setItemMeta(fillMeta);

        for (int i = 0; i < neededInventories; i++) {
            Inventory inv = Bukkit.createInventory(null, invSize, TITLE /*+ (pi.get(p)+1)+"/"+pis.size()*/);

            for (int j = 0;j < inv.getSize();j++) {
                inv.setItem(j, fill);
            }

            int no = i;
            for (int j = 45*no;j < 45+45*no;j++) {
                if (items.size() > j) {
                    if ((j-45*no) < 45) {
                        inv.setItem(j-45*no, items.get(j));
                    }
                }
            }

            ItemStack previous = new ItemStack(Material.ARROW, 1);
            ItemMeta previousMeta = previous.getItemMeta();
            previousMeta.setDisplayName(ChatColor.GOLD + "<< Previous Page");
            previous.setItemMeta(previousMeta);



            ItemStack next = new ItemStack(Material.ARROW, 1);
            ItemMeta nextMeta = next.getItemMeta();
            nextMeta.setDisplayName(ChatColor.GOLD + "Next Page >>");
            next.setItemMeta(nextMeta);



            if (i != 0) {
                inv.setItem(45, previous);
            }
            if (i != neededInventories-1) {
                inv.setItem(53, next);
            }

            inventories.add(inv);
        }
        pis.put(p, inventories);
    }
    public static void getGui(Player p, int aa) {

        int stage = 0;
        if (pi.containsKey(p)) {
            stage = pi.get(p) + aa;
            pi.remove(p);
        }
        pi.put(p, stage);
        p.openInventory(pis.get(p).get(stage));
    }
}
