package thomas15v;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class main extends JavaPlugin {
	
	File ConfigFile = null;
	FileConfiguration Config = null;
	
	
	
	@Override
	public void onEnable() {
		loadConfiguration();
		Bukkit.getLogger().info("[Tekkit Permitor] configuration loaded!");
		launchevents();
		Bukkit.getLogger().info("[Tekkit Permitor] events loaded!");
		loadWorldGuardsupport();
		Bukkit.getLogger().info("[Tekkit Permitor] loaded!");
	}
	
	void forgotenrecipes(){

		ShapedRecipe factorizationconsumer = new ShapedRecipe(new ItemStack(2855 ,1));
		factorizationconsumer.shape(new String[] {"G G"," F ","G G"});
		factorizationconsumer.setIngredient('G', Material.GOLD_INGOT);
		factorizationconsumer.setIngredient('F', new MaterialData(2050, (byte) 22));
		getServer().addRecipe(factorizationconsumer);
		getServer().addRecipe(factorizationconsumer);		

	}
	
	public void loadConfiguration(){
		File ConfigFile = new File(getDataFolder(), "Config.yml");
		

		if (ConfigFile.exists()){
			FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);
			getConfig().setDefaults(Config);
		}
		else{
			getLogger().info("[Tekkit permittor] ERROR no config file do /tep choicedefault <TM|TL|B>");
		}
		
		
		try {
			Config.save(ConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if (Config.getBoolean("Add_forgoten_recipe")) forgotenrecipes();	
	}
	
	public void launchevents(){
		events Events = new events();
		Events.noplaceblock = functions.StringToIntArray(getConfig().getString("block-Mod-block-place.blocks"));
		Events.Modblockplaceenabled = getConfig().getBoolean("block-Mod-block-place.enabled");
		
		Events.onePlayerBlocks = getConfig().getString("Block-moreplayer-using-block.blocks").split(",");
		Events.Blockmoreplayerusingblockenabled = getConfig().getBoolean("Block-moreplayer-using-block.enabled");
		
		Events.blockillegalexprewardenabled = getConfig().getBoolean("block-illegal-exp-reward.enabled");
		Events.illegalexprewardenabledblocks = getConfig().getString("block-illegal-exp-reward.blocks").split(",");
		Events.maxexp = getConfig().getInt("block-illegal-exp-reward.maxexp");
		
		getServer().getPluginManager().registerEvents(Events, this);
	}
	
	private void loadWorldGuardsupport() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        Bukkit.getLogger().info("[Tekkit Permitor] No worldguard plugin founded!!!");
	    }else{
	    	if (getConfig().getBoolean("Protection.enabled")){
	    		Worldguardevents worldguardevents = new Worldguardevents((WorldGuardPlugin) plugin);
		    	worldguardevents.wrenches = functions.StringToIntArray(getConfig().getString("Protection.wrenches"));
		    	worldguardevents.tools = functions.StringToIntArray(getConfig().getString("Protection.tools"));
		    	worldguardevents.alwaysblockedtools = functions.StringToIntArray(getConfig().getString("Protection.alwaysblockedtools"));
		    	worldguardevents.Containerblocks = functions.StringToIntArray(getConfig().getString("Protection.Containerblocks"));
		    	worldguardevents.UseBlocks = functions.StringToIntArray(getConfig().getString("Protection.UseBlocks"));
		    	getServer().getPluginManager().registerEvents(worldguardevents, this);
	    	}
	    	
	    	
	    }
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	Bukkit.getLogger().info("YEAH");
	return true;
	}
}
