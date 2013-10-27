package me.LoveST.MSTBattle;

import java.util.ArrayList;



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.LoveST.MSTBattle.EventTasks.ClickSign;


public class EventCheck implements Listener {

	public static MSTBattle plugin ;
	ArrayList<String> arena1p = new ArrayList<String>();
	public ClickSign ClickSign = new ClickSign(plugin);




	
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
			
			if(plugin.PlayerFreeze(0, p.getName(),1) == 1){
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
	

		
	
	
	
	
	
}
