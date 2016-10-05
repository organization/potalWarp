package warp.manager;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import warp.Warp;

public class WarpManager {
	public Warp warp;
	
	public WarpManager(){
		this.warp = Warp.getInstance();
	}
	
	public boolean addWarp(String warp, Player player){
		return addWarp(warp, player.getX(),player.getY(),player.getZ(),player.getLevel());
	}
	public boolean addWarp(String warp, double x, double y, double z, Level level){
		try{
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			
			map.put("x", x);
			map.put("y", y);
			map.put("z", z);
			map.put("world", level.getFolderName());
			
			this.warp.warps.set(warp, map);
			this.warp.save();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean delWarp(String warp){
		try{
			this.warp.warps.remove(warp);
			this.warp.save();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean isWarp(String warp){
		if(this.warp.warps.get(warp) == null) return false;
		else return true;
	}
	public String[] getList(){
		try{
			String[] list = new String[this.warp.warps.getKeys().size()];
			int i = 0;
			for(String s : this.warp.warps.getKeys()){
				list[i] = s;
				i++;
			}
			return list;
		}catch(Exception e){
			return new String[]{};
		}
	}
	public boolean warp(String warp, Player player){
		try{
			Position pos = this.getWarp(warp);
			player.teleport(pos);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public Position getWarp(String warp){
		try{
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)this.warp.warps.get(warp);
			
			return new Position((double)map.get("x"), (double)map.get("y"), (double)map.get("z"), 
					this.warp.getServer().getLevelByName(map.get("world").toString()));
		}catch(Exception e){
			return new Position();
		}
	}
}
