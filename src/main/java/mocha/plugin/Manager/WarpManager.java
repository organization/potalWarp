package mocha.plugin.Manager;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import mocha.plugin.Warp;

public class WarpManager {
	public Warp warp;
	
	public WarpManager(Warp plugin){
		this.warp = plugin;
	}
	public boolean addWarp(String name, Location location){
		return addWarp(name,location.getX(),location.getY(),location.getZ(),location.getLevel());
	}
	public boolean addWarp(String name, Position pos){
		return addWarp(name,pos.getX(),pos.getY(),pos.getZ(),pos.getLevel());
	}
	public boolean addWarp(String name, Player player){
		return addWarp(name,player.getX(),player.getY(),player.getZ(),player.getLevel());
	}
	public boolean addWarp(String name,double x,double y, double z,Level level){
		try{
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			
			map.put("x", x);
			map.put("y", y);
			map.put("z", z);
			map.put("level", level.getFolderName());
			warp.warps.set(name, map);
			warp.save();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean delWarp(String name){
		try{
			warp.warps.remove(name);
			warp.save();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
