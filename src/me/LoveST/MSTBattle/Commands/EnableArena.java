package me.LoveST.MSTBattle.Commands;

import me.LoveST.MSTBattle.MSTBattle;

import org.bukkit.ChatColor;

public class EnableArena {

	public static MSTBattle plugin ;
	public EnableArena(MSTBattle instance) {
		plugin = instance;
	}
	
	public String ArenaEnable(String arena){
		
		
		if(plugin.getConfig().contains(arena)){
			if(plugin.getConfig().contains(arena + ".status")){
				plugin.getConfig().set(arena+ ".status", null);
				plugin.getConfig().addDefault(arena + ".status" , "enabled");
				plugin.saveConfig();
				return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " has been enabled successfully");
			} else {
				plugin.getConfig().addDefault(arena + ".status" , "enabled");
				plugin.saveConfig();
				return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " has been enabled successfully");	
			}
			} else {
					return (ChatColor.RED + "[MST-Battle] The arena could not be found !!");
			}
			
			// @end if command is "disable" + the arena name !

	}
	
}
