package me.LoveST.MSTBattle.Commands;

import org.bukkit.ChatColor;

import me.LoveST.MSTBattle.MSTBattle;

public class DisableArena {
	
	public static MSTBattle plugin ;
	public DisableArena(MSTBattle instance) {
		plugin = instance;
	}
	
	public String ArenaDisable(String arena){
		
		
		if(plugin.getConfig().contains(arena)){
			if(plugin.getConfig().contains(arena + ".status")){
				plugin.getConfig().set(arena + ".status", null);
				plugin.getConfig().addDefault(arena + ".status" , "0");
				plugin.saveConfig();
				return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " has been disbaled successfully");
			} else {
				plugin.getConfig().addDefault(arena + ".status" , "0");
				plugin.saveConfig();
				return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " has been disbaled successfully");	
			}
			} else {
				return (ChatColor.RED + "[MST-Battle] The arena could not be found !!");
		}	

	}

}
