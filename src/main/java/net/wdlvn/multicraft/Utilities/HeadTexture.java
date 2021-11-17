package net.wdlvn.multicraft.Utilities;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.properties.Property;

public class HeadTexture {
	
	public static void buildSkull(SkullMeta meta, String texture)
	  {
		if (texture != null)
	    {
	      GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	      
	      Field profileField = null;
	      profile.getProperties().put("textures", new Property("textures", texture));
	      try
	      {
	        profileField = meta.getClass().getDeclaredField("profile");
	        profileField.setAccessible(true);
	        profileField.set(meta, profile);
	      }
	      catch (NoSuchFieldException|SecurityException | IllegalArgumentException | IllegalAccessException e)
	      {
	        e.printStackTrace();
	      }
	     
	      
	    }
	  }
}
