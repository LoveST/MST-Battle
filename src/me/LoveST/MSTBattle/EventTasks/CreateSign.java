package me.LoveST.MSTBattle.EventTasks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.LoveST.MSTBattle.MSTBattle;

public class CreateSign implements Listener {

	public static MSTBattle plugin ;
	public CreateSign(MSTBattle instance) {
		plugin = instance;
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
	
	
	
}
