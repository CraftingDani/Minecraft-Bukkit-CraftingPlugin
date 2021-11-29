package de.craftingdani.craftingplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener
{
	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent e)
	{
		e.setCancelled(true);
		Bukkit.broadcastMessage("§a" + e.getPlayer().getName() + ": §f" + e.getMessage());
	}
}