package net.wdlvn.multicraft.Listen;

import net.wdlvn.multicraft.Creator;
import net.wdlvn.multicraft.MultiCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListen implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if (MultiCraft.stage.containsKey(p)){
            if (MultiCraft.stage.get(p)==5){
                Creator creator = MultiCraft.creatorHashMap.get(p);
                creator.setPermission(e.getMessage().toLowerCase());
                MultiCraft.stage.remove(p);
                MultiCraft.creatorHashMap.remove(p);
                creator.save();
                e.setCancelled(true);
                MultiCraft.chance.remove(p);
                p.sendMessage(ChatColor.GREEN + "Success!");
            }
        }
    }
}
