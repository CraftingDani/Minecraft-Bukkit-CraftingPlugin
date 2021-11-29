package de.craftingdani.craftingplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftingdani.craftingplugin.main.TPS_API;

public class TPSCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.hasPermission("craftingplugin.tps"))
			{
				if (args.length == 0)
				{
					player.sendMessage("§aTPS: §f" + TPS_API.getTPS());
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