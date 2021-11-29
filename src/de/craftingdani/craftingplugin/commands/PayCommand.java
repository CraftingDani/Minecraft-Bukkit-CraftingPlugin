package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.craftingdani.craftingplugin.main.Main;

public class PayCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.pay"))
			{
				if (args.length == 2)
				{
					Player target;
					FileConfiguration config = Main.config;
					if(Bukkit.getPlayer(args[0]) instanceof Player)
					{
						if(config.getInt("Eco." + player) >= Integer.parseInt(args[1]) && config.getInt("Eco." + player) > 0)
						{
							target = Bukkit.getPlayer(args[0]);
							config.set("Eco." + target, config.getInt("Eco." + target) + Integer.parseInt(args[1])); //add the amount of money (target)
							config.set("Eco." + player, config.getInt("Eco." + player) - Integer.parseInt(args[1])); //remove the amount of money (player)
							Main.getPlugin().saveConfig();
							player.sendMessage("§aU gave " + target.getName() + " §f" + args[1] + "§a.");
							target.sendMessage("§a" + player.getName() + " gave u §f" + args[1] + "§a.");
						}
						else
						{
							player.sendMessage("§cU don't have enough money!");
						}
					}
					else
					{
						player.sendMessage("§cPlayer not found!");
					}
				}	
				else
				{
					player.sendMessage("§cPlease use /" + label + " <player> <value> !");
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