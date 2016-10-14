package warp.manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import warp.Warp;

public class WarpManager {
        private static WarpManager instance = new WarpManager();
	private Warp warp = Warp.getInstance();
	
	private WarpManager(){
	}

        public static WarpManager getInstance(){
                return instance;
        }
	
	public boolean addWarp(String warp, Player player){
		return addWarp(warp, player.getX(),player.getY(),player.getZ(),player.getLevel());
	}
	public boolean addWarp(String warp, double x, double y, double z, Level level){
		try{
			if(!warp.contains(".")){
				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				
				map.put("x", x);
				map.put("y", y);
				map.put("z", z);
				map.put("level", level.getFolderName());
			
				this.warp.warps.set(warp, map);
				this.warp.save();
				return true;
			}else{
				return false;
			}
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
		if(this.warp.warps.exists(warp)) return true;
		else return false;
	}
	public ArrayList<String> getList(){
		try{
			ArrayList<String> list = new ArrayList<String>();
			for(String s : this.warp.warps.getKeys()){
				if(!s.contains(".")){
					list.add(s);
				}
			}
			return list;
		}catch(Exception e){
			return new ArrayList<String>();
		}
	}
	@SuppressWarnings("unchecked")
	public boolean warp(String warp, Player player){
		try{
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)this.warp.warps.get(warp);
			double x,y,z;
			x = toDouble(map.get("x"));
			y = toDouble(map.get("y"));
			z = toDouble(map.get("z"));
			player.teleport(new Position(x,y,z,this.warp.getServer().getLevelByName(map.get("level").toString())));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	private double toDouble(Object o){
		if(o instanceof Integer) return ((Integer)o).doubleValue();
		if(o instanceof Double) return ((Double)o).doubleValue();
		if(o instanceof String) return Double.parseDouble(o.toString());
		return 0.0D;
	}
}
