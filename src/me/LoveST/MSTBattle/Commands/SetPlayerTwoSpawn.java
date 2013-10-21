package me.LoveST.MSTBattle.Commands;

import org.bukkit.ChatColor;

import me.LoveST.MSTBattle.MSTBattle;

public class SetPlayerTwoSpawn {

	public static MSTBattle plugin ;
	public SetPlayerTwoSpawn(MSTBattle instance) {
		plugin = instance;
	}
	
	public String PlayerTwoSpawn(String arena, double x , double y , double z , float yaw , float pitch) {
		
		if(plugin.getConfig().contains(arena + ".player2")){
			plugin.getConfig().set(arena + ".player2", null);
			plugin.getConfig().addDefault(arena + ".player2", x + ":" + y + ":" + z  + ":" + yaw + ":" + pitch);
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "you set the player 2 spawn in the " + arena);
		} else {
		
			plugin.getConfig().addDefault(arena + ".player2", x + ":" + y + ":" + z  + ":" + yaw + ":" + pitch);
			plugin.saveConfig();
		return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "you set the player 2 spawn in the " + arena);
		
		} // @end if player loc2 already exist
		
	}
	
}
