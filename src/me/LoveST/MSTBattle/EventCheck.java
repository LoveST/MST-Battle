package me.LoveST.MSTBattle;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;


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
    public void OnSignCreate (SignChangeEvent sign) {
    	Player player = sign.getPlayer();
    	
    	if(player.hasPermission("MSTB-CreateSign")){
    	
    	if(sign.getLine(0).equalsIgnoreCase("[MSTB j]")){
    		if(plugin.getConfig().contains(sign.getLine(1))){
        		sign.setLine(0,  ChatColor.GREEN + "[MST-Battle]");
        		sign.setLine(1, ChatColor.BLUE + sign.getLine(1));
        		sign.setLine(2, ChatColor.GOLD + "Click to join");
        		sign.setLine(3, ChatColor.GOLD + "the battle");

        		
        		player.sendMessage(ChatColor.RED + "[MST-Battle]" + ChatColor.GREEN +" Sign created successfully");
        	
    		} else {
        		player.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
    			
    		} // @end check if arena exist
    	} // @end check if line 1 = MST Join	
    	
    	
    	if(sign.getLine(0).equalsIgnoreCase("[MSTB]") && sign.getLine(1).equalsIgnoreCase("[leave]") ){
    		
    		sign.setLine(0, ChatColor.RED + "---------------");
    		sign.setLine(1, ChatColor.BLACK + "[ " + ChatColor.GREEN + "leave " + ChatColor.BLACK + "]");
    		sign.setLine(3, ChatColor.RED + "---------------");
    		
    	}
    	
    	} else {
    		player.sendMessage(ChatColor.RED + "You dont have permission to do this !!");
    	}
    	
    	
    	
    	
    } // @end the event

	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) throws InterruptedException {

		Player p = (Player) e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
	        if(e.getClickedBlock().getTypeId() == 63 || e.getClickedBlock().getTypeId() == 68) {
	            Sign sign = (Sign) e.getClickedBlock().getState();
	            String arenasignt = ChatColor.stripColor(sign.getLine(1));
	            
	            if(sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[MSTB J]") || sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[MST-Battle]")){
	            	if(plugin.getConfig().contains(arenasignt)){
	            		if(plugin.getConfig().getString(arenasignt + ".status").equalsIgnoreCase("enabled")){
	            			
	            			
	            				if(plugin.arenaplayers.containsKey(arenasignt)) {
	            					
	            						if(plugin.arenaplayers.get(arenasignt) == "1"){
	            							if(!plugin.arenaplayers.containsKey(p.getName())) {
	            							
	            								
	            							plugin.arenaplayers.put(arenasignt, "2");
	            							plugin.arenaplayers.put(p.getName(), arenasignt);
	            							
	            							p.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "You successfully joined the battle !");
            								String[] cords = plugin.getConfig().getString(arenasignt + ".player2").split(":");
            								double player2x = Double.parseDouble(cords[0]);
            								double player2y = Double.parseDouble(cords[1]);
            								double player2z = Double.parseDouble(cords[2]);
            								float player2yaw = Float.parseFloat(cords[3]);
            								float player2pitch = Float.parseFloat(cords[4]);
            								Location location = new Location(Bukkit.getWorld("world"),player2x, player2y, player2z, player2yaw, player2pitch);
            								
            								p.teleport(location);
            								
            								plugin.arenaplayers.put(p.getName() + "freeze", "2");
            								p.setGameMode(GameMode.SURVIVAL);
	            							
	            							} else {
	            								
	            								if(plugin.arenaplayers.get(p.getName()) != arenasignt){
	        	            						plugin.arenaplayers.remove(p.getName());
	        	            					}
	            								
	            								p.sendMessage(ChatColor.RED + "[MST-Battle] You're already joined " + arenasignt + " !!");
	            								}
	            						
	            						
	            								} else if(plugin.arenaplayers.get(arenasignt) == "2") {
	            									p.sendMessage(ChatColor.RED + "[MST-Battle] The lobby is full already !!");
	            							} 
	            					
	            							} else {
	            									
	            								
	            								
	            								plugin.arenaplayers.put(arenasignt, "1");
	            								plugin.arenaplayers.put(p.getName(), arenasignt);
	            					
	            					
	            								p.sendMessage(ChatColor.RED + "[MST-Battle] " + ChatColor.GREEN + "You successfully joined the battle !");
	            								String[] cords = plugin.getConfig().getString(arenasignt + ".player1").split(":");
	            								double player1x = Double.parseDouble(cords[0]);
	            								double player1y = Double.parseDouble(cords[1]);
	            								double player1z = Double.parseDouble(cords[2]);
	            								float player1yaw = Float.parseFloat(cords[3]);
	            								float player1pitch = Float.parseFloat(cords[4]);
	            								Location location = new Location(Bukkit.getWorld("world"),player1x, player1y, player1z, player1yaw, player1pitch);

	            								ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
	            								ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
	            								ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
	            								
	            								p.getInventory().clear();		            							
	            								p.getInventory().setBoots(boots);  								
	            								p.getInventory().setChestplate(chest);					
	            								p.getInventory().setLeggings(pants);			
	            								p.setGameMode(GameMode.SURVIVAL);

	            								p.teleport(location);							
	            								plugin.arenaplayers.put(p.getName() + "freeze", "1");
	            								
	            								
	            								

	            					
	            				}
	
	            		} else {
	            			p.sendMessage(ChatColor.RED + "[MST-Battle] The arena is disabled !!");

	            		} // @end if arena status is ?
	            		
	            	} else {
            			p.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
	            	}
	            	Thread.sleep(5);
	            
	            	} // @end / check if player name is already in the list !
	            } // @end / check if player clicked the sign that contains the args
	        } // @end / check if player clicked the sign with the id !
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
