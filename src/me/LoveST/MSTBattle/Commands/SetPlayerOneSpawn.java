package me.LoveST.MSTBattle.Commands;

import org.bukkit.ChatColor;

import me.LoveST.MSTBattle.MSTBattle;

public class SetPlayerOneSpawn {

	public static MSTBattle plugin ;
	public SetPlayerOneSpawn(MSTBattle instance) {
		plugin = instance;
	}
	
	
	public String SetPlayerOneSpawn(String arena, double x , double y , double z , float yaw , float pitch){
		
		
		if(plugin.getConfig().contains(arena + ".player1")){
			plugin.getConfig().set(arena + ".player1", null);
			plugin.getConfig().addDefault(arena + ".player1", x + ":" + y + ":" + z  + ":" + yaw + ":" + pitch);
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "you set the player 1 spawn in the " + arena);
		} else {
		
			plugin.getConfig().addDefault(arena + ".player1", x + ":" + y + ":" + z  + ":" + yaw + ":" + pitch);
			plugin.saveConfig();
		return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "you set the player 1 spawn in the " + arena);
		
		} // @end if player loc1 already exist

	}
	
}
