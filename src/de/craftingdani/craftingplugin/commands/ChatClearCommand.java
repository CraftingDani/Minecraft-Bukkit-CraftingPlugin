package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingplugin.chatclear"))
			{
				if(args.length == 0)
				{
					for (int i = 0; i < 300; i++)
					{
						Bukkit.broadcastMessage(" ");
					}
					Bukkit.broadcastMessage("§aThe chat has been cleared by " + player.getName() + ".");
				}
				else
				{
					player.sendMessage("§cPlease use /" + label + "!");
				}
			}
			else 
			{
				player.sendMessage("§cU don't have the permission to do that!");
			}
		}
		else
		{
			sender.sendMessage("§cU can only use this command as a player!");
		}
		return false;
	}
}