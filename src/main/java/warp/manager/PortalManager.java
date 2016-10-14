package warp.manager;

import cn.nukkit.Player;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.utils.TextFormat;
import warp.Warp;

public class PortalManager {
        private static PortalManager instance = new PortalManager();
	private WarpManager manager = WarpManager.getInstance();
	private Warp warp = Warp.getInstance();
	
	private PortalManager(){
	}

        public static PortalManager getInstance() {
                return instance;
        }
	
	public void addSignWarp(SignChangeEvent event){
		Player player = event.getPlayer();
		String line_top = event.getLine(0);
		
		if(line_top.equals("포탈")||line_top.equals("portal")||line_top.equals("§l§b[ Portal Sign]")){
			if(!player.isOp()){
				player.sendMessage(TextFormat.RED+"포탈 생성권한이 없습니다.");
				event.setCancelled();
			}
			else if(!manager.isWarp(event.getLine(1))){
				player.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 찾을 수 없습니다.");
				event.setCancelled();
			}
			else{
				event.setLine(0, "§l§b[ Portal Sign ]");
				event.setLine(2, "§a"+event.getLine(1)+"으로 이동");
				player.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"포탈을 생성하였습니다.");
			}
		}
	}
	/**
	* name : {
	*   "x" : 1.0, 
	*   "y" : 1.0,
	*   "z" : 1.0,
	*   "world" : "world",
	*   "target" : "spawn"
	* }
	*/
	public boolean addPortal(String name,Player player,String target){
			this.addPortal(name, player.getX(),player.getY(),player.getZ(),player.getLevel(), target);
		}
	 
	public boolean addPortal(String name,double x,double y,double z,Level level,String target){
		if(!this.manager.isWarp()){
			return false;
		}
		LinkedHashMap<String, Object> data = new LinkedHashMap<String,Object>();
		data.put("x", String.format("%.1f", x));
		data.put("y", String.format("%.1f", y));
		data.put("z", String.format("%.1f", z));
		data.put("level", level.getFolderName());
		data.put("target", target);
		try{
		warp.portals.set(name, data);
		}catch(Exption e){
		return flase;
		}
		return true;
	}
	public void delPortal(String name){
		//main클스 porta Config - remove  메서이 
	}
	
	public boolean isPortal(String name){
		return this.warp.getInstance().portals.exists(name);
	}
	public void taskUpdate(){
	}
}
