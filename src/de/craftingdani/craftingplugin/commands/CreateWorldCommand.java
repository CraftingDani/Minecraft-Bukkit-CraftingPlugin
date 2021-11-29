package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWorldCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;

			if(player.hasPermission("craftingplugin.createworld"))
			{
				if(args.length == 2)
				{
					switch(args[1])
					{
						case "flat":
							player.sendMessage("§aCreating new world §o" + args[0] + "...");
							Bukkit.createWorld(WorldCreator.name(args[0]).type(WorldType.FLAT).generateStructures(false));	
							Bukkit.reload();
							player.sendMessage("§aFinished!");
							break;
							
						case "normal":
							player.sendMessage("§aCreating new world §o" + args[0] + "...");
							Bukkit.createWorld(WorldCreator.name(args[0]).type(WorldType.NORMAL).generateStructures(true));
							Bukkit.reload();
							player.sendMessage("§aFinished!");
							break;
							
						default:
							player.sendMessage("§cPlease use /" + label + " <name> <flat/normal>!");
					}
				}
				else
				{
					player.sendMessage("§cPlease use /" + label + " <name> <flat/normal>!");
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