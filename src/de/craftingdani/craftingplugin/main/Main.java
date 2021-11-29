package de.craftingdani.craftingplugin.main;

import java.text.DecimalFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import de.craftingdani.craftingplugin.commands.CreateWorldCommand;
import de.craftingdani.craftingplugin.commands.DeleteWorldCommand;
import de.craftingdani.craftingplugin.commands.FeedCommand;
import de.craftingdani.craftingplugin.commands.GodCommand;
import de.craftingdani.craftingplugin.commands.HealCommand;
import de.craftingdani.craftingplugin.commands.ListWorldsCommand;
import de.craftingdani.craftingplugin.commands.MenuCommand;
import de.craftingdani.craftingplugin.commands.PayCommand;
import de.craftingdani.craftingplugin.commands.RandomItemCommand;
import de.craftingdani.craftingplugin.commands.RandomNumberCommand;
import de.craftingdani.craftingplugin.commands.SetMoneyCommand;
import de.craftingdani.craftingplugin.commands.SetSpawnCommand;
import de.craftingdani.craftingplugin.commands.SpawnCommand;
import de.craftingdani.craftingplugin.commands.TPSCommand;
import de.craftingdani.craftingplugin.commands.UnbanCommand;
import de.craftingdani.craftingplugin.commands.BanCommand;
import de.craftingdani.craftingplugin.commands.CReloadCommand;
import de.craftingdani.craftingplugin.commands.ChatClearCommand;
import de.craftingdani.craftingplugin.listeners.BedEnterListener;
import de.craftingdani.craftingplugin.listeners.ChatListener;
import de.craftingdani.craftingplugin.listeners.DamageListener;
import de.craftingdani.craftingplugin.listeners.EntityDeathListener;
import de.craftingdani.craftingplugin.listeners.JoinListener;
import de.craftingdani.craftingplugin.listeners.QuitListener;
 
public class Main extends JavaPlugin
{
	private static Main plugin;
	public static Scoreboard scoreboardNone;
	public static Scoreboard scoreboard;
	public static FileConfiguration config;

	
   	
	public void onEnable()
	{
		System.out.println("CraftingPlugin started!");
		plugin = this;
		config = getConfig();
		register();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TPS_API(), 100L, 1L);
		updateScoreboard();
	}
	
	public void onDisable()
	{
		System.out.println("CraftingPlugin stopped...");
		saveConfig();
	}
	
	
	
	private void register()
	{
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("chatclear").setExecutor(new ChatClearCommand());
		getCommand("createworld").setExecutor(new CreateWorldCommand());
		getCommand("listworlds").setExecutor(new ListWorldsCommand());
		getCommand("deleteworld").setExecutor(new DeleteWorldCommand());
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("randomitem").setExecutor(new RandomItemCommand());
		getCommand("randomnumber").setExecutor(new RandomNumberCommand());
		getCommand("menu").setExecutor(new MenuCommand());
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
		getCommand("god").setExecutor(new GodCommand());
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("tps").setExecutor(new TPSCommand());
		getCommand("creload").setExecutor(new CReloadCommand());
		getCommand("setmoney").setExecutor(new SetMoneyCommand());
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new QuitListener(), this);
		pluginManager.registerEvents(new DamageListener(), this);
		pluginManager.registerEvents(new BedEnterListener(), this);
		pluginManager.registerEvents(new EntityDeathListener(), this);
		pluginManager.registerEvents(new ChatListener(), this);
	}
	
	public static Main getPlugin() //Main Singleton
	{
		return plugin;
	}	
	
	public static ItemStack createItem(Material material, String displayName) //Item Builder
	{
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);
		item.setItemMeta(itemMeta);
		return item;
	}
	
	public static void setScoreboard(Player player) //sets the scoreboard to a player
	{
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("something", "something", "something");
		objective.setDisplayName("§a§lSurvivalCraft");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.getScore(" ").setScore(6);
		objective.getScore("§aMoney:").setScore(5);
		objective.getScore("§f " + config.getInt("Eco." + player)).setScore(4);
		objective.getScore("  ").setScore(3);
		objective.getScore("§aTPS:").setScore(2);
		objective.getScore("§f " + getTPS()).setScore(1);
		objective.getScore("   ").setScore(0);
		
		player.setScoreboard(scoreboard);
	}
	
	public static void removeScoreboard(Player player) //removes the scoreboard from a player
	{
		scoreboardNone = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objectiveNone = scoreboardNone.registerNewObjective("nothing", "nothing", "nothing");
		objectiveNone.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	private void updateScoreboard() //updates the scoreboard for all players
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable()
		{
			public void run()
			{
				for(Player i : Bukkit.getOnlinePlayers())
				{
					setScoreboard(i);
				}
			}
		}, 0, 25);
	}
	
	public static String getTPS() //returns tps
	{
		if (TPS_API.getTPS() >= 20)
		{
			return "20";
		}
		DecimalFormat format = new DecimalFormat("##.#");
		return format.format(TPS_API.getTPS());
	}
}