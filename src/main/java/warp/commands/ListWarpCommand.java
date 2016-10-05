package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

public class ListWarpCommand extends Command {
	public WarpManager manager;
	
	public ListWarpCommand(){
		super("워프목록","이동가능한 위치를 봅니다.","/워프목록",new String[]{"listwarp","warplist","워프리스트"});
		this.manager = new WarpManager();
		this.setPermission("listwarp.cmd");
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		String result = "";
		for(String s : manager.getList()){
			result += s+", ";
		}
		sender.sendMessage("===워프 목록("+manager.getList().size()+"개) ===");
		sender.sendMessage(TextFormat.GREEN+result);
		return false;
	}

}
