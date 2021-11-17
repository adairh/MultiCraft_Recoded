package net.wdlvn.multicraft.Listen;

import net.wdlvn.IS.Util.NBT.utils.MinecraftVersion;
import net.wdlvn.multicraft.Gui.CreateCraftGui;
import net.wdlvn.multicraft.MultiCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListen implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) &&
                (!event.getPlayer().isSneaking()) &&
                (multiTable(event.getClickedBlock())))
        {
            event.setCancelled(true);
            CreateCraftGui.openInvetory(event.getPlayer());
        }
    }

    public boolean multiTable(Block b)
    {
        if (b.getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
            return false;
        }
        for (BlockFace f : new BlockFace[] { BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH, BlockFace.EAST })
        {
            Block signP = b.getRelative(BlockFace.DOWN).getRelative(f);
            if ((signP.getType().equals(Material.SIGN)) ||
                    (signP.getType().equals(Material.SIGN_POST)) || (signP.getType().equals(Material.WALL_SIGN)))
            {
                org.bukkit.block.Sign s = (org.bukkit.block.Sign)b.getRelative(BlockFace.DOWN).getRelative(f).getState();
                if (s.getLine(1).equalsIgnoreCase( ChatColor.GOLD + "[MultiCraft]")) {
                    return true;
                }
            }
        }
        return false;
    }



}
