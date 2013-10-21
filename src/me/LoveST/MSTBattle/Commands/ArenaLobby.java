package me.LoveST.MSTBattle.Commands;

import org.bukkit.ChatColor;

import me.LoveST.MSTBattle.MSTBattle;

public class ArenaLobby {

	public static MSTBattle plugin ;
	public ArenaLobby(MSTBattle instance) {
		plugin = instance;
	}
	
	public String ArenaLobbySet(String arena , double x , double y , double z , float yaw , float pitch) {
		
		if(plugin.getConfig().contains(arena)){
		if(plugin.getConfig().contains(arena + ".lobby")){
			plugin.getConfig().set(arena + ".lobby", null);
			plugin.getConfig().addDefault(arena + ".lobby", x + ":" + y + ":" + z + ":" + yaw + ":" + pitch );
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " main lobby set !");
		} else {
			plugin.getConfig().addDefault(arena + ".lobby", x + ":" + y + ":" + z + ":" + yaw + ":" + pitch );
			plugin.saveConfig();
			return (ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + arena + " main lobby set !");
			}
		} else {
			return (ChatColor.RED + "[MST-Battle] The arena does not exist !!");
			}
		
		
	} // @end arenalobby function 
	
}
