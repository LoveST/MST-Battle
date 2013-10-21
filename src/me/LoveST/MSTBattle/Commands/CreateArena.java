package me.LoveST.MSTBattle.Commands;

import me.LoveST.MSTBattle.MSTBattle;

import org.bukkit.ChatColor;

public class CreateArena {

	public static MSTBattle plugin ;
	public CreateArena(MSTBattle instance) {
		plugin = instance;
	}
	
	
	public String CreateArena(String arena) {
		
		if(plugin.getConfig().contains(arena)) {
			return (ChatColor.RED + "[MST-Battle] Arena already created !");
		} else {
			
			plugin.getConfig().set(arena + ".player1", "null");
			plugin.getConfig().set(arena + ".player2", "null");
			plugin.getConfig().set(arena + ".status", "disabled");
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " successfully created");
		}
		

	}

	
}
