package warp.manager;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;
import warp.Warp;

/**
 * @author {mocha. Angelless}
 * 포탈의 생성, 삭제, 확인 등 포탈과 관련된 여러 기능들을 하는 클래스입니다.
 */
public class PortalManager {
	public WarpManager manager;
	public Warp warp;

    /**
     * 이 클래스의 생성자입니다. 딱히 아무기능 없습니다.
     */
	public PortalManager(){
		this.warp = new Warp();
		this.manager = new WarpManager();
	}

    /**
     * SignPortal 을 생성하는 메서드 입니다.
     * @param event
     * {@link SignChangeEvent}
     */
	public void addSignWarp(SignChangeEvent event){
		Player player = event.getPlayer();
		String line_top = event.getLine(0);
		
		if(line_top.equals("포탈")||line_top.equals("portal")||line_top.equals("§l§b[ Portal Sign]")){
			if(!player.isOp()){
				player.sendMessage(TextFormat.RED+"포탈 생성권한이 없습니다.");
				event.setCancelled();
			}
			else if(!manager.isWarp(event.getLine(1))){
				player.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프를 찾을 수 없습니다.");
				event.setCancelled();
			}
			else{
				event.setLine(0, "§l§b[ Portal Sign ]");
				event.setLine(2, "§a"+event.getLine(1)+"으로 이동");
				player.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"포탈을 생성하였습니다.");
			}
		}
	}

	/**
     * Player 의 좌표 데이터를 이용해 포탈을 생성하는 메서드입니다.
	 * @param name
	 * @param player
	 * @param target
	 * @return
	 */
	public boolean addPortal(String name,Player player,String target){
		return this.addPortal(name, player.getX(),player.getY(),player.getZ(), player.getLevel() , target);
	}

    /**
     * 포탈을 생성하는 메서드입니다.
     * @param name
     * @param x
     * @param y
     * @param z
     * @param level
     * @param target
     * @return
     */
	public boolean addPortal(String name, double x, double y, double z, Level level, String target){
		if(!this.manager.isWarp(name)){
			return false;
		}
		try{
			LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

			map.put("x", x);
			map.put("y", y);
			map.put("z", y);
			map.put("level", level.getFolderName());
			map.put("target", target);

			warp.portals.set(name, map);
			warp.save();

			return true;
		}catch(Exception e){
			return false;
		}
	}

    /**
     * 포탈을 삭제하는 메서드입니다.
     * @param name
     * @return
     */
	public boolean delPortal(String name){
        try{
            this.warp.portals.remove(name);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 포탈이 존재하는지 확인 해 줍니다.
     * @param name
     * @return
     */
	public boolean isPortal(String name){
		return warp.portals.exists(name);
	}

    /**
     *
     */
	public void taskUpdate(){
        //TODO 뭐하는 메서드 입니까?
	}
}
