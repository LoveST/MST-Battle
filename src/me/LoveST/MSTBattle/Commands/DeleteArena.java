package me.LoveST.MSTBattle.Commands;

import me.LoveST.MSTBattle.MSTBattle;

import org.bukkit.ChatColor;

public class DeleteArena {

	public static MSTBattle plugin ;
	public DeleteArena(MSTBattle instance) {
		plugin = instance;
	}
	
	
	
	public String ArenaDelete(String arena){
		
		if(plugin.getConfig().contains(arena)) {
			plugin.getConfig().set(arena, null);
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "Arena deleted successfully");
		} else {
			return (ChatColor.RED + "[MST-Battle] Could not find the arena !!");
		}
	}
	
	
}
