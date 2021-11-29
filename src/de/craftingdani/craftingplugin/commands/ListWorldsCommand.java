package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListWorldsCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingplugin.listworlds"))
			{
				player.sendMessage("§aWorlds:");
				String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
				int i = 0;
				for(World world : Bukkit.getWorlds())
				{
					worldNames[i] = world.getName();
					i++;
				}
				for(String worlds : worldNames)
				{
					player.sendMessage("- " + worlds);	
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