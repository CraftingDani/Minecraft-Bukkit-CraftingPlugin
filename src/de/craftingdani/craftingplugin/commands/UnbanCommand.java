package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.craftingdani.craftingplugin.main.Main;

public class UnbanCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.unban"))
			{
				if (args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					FileConfiguration config = Main.getPlugin().getConfig();
					if(config.getBoolean("BannedPlayers." + target))
					{
						config.set("BannedPlayers." + target, false);
						player.sendMessage("§cU unbanned " + target);
					}
					else
					{
						player.sendMessage("§cThis player is not banned from this server!");
					}
				}
				else
				{
					player.sendMessage("§cPlease use /unban <player>!");
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