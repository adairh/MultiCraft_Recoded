package net.wdlvn.multicraft;

import net.wdlvn.IS.ItemSystem;
import net.wdlvn.multicraft.Listen.ChatListen;
import net.wdlvn.multicraft.Listen.InteractListen;
import net.wdlvn.multicraft.Listen.InvListen;
import net.wdlvn.multicraft.Listen.SignListen;
import net.wdlvn.multicraft.Utilities.Chance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class MultiCraft extends JavaPlugin {

	public static HashMap<Player,Creator> creatorHashMap = new HashMap<Player,Creator>();
	public static HashMap<Player,Integer> stage = new HashMap<Player,Integer>();
	public static HashMap<Player,Chance> chance = new HashMap<Player,Chance>();
	private static MultiCraft plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("multicraft").setExecutor(new Command());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatListen(), this);
		pm.registerEvents(new InvListen(), this);
		pm.registerEvents(new InteractListen(), this);
		pm.registerEvents(new SignListen(), this);
		File f = new File(ItemSystem.getIns().getDataFolder()+"/Recipes");
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			f.mkdir();
		}
	}

	public static MultiCraft getPlugin() {
		return plugin;
	}
}
