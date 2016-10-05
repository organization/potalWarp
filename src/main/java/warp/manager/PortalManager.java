package warp.manager;

import cn.nukkit.Player;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.utils.TextFormat;
import warp.Warp;

public class PortalManager {
	public WarpManager manager;
	public Warp warp;
	
	public PortalManager(){
		this.warp = Warp.getInstance();
		this.manager = new WarpManager();
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
}
