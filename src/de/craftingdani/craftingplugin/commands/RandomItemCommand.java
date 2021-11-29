package de.craftingdani.craftingplugin.commands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.craftingdani.craftingplugin.main.Main;

public class RandomItemCommand implements CommandExecutor
{	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			Random random = new Random();
			Material[] materials = Material.values();
			Material material = materials[random.nextInt(materials.length)];
			FileConfiguration config = Main.getPlugin().getConfig();
			boolean used = true;
			
			if(player.hasPermission("craftingPlugin.randomitem"))
	        {
				if(!config.getBoolean("UsedItems." + material))
				{
					player.sendMessage("§aRandom Item: §f" + material.name().toString().toLowerCase());
					config.set("UsedItems." + material, used);
					Main.getPlugin().saveConfig();
				}
				else
				{
					this.onCommand(sender, cmd, label, args);
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