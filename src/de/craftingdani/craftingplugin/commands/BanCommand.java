package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.craftingdani.craftingplugin.main.Main;

public class BanCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.ban"))
			{
				if (args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[0]);
					FileConfiguration config = Main.getPlugin().getConfig();
					config.set("BannedPlayers." + target, true);
					target.kickPlayer("§cU are Banned from this Server. Reason: " + args[1]);
					Main.getPlugin().saveConfig();
				}
				else
				{
					player.sendMessage("§cPlease use /ban <player> <reason>!");
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