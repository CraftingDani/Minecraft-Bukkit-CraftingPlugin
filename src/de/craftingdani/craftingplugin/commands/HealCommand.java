package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingplugin.heal"))
			{
				if(args.length == 0)
				{
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage("§aU healed yourself");
				}
				
				else if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null)
					{
						target.setHealth(20);
						target.setFoodLevel(20);
						target.sendMessage("§aU have been healed by " + player.getName());
						player.sendMessage("§aU healed " + target.getName());
					}
				}
				else
				{
					player.sendMessage("§cPlease use /heal <player>!");
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