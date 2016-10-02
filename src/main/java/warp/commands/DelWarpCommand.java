package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.Warp;
import warp.Manager.WarpManager;

public class DelWarpCommand extends Command {
	public WarpManager manager;
	
	public DelWarpCommand(Warp plugin){
		super("워프삭제","워프를 삭제합니다.","/워프삭제 <워프이름>",new String[]{"delwarp","warpdel","dw"});
		this.setPermission("delwarp.cmd");
		manager = new WarpManager(plugin);
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.hasPermission(this.getPermission())) sender.sendMessage(TextFormat.RED+"명령어의 사용권한이 없습니다.");
		else{
			try{
				if(manager.isWarp(args[0])){
					if(manager.delWarp(args[0])) sender.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"워프를 성공적으로 삭제하였습니다.");
					else sender.sendMessage(TextFormat.RED+"워프를 삭제하는데 문제가 발생하였습니다.");
				}else sender.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"같은 이름의 워프를 찾을 수 없습니다.");
			}catch(ArrayIndexOutOfBoundsException exception){
				sender.sendMessage(TextFormat.RED+"워프이름을 입력하세요.");
			}
		}
		return false;	
	}
}
