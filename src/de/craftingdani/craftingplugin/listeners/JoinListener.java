package de.craftingdani.craftingplugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import de.craftingdani.craftingplugin.main.Main;

public class JoinListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		FileConfiguration config = Main.getPlugin().getConfig();
		
		event.setJoinMessage("§a" + player.getName() + " joined!");

		if(config.getBoolean("BannedPlayers." + player))
		{
			player.kickPlayer("§cU are Banned from this Server.");
		}
	}
}