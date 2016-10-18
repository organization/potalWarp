package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.WarpManager;

/**
 * @author {mocha, Angelless}
 * 워프의 목록을 보는 커맨드입니다.<br />예시) "/워프목록" 처럼 사용합니다.
 */
public class ListWarpCommand extends Command {
	public WarpManager manager;
	
	public ListWarpCommand(){
		super("워프목록","이동가능한 위치를 봅니다.","/워프목록",new String[]{"listwarp","warplist","워프리스트"});
		this.manager = new WarpManager();
		this.setPermission("listwarp.cmd");
	}

	/**
	 * 워프목록 커맨드를 실행하면 서버에서 실행되는 메서드입니다.
	 * @param sender
	 * @param label
	 * @param args
	 * @return
	 */
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		String result = "";
		sender.sendMessage("===워프 목록("+manager.getList().size()+"개) ===");
		for(String s : manager.getList()){
			result += s+", ";
		}
		sender.sendMessage(TextFormat.GREEN+result);

		return true;
	}
}
