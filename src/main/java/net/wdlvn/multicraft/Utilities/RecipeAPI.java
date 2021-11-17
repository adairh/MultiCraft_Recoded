package net.wdlvn.multicraft.Utilities;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.NBT.NBTItem;
import net.wdlvn.multicraft.Creator;
import net.wdlvn.multicraft.MultiCraft;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeAPI {
    public static boolean match(ItemStack[] rec1, ItemStack[] rec2){
        return (Arrays.equals(rec1, rec2));
    }

    public static Creator getRecipe(ItemStack is){
        for (Item i:Item.getAllItem()){
            NBTItem nbti = new NBTItem(is);
            if (nbti.hasNBTData()) {
                if (nbti.hasKey("ItemSystem")) {
                    String path = nbti.getString("ItemSystem");
                    if (path.equals(i.getPath())){
                        for (Creator creator:Creator.getAllCreators()){
                            if (creator.getItem().getPath().equals(i.getPath())){
                                return creator;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    /*public static boolean match(ItemStack[] rec1, ItemStack[] rec2){
        boolean a = false;
        boolean b = false;
        for (int i = 0; i < rec1.length; i++){
            a = Arrays.asList(rec2).contains(rec1[i]);
            if (!Arrays.asList(rec2).contains(rec1[i])){
                return false;
            }
        }
        for (int i = 0; i < rec2.length; i++){
            b = Arrays.asList(rec1).contains(rec2[i]);
            if (!Arrays.asList(rec1).contains(rec2[i])){
                return false;
            }
        }
        if (a && b){
            return true;
        }
        return false;
    }
*/

}
