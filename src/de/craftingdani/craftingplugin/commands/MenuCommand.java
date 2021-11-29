package de.craftingdani.craftingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import de.craftingdani.craftingplugin.main.Main;

public class MenuCommand implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.hasPermission("craftingplugin.menu"))
			{
				Inventory inventory = Bukkit.createInventory(null, 9*3, "§aItem Menu");
				
				ItemStack redWool = Main.createItem(Material.RED_WOOL, "§cRED");
				inventory.setItem(11, redWool);
				
				ItemStack yellowWool = Main.createItem(Material.YELLOW_WOOL, "§eYELLOW");
				inventory.setItem(12, yellowWool);
				
				ItemStack greenWool = Main.createItem(Material.LIME_WOOL, "§aGREEN");
				inventory.setItem(13, greenWool);

				ItemStack blueWool = Main.createItem(Material.BLUE_WOOL, "§1BLUE");
				inventory.setItem(14, blueWool);
				
				ItemStack purpleWool = Main.createItem(Material.PURPLE_WOOL, "§5PURPLE");
				inventory.setItem(15, purpleWool);
				
				player.openInventory(inventory);
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