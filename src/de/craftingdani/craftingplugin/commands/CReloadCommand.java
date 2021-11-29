package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import de.craftingdani.craftingplugin.main.Main;

public class CReloadCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender.hasPermission("craftingplugin.reload"))
		{
			if (args.length == 0)
			{
				sender.sendMessage("§aReloading...");
				Main.getPlugin().saveConfig();
				Bukkit.reload();
				sender.sendMessage("§aReload Complete!");
			}
			else
			{
				sender.sendMessage("§cPlease use /" + label + "!");
			}
		}
		else
		{
			sender.sendMessage("§cU don't have the permission to do that!");
		}
		return false;
	}
}