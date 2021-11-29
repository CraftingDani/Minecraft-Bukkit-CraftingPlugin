package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.craftingdani.craftingplugin.main.Main;

public class GodCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.god"))
			{
				FileConfiguration config = Main.getPlugin().getConfig();
				
				switch (args.length)
				{
				//without target
				case 0:
					if(!config.getBoolean("God." + player)) //if player isn't already god
					{
						config.set("God." + player, true);
						player.sendMessage("§aU are a god now.");
						Main.getPlugin().saveConfig();
						player.setHealth(20);
					}
					else //if player is already god
					{
						config.set("God." + player, false);
						player.sendMessage("§aU are no god any more.");
						Main.getPlugin().saveConfig();
					}
					break;
				
				//target included
				case 1:
					Player target = Bukkit.getPlayer(args[0]);
					if(!config.getBoolean("God." + target)) //if target isn't already god
					{
						config.set("God." + target, true);
						target.sendMessage("§aU are a god now. Congrats!");
						player.sendMessage("§aU made " + target.getName() + " a god.");
						Main.getPlugin().saveConfig();
						target.setHealth(20);
					}
					else //if target is already god
					{
						config.set("God." + target, false);
						target.sendMessage("§aU are no god any more.");
						player.sendMessage("§aU made " + target.getName() + " no god any more");
						Main.getPlugin().saveConfig();
					}
					break;
				default:
					player.sendMessage("§cPlease use /god <player>!");
					break;
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