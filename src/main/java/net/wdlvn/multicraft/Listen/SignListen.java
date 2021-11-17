package net.wdlvn.multicraft.Listen;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListen implements Listener {
    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event)
    {
        if (event.getLine(0).equalsIgnoreCase("multicraft"))
        {
            org.bukkit.material.Sign s = (org.bukkit.material.Sign)event.getBlock().getState().getData();
            if (event.getBlock().getRelative(s.getFacing().getOppositeFace()).getRelative(BlockFace.UP).getType().equals(Material.AIR))
            {
                event.getPlayer().sendMessage("There must be a workbench above the sign!");
                event.setCancelled(true);
                return;
            }
            if (event.getPlayer().hasPermission("multicraft.admin"))
            {
                event.setLine(1, ChatColor.GOLD + "[MultiCraft]");
                event.setLine(0, "");
                event.getPlayer().sendMessage(ChatColor.GREEN + "Successfully created Mutli-Craft Table!");
            }
            else
            {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to create an multi-craft table");
            }
        }
    }
}
