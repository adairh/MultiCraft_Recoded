package net.wdlvn.multicraft.Listen;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.ItemSystem;
import net.wdlvn.IS.Util.NBT.NBTItem;
import net.wdlvn.multicraft.Creator;
import net.wdlvn.multicraft.Events.InventoryChangeEvent;
import net.wdlvn.multicraft.Gui.*;
import net.wdlvn.multicraft.MultiCraft;
import net.wdlvn.multicraft.Utilities.API;
import net.wdlvn.multicraft.Utilities.Chance;
import net.wdlvn.multicraft.Utilities.InvAPI;
import net.wdlvn.multicraft.Utilities.RecipeAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InvListen implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        int s = event.getSlot();
        List<Integer> except = Arrays.stream(IntStream.range(0, 27).toArray()).boxed().collect(Collectors.toList());
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        if (inv.getContents() != null){
            ItemStack[] cont = inv.getContents();
            if (MultiCraft.creatorHashMap.containsKey(player)) {
                if (inv.getTitle().equalsIgnoreCase(CreateCraftGui.TITLE)) {
                    if (!except.contains(event.getSlot())) {
                        event.setCancelled(true);
                        ItemStack[] putContent = InvAPI.getAPartContent(inv, 0, 27);
                        if (event.getSlot() == 40) {
                            if (player.hasMetadata("MultiCraft_Creator")) {
                                Creator creator = MultiCraft.creatorHashMap.get(player);
                                creator.setContents(putContent);
                                MultiCraft.stage.remove(player);
                                MultiCraft.stage.put(player, 2);
                                MultiCraft.creatorHashMap.remove(player);
                                MultiCraft.creatorHashMap.put(player, creator);
                                ListGui.setUp(player);
                                ListGui.getGui(player, 0);
                            }
                        }
                    }
                } else if (inv.getTitle().equals(ListGui.TITLE)) {
                    event.setCancelled(true);
                    if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                        int stage = ListGui.pi.get(player);
                        if (event.getSlot() == 53) {
                            if (stage >= 0 && stage < (ListGui.pis.get(player).size() - 1)) {
                                ListGui.getGui(player, 1);
                            }
                        } else if (event.getSlot() == 45) {
                            if (stage > 0 && stage < ListGui.pis.get(player).size()) {
                                ListGui.getGui(player, -1);
                            }
                        } else {
                            if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                                Creator creator = MultiCraft.creatorHashMap.get(player);
                                NBTItem nbti = new NBTItem(inv.getItem(s));
                                if (nbti.hasNBTData()) {
                                    if (nbti.hasKey("ItemSystem")) {
                                        String path = nbti.getString("ItemSystem");
                                        Item i = new Item(path);
                                        creator.setItem(i);
                                        MultiCraft.stage.remove(player);
                                        MultiCraft.stage.put(player, 3);
                                        MultiCraft.creatorHashMap.remove(player);
                                        MultiCraft.creatorHashMap.put(player, creator);
                                        Chance c = new Chance();
                                        MultiCraft.chance.put(player,c);
                                        ChanceGui.openInvetory(player);
                                    }
                                }
                            }
                        }
                    }
                } else if (inv.getTitle().equals(ChanceGui.TITLE)) {
                    event.setCancelled(true);
                    List<Integer> buttonSlot = Arrays.asList(12,13,14,
                                                             21,22,23,
                                                             30,31,32,
                                                             39,40,41,49);
                    if (buttonSlot.contains(s)){
                        Chance chance;
                        if (MultiCraft.chance.containsKey(player)) {
                            chance = MultiCraft.chance.get(player);
                        }
                        else {
                            chance = new Chance();
                        }
                        String c = String.valueOf(chance.getChance());
                        if (s != 49 && s != 41){
                            if (s != 39){
                                if (c.equals("0")){
                                    c = ChatColor.stripColor(inv.getItem(s).getItemMeta().getDisplayName());
                                } else {
                                    c = c + ChatColor.stripColor(inv.getItem(s).getItemMeta().getDisplayName());
                                }
                            }
                            else {
                                if (!c.contains(".")){
                                    c = c+".";
                                }
                            }
                            chance.setChance(c);
                            MultiCraft.chance.remove(player);
                            MultiCraft.chance.put(player,chance);
                            inv.setItem(4,ChanceGui.info(player));
                        }
                        else {
                            if (s == 41){
                                c = "0";
                                chance.setChance(c);
                                MultiCraft.chance.remove(player);
                                MultiCraft.chance.put(player,chance);
                                inv.setItem(4,ChanceGui.info(player));
                            }
                            else {
                                if (Double.parseDouble(MultiCraft.chance.get(player).getChance()) <= 100) {

                                    Creator creator = MultiCraft.creatorHashMap.get(player);
                                    creator.setChance(Double.parseDouble(MultiCraft.chance.get(player).getChance()));
                                    MultiCraft.stage.remove(player);
                                    MultiCraft.stage.put(player, 4);
                                    MultiCraft.creatorHashMap.remove(player);
                                    MultiCraft.creatorHashMap.put(player, creator);
                                    FailGui.openInvetory(player);
                                } else {
                                    player.sendMessage(ChatColor.RED + "Chance must not be larger than 100%");
                                }
                            }
                        }
                    }
                } else if (inv.getTitle().equals(FailGui.TITLE)) {
                    event.setCancelled(true);
                    if (s == 31){
                        if (inv.getItem(13).getType() != Material.AIR){
                            Creator creator = MultiCraft.creatorHashMap.get(player);
                            creator.setFailItem(inv.getItem(13));
                            MultiCraft.stage.remove(player);
                            MultiCraft.stage.put(player, 5);
                            MultiCraft.creatorHashMap.remove(player);
                            MultiCraft.creatorHashMap.put(player, creator);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN+"Type the permission for this Recipe. if dont want to set, type \"none\"");
                        }
                    }
                    else if (s == 13){
                        event.setCancelled(false);
                    }
                }
            }
            else {
                if (!player.hasMetadata("MultiCraft_Creator")) {
                    ItemStack[] putContent = InvAPI.getAPartContent(inv, 0, 27);
                    if (inv.getTitle().equalsIgnoreCase(CreateCraftGui.TITLE)) {
                        if (!except.contains(s)) {
                            event.setCancelled(true);
                        }
                        if (s==40){
                            for (Creator creator : Creator.getAllCreators()) {
                                if (RecipeAPI.match(putContent, creator.getContents())) {
                                    if (InvAPI.isEmpty(inv,s)){
                                        ItemStack is = creator.getItem().getItemStack();
                                        short d = is.getType().getMaxDurability();
                                        int c = d/100*(int)creator.getChance();
                                        is.setDurability((short)(d-c));

                                        if (!creator.getPermission().equals("none")) {
                                            if (!player.hasPermission(creator.getPermission())) {
                                                return;
                                            }
                                        }

                                        inv.setItem(40,is);


                                    }
                                    else {
                                        if (API.random(creator.getChance())) {
                                            if (InvAPI.isInvFull(player)){
                                                player.getWorld().dropItemNaturally(player.getLocation(),creator.getItem().getItemStack());
                                            }
                                            else {
                                                player.getInventory().addItem(creator.getItem().getItemStack());
                                            }
                                            for (int i:except){
                                                inv.setItem(i, new ItemStack((Material.AIR)));
                                            }
                                            player.closeInventory();
                                            break;
                                        } else {
                                            inv.setItem(event.getSlot(), creator.getFailItem());
                                            if (InvAPI.isInvFull(player)){
                                                player.getWorld().dropItemNaturally(player.getLocation(),creator.getFailItem());
                                            }
                                            else {
                                                player.getInventory().addItem(creator.getFailItem());
                                            }
                                            for (int i:except){
                                                inv.setItem(i, new ItemStack((Material.AIR)));
                                            }
                                            player.closeInventory();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else if (except.contains(s)){
                            inv.setItem(40,CreateCraftGui.result());
                        }
                    }
                    else if (inv.getTitle().equalsIgnoreCase(MergeListGui.TITLE)){

                        event.setCancelled(true);
                        if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                            int stage = MergeListGui.pi.get(player);
                            if (event.getSlot() == 53) {
                                if (stage >= 0 && stage < (MergeListGui.pis.get(player).size() - 1)) {
                                    MergeListGui.getGui(player, 1);
                                }
                            } else if (event.getSlot() == 45) {
                                if (stage > 0 && stage < MergeListGui.pis.get(player).size()) {
                                    MergeListGui.getGui(player, -1);
                                }
                            } else {
                                if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                                    RecipeInfo.openInvetory(player, RecipeAPI.getRecipe(inv.getItem(s)));
                                }
                            }
                        }
                    }
                    else if (inv.getTitle().startsWith(RecipeInfo.TITLE)){
                        event.setCancelled(true);
                    }
                }
            }
        }
    }


    /*@EventHandler
    public void onDrag(InventoryClickEvent event){
        Bukkit.broadcastMessage("gtgg");
        Inventory inv = event.getInventory();
        Player player = (Player)event.getWhoClicked();
        if (!player.hasMetadata("MultiCraft_Creator")) {
            Bukkit.broadcastMessage("aaa");
            InvAPI.updateInventory(player,inv);
            ItemStack[] putContent = InvAPI.getAPartContent(inv, 0, 27);
            if (inv.getTitle().equalsIgnoreCase(CreateCraftGui.TITLE)) {

                for (Creator creator : Creator.getAllCreators()) {
                    Bukkit.broadcastMessage(putContent.length+API.Color(" &6&lAND&r\n ")+creator.getContents().length);
                    if (RecipeAPI.match(putContent, creator.getContents())) {
                        Bukkit.broadcastMessage("ccc");
                        Bukkit.broadcastMessage(creator.getItem().getName());
                        if (!creator.getPermission().equals("none")) {
                            if (player.hasPermission(creator.getPermission())) {
                                inv.setItem(40, creator.getItem().getItemStack());
                            }
                        } else {
                            inv.setItem(40, creator.getItem().getItemStack());
                        }
                    }

                }

            }

        }
    }

*/


    @EventHandler
    public void onInventoryInteract(final InventoryClickEvent e)
    {
        if (!(e.getWhoClicked() instanceof Player))
        {
            return;
        }
        final Player p = (Player) e.getWhoClicked();
        final Inventory inv = e.getInventory();
        final ItemStack current = e.getCurrentItem();
        final ItemStack cursor = e.getCursor();

        if (current.getType().equals(Material.AIR))
        {
            final InventoryChangeEvent ev = new InventoryChangeEvent(p, InventoryChangeEvent.Mode.PUT, e.getClickedInventory());
            Bukkit.getServer().getPluginManager().callEvent(ev);
            return;
        } else if (!current.getType().equals(Material.AIR) && cursor.getType().equals(Material.AIR))
        {
            final InventoryChangeEvent ev = new InventoryChangeEvent(p, InventoryChangeEvent.Mode.TAKE, e.getClickedInventory());
            Bukkit.getServer().getPluginManager().callEvent(ev);
            return;
        } else if (!current.getType().equals(Material.AIR) && !cursor.getType().equals(Material.AIR))
        {
            final InventoryChangeEvent ev = new InventoryChangeEvent(p, InventoryChangeEvent.Mode.SWAP, e.getClickedInventory());
            Bukkit.getServer().getPluginManager().callEvent(ev);
            return;
        }
    }




    @EventHandler
    public void onClose (InventoryCloseEvent e){
        if (e.getInventory().getTitle().equals(CreateCraftGui.TITLE)){
            if (e.getPlayer().hasMetadata("MultiCraft_Creator")){
                e.getPlayer().removeMetadata("MultiCraft_Creator",MultiCraft.getPlugin());
            }
            List<Integer> except = Arrays.stream(IntStream.range(0, 27).toArray()).boxed().collect(Collectors.toList());
            for (int i:except){
                if (e.getInventory().getItem(i) != null) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), e.getInventory().getItem(i));
                }
            }
        }
    }
}
