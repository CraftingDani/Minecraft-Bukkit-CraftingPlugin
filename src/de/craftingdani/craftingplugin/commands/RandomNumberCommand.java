package de.craftingdani.craftingplugin.commands;

import java.util.Random;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomNumberCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingplugin.randomnumber"))
			{
				if(args.length == 2)
				{
					int min = Integer.parseInt(args[0]);
					int max = Integer.parseInt(args[1]);
					
					if(min <= max)
					{
						Random random = new Random();
						int randomInt = random.nextInt((max - min) + 1) + min;
						player.sendMessage("§aRandom Number within " + min + " and " + max + ": §f" + randomInt);
					}
					else
					{
						player.sendMessage("§cThe minimum value can't be higher than the max!");
					}
				}
				else
				{
					player.sendMessage("§cPlease use /randomitem <minimum> <maximum>!");
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