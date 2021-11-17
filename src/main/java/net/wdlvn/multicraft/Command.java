package net.wdlvn.multicraft;

import net.wdlvn.IS.GuiMenu.MainGui;
import net.wdlvn.multicraft.Gui.CreateCraftGui;
import net.wdlvn.multicraft.Gui.MergeListGui;
import net.wdlvn.multicraft.Utilities.API;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("MultiCraft")){
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                if (strings.length == 1) {
                    if (strings[0].equalsIgnoreCase("admin")) {
                        if (p.hasPermission("multicraft.admin")) {
                            Creator creator = new Creator();
                            p.setMetadata("MultiCraft_Creator", new FixedMetadataValue(MultiCraft.getPlugin(), ""));
                            MultiCraft.stage.put(p, 1);
                            MultiCraft.creatorHashMap.put(p, creator);
                            CreateCraftGui.openInvetory(p);
                        }
                    }
                    else if (strings[0].equalsIgnoreCase("open")) {
                        if (p.hasPermission("multicraft.quickcommand")) {
                            CreateCraftGui.openInvetory(p);
                        }
                    }
                    else if (strings[0].equalsIgnoreCase("list")) {
                        if (p.hasPermission("multicraft.list")) {
                            MergeListGui.setUp(p);
                            MergeListGui.getGui(p,0);
                        }
                    }
                    else {
                        p.sendMessage(API.Color("&6&l---------------MultiCraft---------------"));
                        if (p.hasPermission("multicraft.quickcommand")) {
                            p.sendMessage(API.Color("&e&l/multicraft open: Craft item"));
                        }
                        if (p.hasPermission("multicraft.list")) {
                            p.sendMessage(API.Color("&e&l/multicraft list: View list of recipes"));
                        }
                        if (p.hasPermission("multicraft.admin")) {
                            p.sendMessage(API.Color("&e&l/multicraft admin: Create recipe"));
                        }
                        p.sendMessage(API.Color("&6&l----------------------------------------"));
                    }
                }
                else {
                    p.sendMessage(API.Color("&6&l---------------MultiCraft---------------"));
                    if (p.hasPermission("multicraft.quickcommand")) {
                        p.sendMessage(API.Color("&e&l/multicraft open: Craft item"));
                    }
                    if (p.hasPermission("multicraft.list")) {
                        p.sendMessage(API.Color("&e&l/multicraft list: View list of recipes"));
                    }
                    if (p.hasPermission("multicraft.admin")) {
                        p.sendMessage(API.Color("&e&l/multicraft admin: Create recipe"));
                    }
                    p.sendMessage(API.Color("&6&l----------------------------------------"));
                }
            }
        }
        return false;
    }
}
