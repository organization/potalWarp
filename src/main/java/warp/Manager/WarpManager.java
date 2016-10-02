package warp.Manager;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import warp.Warp;

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
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			
			map.put("x", x);
			map.put("y", y);
			map.put("z", z);
			map.put("level", level.getFolderName());
			
			warp.warps.set(name, map);
			warp.save();
			return true;
	}
	public boolean delWarp(String name){
		try{
			if(isWarp(name)){
				warp.warps.remove(name);
				warp.save();
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public boolean warp(String name, Player player){
		try{
			if(isWarp(name)){
				Position pos = getPosition(name);
				player.teleport(pos);
				return true;
			}else return false;
		}catch(Exception e){
			return false;
		}
	}
	public String[] getList(){
		try{
			String[] result = new String[warp.warps.getKeys().size()];
			int i = 0;
			for(String s : warp.warps.getKeys()){
				result[i] = s;
				i++;
			}
			return result;
		}catch(Exception e){
			return new String[0];
		}
	}
	public boolean isWarp(String name){
		if(warp.warps.getKeys().contains(name)) return true;
		else return false;
	}
	@SuppressWarnings("unchecked")
	public Position getPosition(String name){
		if(isWarp(name)){
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)warp.warps.get(name);
			return new Position((double)map.get("x"),(double)map.get("y"),(double)map.get("z"),warp.getServer().getLevelByName(map.get("level").toString()));
		}else return new Position();
	}
}
