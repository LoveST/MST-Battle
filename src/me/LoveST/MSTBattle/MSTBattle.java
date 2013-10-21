package me.LoveST.MSTBattle;


import java.util.HashMap;
import java.util.logging.Logger;

import me.LoveST.MSTBattle.Commands.CreateArena;
import me.LoveST.MSTBattle.Commands.DeleteArena;
import me.LoveST.MSTBattle.Commands.DisableArena;
import me.LoveST.MSTBattle.Commands.EnableArena;
import me.LoveST.MSTBattle.Commands.ArenaLobby;
import me.LoveST.MSTBattle.Commands.SetPlayerOneSpawn;
import me.LoveST.MSTBattle.Commands.SetPlayerTwoSpawn;
import me.LoveST.MSTBattle.EventTasks.CreateSign;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MSTBattle extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft"); 
	public HashMap<String,Integer> arena = new HashMap<String,Integer>();
	public HashMap<String,String> arenaplayers = new HashMap<String,String>();
	public CreateArena createarena = new CreateArena(this);
	public DeleteArena deletearena = new DeleteArena(this);
	public EnableArena enablearena = new EnableArena(this);
	public DisableArena disablearena = new DisableArena(this);
	public ArenaLobby ArenaLobby = new ArenaLobby(this);
	public SetPlayerOneSpawn SetPlayerOneSpawn = new SetPlayerOneSpawn(this);
	public SetPlayerTwoSpawn SetPlayerTwoSpawn = new SetPlayerTwoSpawn(this);

	
	public EventCheck eventcheck = new EventCheck(this);
	public CreateSign CreateSign = new CreateSign(this);
	
	@Override
	public void onEnable(){
		
		
		getServer().getPluginManager().registerEvents(eventcheck, this);
		getServer().getPluginManager().registerEvents(CreateSign, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	public void onDisable(){
		saveConfig();
	}
	
	
	
	public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("MSTB")) {
			if(args.length  == 0) {
				if(player.hasPermission("MSTB-Create")) {
			player.sendMessage(ChatColor.GOLD + "/mstb create <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb delete <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb join <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb lobby <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb playerspawn1 <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb playerspawn2 <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb disable <arena-name>");	
			player.sendMessage(ChatColor.GOLD + "/mstb enable <arena-name>");	

				} else {
					player.sendMessage(ChatColor.GOLD + "/mstb join <arena-name>");
				} // @end if player has permission
		
			} else if (args.length == 1) {
				
					if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("lobby") || args[0].equalsIgnoreCase("playerspawn1") || args[0].equalsIgnoreCase("playerspawn2") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable")) {
						player.sendMessage(ChatColor.RED + "[MST-Battle] Please specified an arena name !");
					} else {
						player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown command !!");
					} // @end if command.args[0] == all mst commands without arena name !

				
			} else if (args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					
					player.sendMessage(this.createarena.ArenaCreate(args[1]));
					
					// @end if command is "create" + the arena name !
				} else if (args[0].equalsIgnoreCase("delete")) {

						player.sendMessage(this.deletearena.ArenaDelete(args[1]));
					
					
					
					// @end if command is "delete" + the arena name !
				} else if (args[0].equalsIgnoreCase("join")) {
					
                   
                   
                   
					// @end if command is "join" + the arena name ! 
				} else if (args[0].equalsIgnoreCase("lobby")) {
					

						Location loc = player.getLocation();
					player.sendMessage(this.ArenaLobby.ArenaLobbySet(args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()));
					
					
	
					// @end if command is "spawn" + the arena name !
				} else if (args[0].equalsIgnoreCase("playerspawn1")) {
					
					Location loc = player.getLocation();
					player.sendMessage(this.SetPlayerOneSpawn.PlayerOneSpawn(args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()));

					

					
					// @end if command is "playerspawn1" + the arena name !
				} else if (args[0].equalsIgnoreCase("playerspawn2")) {
					
						Location loc = player.getLocation();
					
						player.sendMessage(this.SetPlayerTwoSpawn.PlayerTwoSpawn(args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()));

						
					
					// @end if command is "playerspawn2" + the arena name !
				} else if (args[0].equalsIgnoreCase("disable")) {
					
					
					player.sendMessage(this.disablearena.ArenaDisable(args[1]));
					
					// @end if command is "disable" + the arena name !
				} else if (args[0].equalsIgnoreCase("enable")) {
		
					
					player.sendMessage(this.enablearena.ArenaEnable(args[1]));
					
					// @end if command is "disable" + the arena name !
				} else {
					player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown error !! ");
				}
				
				
				
			} else {
				player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown Command !!");
			} // @end if args[0-1-2]
	} // @end if get command "MSTB"
		return true;
	}
	
	
}
