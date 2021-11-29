package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.craftingdani.craftingplugin.main.Main;

public class SpawnCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingPlugin.spawn"))
	        {
				FileConfiguration config = Main.getPlugin().getConfig();
				World world = Bukkit.getWorld(config.getString("Spawn.World"));
				double x = config.getDouble("Spawn.X");
				double y = config.getDouble("Spawn.Y");
				double z = config.getDouble("Spawn.Z");
				float yaw = (float) config.getDouble("Spawn.Yaw");
				float pitch = (float) config.getDouble("Spawn.Pitch");
				Location location = new Location(world, x, y, z, yaw, pitch);
				player.teleport(location);
				player.sendMessage("§aU habe been sent to the spawn.");
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