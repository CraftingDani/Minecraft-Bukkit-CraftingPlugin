package de.craftingdani.craftingplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.craftingdani.craftingplugin.main.Main;

public class SetSpawnCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingPlugin.setspawn"))
	        {
				FileConfiguration config = Main.getPlugin().getConfig();
				config.set("Spawn.World", player.getWorld().getName());
				config.set("Spawn.X", player.getLocation().getBlockX());
				config.set("Spawn.Y", player.getLocation().getBlockY());
				config.set("Spawn.Z", player.getLocation().getBlockZ());
				config.set("Spawn.Yaw", player.getLocation().getYaw());
				config.set("Spawn.Pitch", player.getLocation().getPitch());
				Main.getPlugin().saveConfig();
				player.sendMessage("§aU set the new spawn to your location.");
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
