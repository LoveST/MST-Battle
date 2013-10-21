package me.LoveST.MSTBattle;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventCheck implements Listener {

	public static MSTBattle plugin ;
	ArrayList<String> arena1p = new ArrayList<String>();

	
	public EventCheck(MSTBattle instance) {
		plugin = instance;
	}
	

	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		
		
		if( plugin.arenaplayers.containsKey(e.getPlayer().getName() ) ){
			Player p = (Player) e.getPlayer();
			String arenaname = plugin.arenaplayers.get(p.getName());
			Location playerLocation = p.getLocation();
			String PlayerNumber;
			
			if(plugin.arenaplayers.get(p.getName() + "freeze") == "1"){
				PlayerNumber = "1";
			} else {
				PlayerNumber = "2";
			}
			
			String[] cords = plugin.getConfig().getString(arenaname + ".player" + PlayerNumber).split(":");
			double playerx = Double.parseDouble(cords[0]);
			double playery = Double.parseDouble(cords[1]);
			double playerz = Double.parseDouble(cords[2]);
			
			
			if( p.getLocation().getX() != playerx ) {
				
				Location location = new Location(Bukkit.getWorld("world"),playerx, playery, playerz, playerLocation.getYaw(), playerLocation.getPitch());
				p.teleport(location);
			}
			if( p.getLocation().getZ() != playerz ) {
				
				Location location = new Location(Bukkit.getWorld("world"),playerx, playery, playerz, playerLocation.getYaw(), playerLocation.getPitch());
				p.teleport(location);
				
			}
			
			
		}
	}
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
				
		if(plugin.arenaplayers.containsKey(e.getPlayer().getName())){
			
			String player = e.getPlayer().getName();
			String arenaname = plugin.arenaplayers.get(player);
			
			if(plugin.arenaplayers.get(arenaname) == "2"){
				plugin.arenaplayers.remove(arenaname);
				plugin.arenaplayers.put(arenaname, "1");	
			} else if (plugin.arenaplayers.get(arenaname) == "1") {
				plugin.arenaplayers.remove(arenaname);
				plugin.arenaplayers.put(arenaname, "0");
			}
			
			plugin.arenaplayers.remove(player);
			plugin.arenaplayers.remove(e + "freeze");
			
			String[] cords = plugin.getConfig().getString(arenaname + ".lobby").split(":");
			double lobbyx = Double.parseDouble(cords[0]);
			double plobbyy = Double.parseDouble(cords[1]);
			double lobbyz = Double.parseDouble(cords[2]);
			float lobbyyaw = Float.parseFloat(cords[3]);
			float lobbypitch = Float.parseFloat(cords[4]);
			
			Location location = new Location(Bukkit.getWorld("world"),lobbyx, plobbyy, lobbyz, lobbyyaw, lobbypitch);
			e.getPlayer().teleport(location);
		}
		
		
	}
	
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Player player = e.getPlayer();
		
		if(plugin.arenaplayers.containsKey(player.getName())){
		if(e.getMessage().equalsIgnoreCase("/mstb leave")){
			
		} else {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "[MST-Battle] You're not allowed to use commands while in game !!");
			}
		}
		
	}
		
		
	
	
	
	
	
}
