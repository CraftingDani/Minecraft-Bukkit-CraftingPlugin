package de.craftingdani.craftingplugin.commands;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWorldCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(args.length == 1)
			{
		        if(player.hasPermission("craftingPlugin.deleteWorld"))
		        {
		            if(Bukkit.getWorld(args[0]) instanceof World)
		            {
		            	World delete = Bukkit.getWorld(args[0]);
		            	File deleteFolder = delete.getWorldFolder(); 
		            	player.sendMessage("§aDeleting world §f" + delete.getName() + "§a...");
		            	deleteWorld(deleteFolder);
		            	Bukkit.reload();
		            	player.sendMessage("World §f" + delete.getName() + " §adeleted.");
		            }
		    		else
		    		{
		    			player.sendMessage("§cThis world has not been found!");
		    		}
		        }
		        else 
		        {
		    		player.sendMessage("§cU don't have the permission to do that!");
		    	}
			}
			else
			{
				player.sendMessage("§cPlease use /" + label + " <world name>!");
			}
		}
		else
		{
			sender.sendMessage("§cU can only use this command as a player!");
		}
        return false;
    }
    
	public void deleteWorld(File path)
	{
		
	    if(path.exists())
	    {
	        File files[] = path.listFiles();
	        for(int i=0; i < files.length; i++)
	        {
	            if(files[i].isDirectory())
	            {
	                deleteWorld(files[i]);
	            }
	            else
	            {
	                files[i].delete();
	            }
	        }
	    }
	}
}