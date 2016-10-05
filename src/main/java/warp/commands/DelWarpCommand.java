package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

public class DelWarpCommand extends Command {
	public WarpManager manager;
	
	public DelWarpCommand(){
		super("워프삭제", "워프를 삭제합니다.", "/워프삭제 <워프이름>", new String[]{"delwarp","warpdel","dw","wd"});
		this.setPermission("delwarp.cmd");
		this.manager = new WarpManager();
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.hasPermission(this.getPermission())){
			sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
			return false;
		}
		try{
			if(this.manager.delWarp(args[0])) {
				sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프가 삭제되었습니다.");
				return true;
			}else{
				sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 삭제하지 못했습니다.");
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프의 이름을 설정해주세요");
			return false;
		}
	}

}
