package net.wdlvn.multicraft.Utilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InvAPI {
    public static void fillBorders(int fromRow, int fromColumn, int toRow, int toColumn, ItemStack item, Inventory inv) {
        for (int row = fromRow; row <= toRow; row++) {
            for (int column = fromColumn; column <= toColumn; column++) {
                if (row != fromRow && row != toRow && column != fromColumn && column != toColumn)
                    continue;
                inv.setItem(9 * row + column, item);
            }
        }
    }

    public static void fillAll(ItemStack is, Inventory inv){
        for (int i = 0; i < inv.getSize(); i++){
            inv.setItem(i, is);
        }
    }

    public static void fillExcept(ItemStack is, Inventory inv, List<Integer> slots){
        for (int i = 0; i < inv.getSize(); i++){
            if (!slots.contains(i)) {
                inv.setItem(i, is);
            }
        }
    }

    public static void fillOnly(ItemStack is, Inventory inv, List<Integer> slots){
        for (int i = 0; i < inv.getSize(); i++){
            if (slots.contains(i)) {
                inv.setItem(i, is);
            }
        }
    }

    public static boolean isEmpty(Inventory inv, int s){
        if (inv.getItem(s) == null) return true;
        return inv.getItem(s).getType()==Material.STAINED_GLASS_PANE;
    }


    public static boolean isInvFull(Player p){
        boolean a = false;
        for (ItemStack is:p.getInventory().getContents()){
            if (is == null){
                a = true;
                break;
            }
        }
        return a;
    }

    public static void updateInventory(Player p, Inventory i)
    {
        ItemStack[] contents = i.getContents();
        i.clear();

        i.setContents(contents);
    }

    public static ItemStack[] getAPartContent(Inventory inv, int from, int to){
        ItemStack[] realContent = inv.getContents();
        List<ItemStack> stackList = new ArrayList<ItemStack>();
        int realTo = to;
        int realFrom = from;
        if (from < 0){
            realFrom = 0;
        }
        if (to > realContent.length){
            realTo = realContent.length;
        }
        for (int i = realFrom; i<realTo; i++){
            if (realContent[i] == null) {
                stackList.add(new ItemStack(Material.AIR));
            }
            else {
                stackList.add(realContent[i]);
            }
        }
        ItemStack[] partContent = new ItemStack[stackList.size()];
        stackList.toArray(partContent);

        return partContent;
    }
}
