package de.craftingdani.craftingplugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import de.craftingdani.craftingplugin.main.Main;

public class BedEnterListener implements Listener
{
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event)
	{
		FileConfiguration config = Main.getPlugin().getConfig();
		if(config.getBoolean("InstaBed.use"))
		{
			event.getPlayer().getWorld().setTime(1000);
		}
	}
}