package de.craftingdani.craftingplugin.listeners;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import de.craftingdani.craftingplugin.main.Main;

public class EntityDeathListener implements Listener
{
	@EventHandler
	public void onEntityDeath(PlayerDeathEvent event)
	{
		Player killer = (Player) event.getEntity().getKiller(); //Player (killer/damager)
		Player killed = (Player) event.getEntity(); //Player (killed)
		FileConfiguration config = Main.config;
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
		
		if(killer instanceof Player && killed instanceof Player)
		{
			meta.setOwningPlayer(killed);
	        meta.setDisplayName("§a" + killed.getName());
	        lore.add(" ");
	        lore.add("killed by: " + killer.getName());
	        meta.setLore(lore);
	        skull.setItemMeta(meta);
	        killer.getInventory().addItem(skull);
	        
	        int money = config.getInt("Eco." + killed);  //half of killed's money
	        if(money < 10)
	        {
	        	config.set("Eco." + killed, (money / 2)); //remove the killed player's half money
		        config.set("Eco." + killer, config.getInt("Eco." + killer) + (money / 2)); //add money to the killer
		        System.out.println(killed.getName() + " lost " + money / 2 + ", " + killer.getName() + " got " + money / 2);
	        }
	        else
	        {
	        	config.set("Eco." + killed, (money - 10)); //remove the killed player's half money
		        config.set("Eco." + killer, config.getInt("Eco." + killer) + 10); //add money to the killer
		        System.out.println(killed.getName() + " lost 10, " + killer.getName() + " got 10");
	        }
		}
	}
}