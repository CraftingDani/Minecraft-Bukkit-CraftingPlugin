package de.craftingdani.craftingplugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import de.craftingdani.craftingplugin.main.Main;

public class DamageListener implements Listener
{
	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		FileConfiguration config = Main.getPlugin().getConfig();
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			if(config.getBoolean("God." + player))
			{
				e.setCancelled(true);
			}
		}
	}
}