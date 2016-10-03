package warp.Manager;

import cn.nukkit.Player;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.utils.TextFormat;
import warp.Warp;

public class PortalManager {
	public Warp warp;
	public WarpManager manager;
	
	public PortalManager(Warp plugin) {
		this.warp = plugin;
		this.manager = new WarpManager(plugin);
	}
	
	public void addSignPortal(SignChangeEvent event){
		Player player = event.getPlayer();
		String line1 = event.getLine(0);
		String line2 = event.getLine(1);
		
		if(line1.equalsIgnoreCase("포탈")||line1.equalsIgnoreCase("§l§b[ Portal Sign ]")){
			if(player.isOp()){
				if(manager.isWarp(line2)){
					event.setLine(0, "§l§b[ Portal Sign ]");
					event.setLine(2, "§a로 이동");
				}else{
					player.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"워프를 찾을 수 없습니다.");
					event.setCancelled();
				}
			}else{
				player.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"권한이 없습니다.");
				event.setCancelled();
			}
		}
	}
}
