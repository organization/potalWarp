package warp.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.Warp;
import warp.Manager.WarpManager;

public class WarpCommand extends Command {
	public WarpManager manager;

	public WarpCommand(Warp plugin) {
		super("워프","워프합니다.","/워프 <워프이름>",new String[]{"이동","warp"});
		this.setPermission("warp.cmd");
		manager = new WarpManager(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.isPlayer()) sender.sendMessage(TextFormat.RED+"콘솔은 사용 불가능 합니다.");
		else{
			try{
				if(manager.isWarp(args[0])){
					Player player = (Player)sender;
					manager.warp(args[0], player);
					sender.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"이동되었습니다.");
				}else sender.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"워프를 찾을 수 없습니다.");
			}catch(ArrayIndexOutOfBoundsException e){
				sender.sendMessage(TextFormat.RED+"[오류]"+TextFormat.GRAY+" 워프이름을 입력 해 주세요.");
			}
		}
		return false;
	}

}
