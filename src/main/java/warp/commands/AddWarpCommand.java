package warp.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

public class AddWarpCommand extends Command {
	public WarpManager manager;
	
	public AddWarpCommand(){
		super("워프추가", "워프를 추가합니다.", "/워프추가 <워프이름>", new String[]{"addwarp","warpadd","aw","wa"});
		this.setPermission("addwarp.cmd");
		this.manager = new WarpManager();
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.hasPermission(this.getPermission())){
			sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
			return false;
		}
		if(!sender.isPlayer()){
			sender.sendMessage(TextFormat.RED+"플레이어만 사용 가능합니다.");
			return false;
		}
		try{
			if(this.manager.addWarp(args[0], (Player)sender)) {
				sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프가 생성되었습니다.");
				return true;
			}else{
				sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 생성하지 못했습니다.");
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프의 이름을 설정해주세요");
			return false;
		}
	}

}
