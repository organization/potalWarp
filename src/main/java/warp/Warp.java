package warp;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import warp.commands.AddWarpCommand;
import warp.commands.DelWarpCommand;
import warp.commands.ListWarpCommand;
import warp.commands.WarpCommand;
import warp.manager.PortalManager;
import warp.manager.WarpManager;

public class Warp extends PluginBase implements Listener {
	public static Warp instance;
	public Config warps,portals;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		instance = this;
		this.warps = new Config(this.getDataFolder()+"/warps.yml", Config.YAML);
		this.portals = new Config(this.getDataFolder()+"/portals.json", Config.JSON);
		this.getServer().getCommandMap().register("워프추가", new AddWarpCommand());
		this.getServer().getCommandMap().register("워프삭제", new DelWarpCommand());
		this.getServer().getCommandMap().register("워프", new WarpCommand());
		this.getServer().getCommandMap().register("워프목록", new ListWarpCommand());
		super.onEnable();
	}
	@Override
	public void onDisable() {
		this.save();
		super.onDisable();
	}
	
	/*event*/
	@EventHandler
	public void onChange(SignChangeEvent event){
		new PortalManager().addSignWarp(event);
	}
	@EventHandler
	public void onTouch(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if(event.getBlock().getId() == Item.SIGN_POST||event.getBlock().getId() == Item.WALL_SIGN){
			BlockEntitySign sign = (BlockEntitySign)event.getBlock().getLevel().getBlockEntity(
					new Vector3(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ()));
			if(sign.getText()[0].equals("§l§b[ Portal Sign ]")){
				if(new WarpManager().warp(sign.getText()[1], player)) player.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프되었습니다.");
				else player.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프하지 못했습니다.");
			}
		}
		return;
	}
        
        @EventHandler
	public void MovingCheck(PlayerMoveEvent event){
		Player player = event.getPlayer();
		int x = player.getFloorX();
		int y = player.getFloorY();
		int z = player.getFloorZ();
		
		if(player.level.getBlockIdAt(x, y, z) == Item.SIGN_POST || player.level.getBlockIdAt(x, y, z) == Item.WALL_SIGN){
			BlockEntitySign t = (BlockEntitySign) player.level.getBlockEntity(new Vector3(x, y, z));
			//아래 코드는 참고하여 사용하였습니다.
			if(t.getText()[0].equals("§l§b[ Portal Sign ]")){
				if(new WarpManager().warp(t.getText()[1], player)) player.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프되었습니다.");
				else player.sendMessage(TextFormat.RED+"[ 오류 ] "+TextFormat.GRAY+"워프하지 못했습니다.");
			}
		}
		//이 경우에는 출구에 워프를 생성하면 안될걸로 예상하는데,,		
	}
	/*****/
	public void save(){
		this.warps.save();
	}
	public static Warp getInstance(){
		return instance;
	}
}
