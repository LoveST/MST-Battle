package me.LoveST.MSTBattle;

import java.util.HashMap;
import java.util.logging.Logger;

import me.LoveST.MSTBattle.EventTasks.CreateSign;
import me.LoveST.MSTBattle.EventTasks.ClickSign;




import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class MSTBattle extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft");
	public HashMap<String, String> arenaplayers = new HashMap<String, String>();
	public HashMap<String, Integer> timerint = new HashMap<String, Integer>();

	public static MSTBattle plugin;
	public EventCheck eventcheck = new EventCheck(this);
	public CreateSign CreateSign = new CreateSign(this);
	public ClickSign ClickSign = new ClickSign(this);

	int number = 10;

	@Override
	public void onEnable() {

		getServer().getPluginManager().registerEvents(eventcheck, this);
		getServer().getPluginManager().registerEvents(CreateSign, this);
		getServer().getPluginManager().registerEvents(ClickSign, this);
		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	public void onDisable() {
		saveConfig();
	}
	

	

	public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
		final Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("MSTB")) {
			if(args.length  == 0) {
				if(player.hasPermission("MSTB-Create")) {
						player.sendMessage(ChatColor.GOLD + "/mstb create <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb delete <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb lobby <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb playerspawn1 <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb playerspawn2 <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb disable <arena-name>");	
						player.sendMessage(ChatColor.GOLD + "/mstb enable <arena-name>");	

				} else {
						player.sendMessage(ChatColor.GOLD + "/mstb join <arena-name>");
				} // @end if player has permission
		
			} else if (args.length == 1) {
				if(player.hasPermission("MSTB-Create")){
					if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("lobby") || args[0].equalsIgnoreCase("playerspawn1") || args[0].equalsIgnoreCase("playerspawn2") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable")) {
						player.sendMessage(ChatColor.RED + "[MST-Battle] Please specified an arena name !");
					} else {
						player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown command !!");
					} 
				} else {
					player.sendMessage(ChatColor.RED + "You dont have permission to do this !!");
				} // @end if command.args[0] == all mst commands without arena name !

				
			} else if (args.length == 2) {
				if(player.hasPermission("MSTB-Create")){
				if(args[0].equalsIgnoreCase("create")) {
					
					if(this.CheckArenaExist(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] The arena already exist !!");
					} else {
						if(this.CreateArena(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "The arena created successfully");
						}
					}
					
					// @end if command is "create" + the arena name !
				} else if (args[0].equalsIgnoreCase("delete")) {

					if(!this.CheckArenaExist(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
					} else {
						if(this.DeleteArena(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "The arena deleted successfully");
						}
					}
					
					
					
					// @end if command is "delete" + the arena name !
				} else if (args[0].equalsIgnoreCase("lobby")) {
					

						Location loc = player.getLocation();
						if(!this.CheckArenaExist(args[1])){
							player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
						} else {
							if(this.SetArenaLobby(args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch())) {
								player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "the " + args[1] + " main lobby set !");
							}
						}
					
	
					// @end if command is "spawn" + the arena name !
				} else if (args[0].equalsIgnoreCase("playerspawn1")) {
					
					Location loc = player.getLocation();
					if(!this.CheckArenaExist(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
					} else {
						if(SetPlayerSpawn("player1",args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch())){
							player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "player one spawn set in : " + args[1]);
						}
					}
					

				
					// @end if command is "playerspawn1" + the arena name !
				} else if (args[0].equalsIgnoreCase("playerspawn2")) {
					
						Location loc = player.getLocation();
					
						if(!this.CheckArenaExist(args[1])){
							player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
						} else {
							if(SetPlayerSpawn("player2",args[1], loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch())){
								player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "player one spawn set in : " + args[1]);
							}
						
						
						}
						
						
						
				} else if (args[0].equalsIgnoreCase("2player")) {
					
					arenaplayers.put("arena1", "1");
					
					
					
					
					
						
					
					// @end if command is "playerspawn2" + the arena name !
				} else if (args[0].equalsIgnoreCase("disable")) {
					
					
					if(!this.CheckArenaExist(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
					} else {
						if(this.DisableArena(args[1])){
							player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + args[1] + " has been disabled !");
						}
					
					
					}
					
					
					// @end if command is "disable" + the arena name !
				} else if (args[0].equalsIgnoreCase("enable")) {
		
					
					if(!this.CheckArenaExist(args[1])){
						player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
					} else {
						if(this.EnableArena(args[1])){
							player.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + args[1] + " has been enabled !");
						}
					
					
					}
					
					
					// @end if command is "disable" + the arena name !
				
				} else {
					player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown command !!");
				}
				
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to do this !!");
				}  // @end check if player has permission @ args[2]
				
			} else {
				player.sendMessage(ChatColor.RED + "[MST-Battle] Uknown Command !!");
			} 
			} // @end if args[0-1-2]
	 // @end if get command "MSTB"
		return true;
	}
	



	public boolean CheckArenaExist(String arena){
		if(this.getConfig().contains(arena)){
			return true;
		} else {
			return false;
		}
	
	}
	
	
	
	public boolean CreateArena(String arena){
		
		this.getConfig().set(arena + ".player1", "null");
		this.getConfig().set(arena + ".player2", "null");
		this.getConfig().set(arena + ".lobby", "null");
		this.getConfig().set(arena + ".status", "0");
		this.saveConfig();
		
		return true;
	}
	
	
	
	
	public String CheckArenaStatus(String arena){
		if(this.arenaplayers.get(arena) == "2"){
			return (ChatColor.RED + "[MST-Battle] The arena is full !!");
		} else {
			return "";
		}

	}
	
	
	public boolean EnableArena(String arena){
		this.getConfig().set(arena + ".status", null);
		this.getConfig().addDefault(arena + ".status" , "1");
		this.saveConfig();
		return true;
	}
	
	
	public boolean DisableArena(String arena){
		this.getConfig().set(arena + ".status", null);
		this.getConfig().addDefault(arena + ".status" , "0");
		this.saveConfig();
		return true;
	}
	
	
	
	public boolean DeleteArena(String arena){
		this.getConfig().set(arena, null);
		this.saveConfig();
		return true;
	}
	
	

	
	public boolean SetArenaLobby(String arena, double x , double y , double z , float yaw , float pitch){
		this.getConfig().set(arena + ".lobby", null);
		this.getConfig().addDefault(arena + ".lobby", x + ":" + y + ":" + z + ":" + yaw + ":" + pitch );
		this.saveConfig();
		return true;
	}
	
	
	public boolean SetPlayerSpawn(String player, String arena, double x , double y , double z , float yaw , float pitch){
		this.getConfig().set(arena + "." + player, null);
		this.getConfig().addDefault(arena + "." + player, x + ":" + y + ":" + z + ":" + yaw + ":" + pitch );
		this.saveConfig();
		return true;	
	}
	
	


}
