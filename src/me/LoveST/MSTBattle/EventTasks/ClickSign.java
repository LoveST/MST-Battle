package me.LoveST.MSTBattle.EventTasks;

import me.LoveST.MSTBattle.MSTBattle;

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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickSign implements Listener {
	
	public static MSTBattle plugin ;
	public ClickSign(MSTBattle instance) {
		plugin = instance;
	}
	
	
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

}
