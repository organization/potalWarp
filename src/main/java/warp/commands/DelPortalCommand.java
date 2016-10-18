package warp.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.PortalManager;

/**
 * @author {mocha, Angelless}
 * 포탈을 삭제하는 커맨드입니다. <br />예시) "/포탈삭제 스폰입구" 처럼 사용합니다.
 */
public class DelPortalCommand extends Command{
    public PortalManager manager;

    /**
     * 커맨드의 생성자입니다. 커맨드의 기본설정을 합니다.
     */
    public DelPortalCommand(){
        super("포탈삭제","포탈을 삭제하는 커맨드입니다.","/포탈삭제 <포탈이름>",new String[]{"delportal", "portaldel", "dp"});
        this.setPermission("");
        manager = new PortalManager();
    }

    /**
     * 포탈삭제 커맨드를 실행하면 서버에서 실행되는 메서드입니다.
     * @param sender
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean execute(CommandSender sender, String label, String[] args){
        if(!sender.hasPermission(this.getPermission())){
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
            return false;
        }
        try{
            if(manager.isPortal(args[0])){
                if(this.manager.delPortal(args[0])) {
                    sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"포탈이 삭제되었습니다.");
                    return true;
                }else{
                    sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"포탈을 삭제하지 못했습니다.");
                    return false;
                }
            }else{
                sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"포탈을 찾을수없습니다.");
                return false;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"포탈의 이름을 입력해주세요");
            return false;
        }
    }
}