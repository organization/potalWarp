package warp.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

public class WarpCommand extends Command {
	public WarpManager manager;
	
	public WarpCommand(){
		super("워프", "워프합니다.", "/워프 <워프이름>", new String[]{"warp","move","이동"});
		this.setPermission("warp.cmd");
		this.manager = new WarpManager();
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.isPlayer()){
			sender.sendMessage(TextFormat.RED+"플레이어만 사용 가능합니다.");
			return false;
		}
		try{
			if(this.manager.warp(args[0], (Player)sender)) {
				sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프되었습니다.");
				return true;
			}else{
				sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프하지 못했습니다.");
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프의 이름을 설정해주세요");
			return false;
		}
	}

}
