package warp.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

/**
 * 워프를 하는 커맨드입니다. <br /> 예시) "/워프 스폰" 처럼 이용합니다.
 * @author {mocha, Angelless}
 */
public class WarpCommand extends Command {
	public WarpManager manager;

	/**
	 * 커맨드의 생성자입니다. 커맨드의 기본적인 설정을 합니다.
	 */
	public WarpCommand(){
		super("워프", "워프합니다.", "/워프 <워프이름>", new String[]{"warp","move","이동"});
		this.setPermission("warp.cmd");
		this.manager = new WarpManager();
	}

	/**
	 * 워프 커맨드를 실행하였을 때 서버에서 실행되는 메서드입니다.
	 * @param sender
	 * @param label
	 * @param args
	 * @return
	 */
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.isPlayer()){
			sender.sendMessage(TextFormat.RED+"플레이어만 사용 가능합니다.");
			return false;
		}
		try{
			if(manager.isWarp(args[0])){
				if(this.manager.warp(args[0], (Player)sender)) {
					sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프되었습니다.");
					return true;
				}else{
					sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프하지 못했습니다.");
					return false;
				}
			}else {
				sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 찾을수없습니다.");
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프의 이름을 설정해주세요");
			return false;
		}
	}

}
