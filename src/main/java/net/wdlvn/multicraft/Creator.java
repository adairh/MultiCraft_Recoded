package net.wdlvn.multicraft;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.ItemSystem;
import net.wdlvn.multicraft.Utilities.EnglishNumberToWords;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Creator {
    private ItemStack[] contents;
    private Item item;
    private String permission;
    private double chance;
    private ItemStack failItem;

    public Creator (){

    }
    public Creator (ItemStack[] contents, Item item, String permission, double chance, ItemStack failItem){
        this.contents = contents;
        this.item = item;
        this.permission = permission;
        this.chance = chance;
        this.failItem = failItem;
    }

    public Creator (String name){
        File file = new File(MultiCraft.getPlugin().getDataFolder()+"/Recipes",name+".yml");
        YamlConfiguration f = YamlConfiguration.loadConfiguration(file);

        this.chance = (f.getDouble(name+".Chance"));
        this.permission = (f.getString(name+".Permission"));
        this.item = (new Item(name));
        List<ItemStack> list = new ArrayList<ItemStack>();
        ItemStack[] is = new ItemStack[f.getConfigurationSection(name+".Contents").getKeys(false).size()];
        for (String s:f.getConfigurationSection(name+".Contents").getKeys(false)){
            list.add(f.getItemStack(name+".Contents."+s));
        }
        this.contents = (list.toArray(is));
        this.failItem = (f.getItemStack(name+".FailItem"));
    }


    public Item getItem() {
        return item;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public String getPermission() {
        return permission;
    }

    public double getChance() {
        return chance;
    }

    public ItemStack getFailItem() {
        return failItem;
    }

    public void setFailItem(ItemStack failItem) {
        this.failItem = failItem;
    }

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void save(){
        String path = this.item.getPath();

        File f = new File(MultiCraft.getPlugin().getDataFolder()+"/Recipes",path+".yml");
        if (!f.exists()){
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();

                YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

                file.save(f);

                file.addDefault(path+".Chance",this.chance);
                file.addDefault(path+".Permission",this.permission);
                for (int i = 0;i<this.contents.length;i++) {
                    file.addDefault(path + ".Contents."+(
                            EnglishNumberToWords.convert(i).replace(" ","_")
                    ), this.contents[i]);
                }
                file.addDefault(path+".FailItem",this.failItem);

                file.options().copyDefaults(true);
                file.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Creator> getAllCreators(){
        File f = new File(MultiCraft.getPlugin().getDataFolder()+"/Recipes");
        File[] listItem = f.listFiles();
        List<Creator> items = new ArrayList<Creator>();
        if (f.exists()) {
            for (int i = 0; i < listItem.length; i++) {
                File item = listItem[i];
                String name = item.getName().replace(".yml","");
                items.add(new Creator(name));
            }
        }
        return items;
    }
}
