package warp.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import warp.manager.PortalManager;
import warp.manager.WarpManager;

/**
 * @author {mocha, Angelless}
 * 포탈을 생성하는 커맨드입니다. <br />예시) "/포탈추가 스폰입구 스폰" 처럼 사용합니다.
 */
public class AddPortalCommand extends Command {
    public PortalManager manager;
    public WarpManager warpManager;

    /**
     * 커맨드의 생성자입니다. 커맨드의 기본설정을 합니다.
     */
    public AddPortalCommand(){
        super("포탈추가", "포탈을 추가하는 커맨드입니다", "/포탈추가 <포탈이름> <워프이름>",
                new String[] {"addportal","portaladd", "ap"});
        this.setPermission("addportal.cmd");
        manager = new PortalManager();
        warpManager = new WarpManager();
    }

    /**
     * 포탈추가 커맨드를 실행하면 서버에서 실행되는 메서드입니다.
     * @param sender
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean execute(CommandSender sender, String label, String[] args){
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            try{
                if(!warpManager.isWarp(args[1]))
                    sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 찾을수없습니다.");
                else{
                    if(manager.addPortal(args[0], (Player)sender, args[1])){
                        if(args[0].contains("s") || args[1].contains(".")){
                            sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"포탈이름이나 워프이름에는 특수문자(.)가 들어갈 수 없습니다.");
                        }else
                            sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"포탈이 생성되었습니다.");
                    }else
                        sender.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"포탈을 생성하지 못했습니다.");
                }
            }catch(ArrayIndexOutOfBoundsException e){
                sender.sendMessage(TextFormat.RED+"[ 오류 ]"+TextFormat.GRAY+this.getUsage());
            }
        }
        return false;
    }
}