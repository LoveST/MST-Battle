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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class ClickSign implements Listener {
	
	public static MSTBattle plugin ;
	public ClickSign(MSTBattle instance) {
		plugin = instance;
	}

	final int number = 15;
	
	public int TimerStart(final Player player){
		plugin.timerint.put("timer", number);
	@SuppressWarnings("deprecation")

	 int timer = plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
		
		
		public void run(){
			
		}
		
	}, 0L, 20L);
	return timer;
	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) throws InterruptedException {

		final Player p = (Player) e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
	        if(e.getClickedBlock().getTypeId() == 63 || e.getClickedBlock().getTypeId() == 68) {
	            Sign sign = (Sign) e.getClickedBlock().getState();
	            String arenasignt = ChatColor.stripColor(sign.getLine(1));
	            
	            if(sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[MSTB J]") || sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[MST-Battle]")){
	    
	            	if(plugin.getConfig().contains(arenasignt)){
	            		if(plugin.getConfig().get(arenasignt + ".status").equals("1")) {
	            			if(plugin.arenaplayers.containsKey(arenasignt) || !plugin.arenaplayers.containsKey(arenasignt)){
	            	             if(!plugin.arenaplayers.containsKey(arenasignt)){
	            	            	 plugin.arenaplayers.put(arenasignt, "0");
	            	             } // @end check if arena name is empty or not 
	            	             	if(plugin.arenaplayers.get(e.getPlayer().getName()) == arenasignt) {
	            	             		p.sendMessage(ChatColor.RED + "[MST-Battle] You're already joined this battle !!");
	            	             	} else {
	            	             		
	            	             		if(plugin.arenaplayers.get(e.getPlayer().getName()) != arenasignt){
	            	             			if(plugin.arenaplayers.containsKey(p.getName())){
	            	             				plugin.arenaplayers.remove(plugin.arenaplayers.get(p.getName()));
	            	             			
	            	             			} // @end remove player name if exist in another lobby
	            	             		
	            	             				if(plugin.arenaplayers.get(arenasignt) == "2"){
		            	             			p.sendMessage(ChatColor.RED + "[MST-Battle] The lobby is full or already started !!");
	            	             				} else if (plugin.arenaplayers.get(arenasignt) == "0") {
		            	             		
		            	             	            	
	            	             					
	            	             					plugin.arenaplayers.put(p.getName(), arenasignt);

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
		            								ItemStack wool = new ItemStack(Material.WOOL);
		            								ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

		            								
		            								p.getInventory().clear();		            							
		            								p.getInventory().setBoots(boots);  								
		            								p.getInventory().setChestplate(chest);					
		            								p.getInventory().setLeggings(pants);
		            								p.getInventory().setHelmet(wool);	
		            								p.getInventory().setItem(0, sword);
		            								Thread.sleep(5);
		            								p.setGameMode(GameMode.SURVIVAL);
	                								
	                								p.teleport(location);
	                								
	                								plugin.PlayerFreeze(1, p.getName(),1);
	                								
	                								p.setGameMode(GameMode.SURVIVAL);
	            	             					
	            	             					
	            	             					

		            				
		            				
		            				
	            	             				} else if (plugin.arenaplayers.get(arenasignt) == "1") {
	            	             					
	            	             					
	            	             					
	            	             					plugin.arenaplayers.put(arenasignt, "2");
	            	             					plugin.arenaplayers.put(p.getName(), arenasignt);
	            	             					
	            	             					String[] cords = plugin.getConfig().getString(arenasignt + ".player2").split(":");
	                								double player2x = Double.parseDouble(cords[0]);
	                								double player2y = Double.parseDouble(cords[1]);
	                								double player2z = Double.parseDouble(cords[2]);
	                								float player2yaw = Float.parseFloat(cords[3]);
	                								float player2pitch = Float.parseFloat(cords[4]);
	                								Location location = new Location(Bukkit.getWorld("world"),player2x, player2y, player2z, player2yaw, player2pitch);
	                								
	                								ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		            								ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		            								ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
		            								ItemStack wool = new ItemStack(Material.WOOL);
		            								ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

		            								
		            								p.getInventory().clear();		            							
		            								p.getInventory().setBoots(boots);  								
		            								p.getInventory().setChestplate(chest);					
		            								p.getInventory().setLeggings(pants);
		            								p.getInventory().setHelmet(wool);	
		            								p.getInventory().setItem(0, sword);
		            								Thread.sleep(5);
		            								p.setGameMode(GameMode.SURVIVAL);
	                								
	                								p.teleport(location);
	                								
	                								plugin.PlayerFreeze(1, p.getName(),2);
	                								
	                								p.setGameMode(GameMode.SURVIVAL);
	            	             					
	            	             					
	            	             					
	            	             					// timer start
	                								
	            	             					
	            	             					
	            	             					
	            	             					
	            	             				} // @end check if arena is full or not [2-1-0]
	            	             		
	            	             		
	            	             		} // @end if name exist and != the arena name
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             		
	            	             	} // @end check if player clicked the same sign and he is already connected to the same arena
	            			} // @end / check if arena created in the Hashmap
	            				} else if(plugin.getConfig().get(arenasignt + ".status").equals("0")) {
	            					p.sendMessage(ChatColor.RED + "[MST-Battle] The " + arenasignt + " is offline, try again later !!");
	            				} // @end / check if arena is enabled
	            		
	            					} else {
	            					p.sendMessage(ChatColor.RED + "[MST-Battle] The arena does not exist !!");
	            		
	            						} // @end check if arena exist in the config file !
	            		
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	} else if(sign.getLine(1).equalsIgnoreCase(ChatColor.BLACK + "[ " + ChatColor.GREEN + "leave " + ChatColor.BLACK + "]")) {
	            		
	            		String arenaname = plugin.arenaplayers.get(p.getName());
	            		if(plugin.arenaplayers.containsKey(p.getName())){
	            			
	            			plugin.getServer().getScheduler().cancelTask(this.TimerStart(p));
	            			plugin.PlayerFreeze(2, p.getName(),1);
	            			
	            			String[] cords = plugin.getConfig().getString(arenaname + ".lobby").split(":");
	            			double lobbyx = Double.parseDouble(cords[0]);
	            			double plobbyy = Double.parseDouble(cords[1]);
	            			double lobbyz = Double.parseDouble(cords[2]);
	            			float lobbyyaw = Float.parseFloat(cords[3]);
	            			float lobbypitch = Float.parseFloat(cords[4]);
	            			
	            			if(plugin.ArenaStatus(0,arenaname,"1") == 2){
	            				plugin.ArenaStatus(1,arenaname,"1");
	            			
	            			} else if(plugin.ArenaStatus(0,arenaname,"1") == 1){
	            				plugin.ArenaStatus(1,arenaname,"0");
	            			}
	            			
	            			
	            			
	            			Location location = new Location(Bukkit.getWorld("world"),lobbyx, plobbyy, lobbyz, lobbyyaw, lobbypitch);
	            			e.getPlayer().teleport(location);
	            			plugin.arenaplayers.remove(p.getName());
	            			p.getInventory().clear();
	            			
	            			
	            		} else {
	            			p.sendMessage(ChatColor.RED + "[MST-Battle] you're not joined any battle !!");
	            		}
	            		
	            		
	            		
	            		
	            		
	            		
	            		
	            		
	            	} // @end / check if player clicked the sign that contains the args
	            } // @end / check if player clicked the sign with the id !
	        } // @end / check if player right clicked somthing 
	    }
	
	
	
	
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
				
		if(plugin.arenaplayers.containsKey(e.getPlayer().getName())){
			
			String player = e.getPlayer().getName();
			String arenaname = plugin.arenaplayers.get(player);
			ItemStack air = new ItemStack(Material.AIR);
			
			if(plugin.ArenaStatus(0,arenaname,"2") == 2){
				plugin.getServer().getScheduler().cancelTask(TimerStart(e.getPlayer()));
				plugin.timerint.remove("timer");
				plugin.arenaplayers.remove(arenaname);
				plugin.arenaplayers.put(arenaname, "1");	
			} else if (plugin.ArenaStatus(0,arenaname,"2") == 1) {
				plugin.arenaplayers.remove(arenaname);
				plugin.arenaplayers.put(arenaname, "0");
			}
			
		//	plugin.getServer().getScheduler().cancelTask(timer);
			plugin.arenaplayers.remove(player);
			plugin.PlayerFreeze(2, player,1);
			
			
			e.getPlayer().getPlayer().getInventory().clear();
			e.getPlayer().getInventory().setBoots(air);  								
			e.getPlayer().getInventory().setChestplate(air);					
			e.getPlayer().getInventory().setLeggings(air);
			e.getPlayer().getInventory().setHelmet(air);
			e.getPlayer().getInventory().setItem(0, air);
			e.getPlayer().getInventory().setItem(1, air);
			e.getPlayer().getInventory().setItem(2, air);
			e.getPlayer().getInventory().setItem(3, air);
			e.getPlayer().getInventory().setItem(4, air);
			e.getPlayer().getInventory().setItem(5, air);
			e.getPlayer().getInventory().setItem(6, air);
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
	
	
	
	
	
	
	
	
	

}
