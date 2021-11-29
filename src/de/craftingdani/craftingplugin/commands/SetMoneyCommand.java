package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import de.craftingdani.craftingplugin.main.Main;

public class SetMoneyCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.setmoney"))
			{
				if (args.length == 2)
				{
					try
					{
						
						
						if(Integer.parseInt(args[1]) >= 0)
						{
							Player target = Bukkit.getPlayer(args[0]);
							FileConfiguration config = Main.config;
							config.set("Eco." + target, Integer.parseInt(args[1]));
							player.sendMessage("U set the amount of money for " + target.getName() + " to " + Integer.parseInt(args[1]));
							Main.getPlugin().saveConfig();
						}
						else
						{
							player.sendMessage("§cInvalid value!");
						}
					}
					catch (Exception e)
					{
						player.sendMessage("§cInvalid value!");
					}
				}
				else
				{
					player.sendMessage("§cPlease use /" + label + " <player> <value>!");
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