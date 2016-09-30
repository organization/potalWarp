package mocha.plugin.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import mocha.plugin.Warp;
import mocha.plugin.Manager.WarpManager;

public class AddWarpCommand extends Command {
	public WarpManager manager;

	public AddWarpCommand(Warp plugin) {
		super("워프추가","워프를 추가합니다.","/워프추가 <워프이름>",new String[] {"addwarp","aw","warpadd"});
		setPermission("mocha.plugin.addwarp.command");
		manager = new WarpManager(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!sender.hasPermission(this.getPermission())) sender.sendMessage(TextFormat.RED+"명령어의 권한을 가지고 있지 않습니다.");
		else if(sender.isPlayer()){
			try{
				if(manager.addWarp(args[0], (Player)sender))
					sender.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"워프 "+args[1]+"(이)가 생성되었습니다.");
				else sender.sendMessage(TextFormat.RED+"워프를 생성하는데 문제가 발생하였습니다.");
			}catch(NullPointerException e){
				sender.sendMessage(TextFormat.RED+"워프의 이름을 지정하여 주세요.");
			}
		}else sender.sendMessage(TextFormat.RED+"플레이어만 사용가능한 명령어 입니다.");
		return false;
	}

}
