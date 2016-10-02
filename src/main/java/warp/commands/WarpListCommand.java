package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.Warp;
import warp.Manager.WarpManager;

public class WarpListCommand extends Command {
	public WarpManager manager;
	
	public WarpListCommand(Warp plugin){
		super("워프목록","워프의 목록을 확인합니다.","/워프목록",new String[]{"warplist","wl","listwarp"});
		this.setPermission("warplist.cmd");
		this.manager = new WarpManager(plugin);
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		sender.sendMessage("=== 워프 목록("+manager.getList().length+"개) ===");
		String result = "";
		for(String s : manager.getList()){
			result += s+", ";
		}
		sender.sendMessage(TextFormat.GREEN+result);
		return true;
	}
}
