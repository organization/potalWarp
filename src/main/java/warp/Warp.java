package warp;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import warp.Manager.PortalManager;
import warp.Manager.WarpManager;
import warp.commands.AddWarpCommand;
import warp.commands.DelWarpCommand;
import warp.commands.WarpCommand;
import warp.commands.WarpListCommand;

/************************************
 * @author mocha,angelless
 * warp,portal plugin
 ***********************************/
public class Warp extends PluginBase implements Listener {
	public Config warps,portals;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		warps = new Config(getDataFolder()+"/warps.json",Config.JSON);
		portals = new Config(getDataFolder()+"/portals.json",Config.JSON);
		this.getServer().getCommandMap().register("워프추가", new AddWarpCommand(this));
		this.getServer().getCommandMap().register("워프목록", new WarpListCommand(this));
		this.getServer().getCommandMap().register("워프삭제", new DelWarpCommand(this));
		this.getServer().getCommandMap().register("워프", new WarpCommand(this));
		super.onEnable();
	}
	@Override
	public void onDisable(){
		save();
	}
	/***********************event***************************/
	@EventHandler
	public void onSignChange(SignChangeEvent event){
		new PortalManager(this).addSignPortal(event);
	}
	@EventHandler(priority=EventPriority.NORMAL)
	public void onTouch(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		WarpManager manager = new WarpManager(this);
		
		if(block.getId() == Item.WALL_SIGN||block.getId() == Item.SIGN_POST){
			BlockEntitySign sign = (BlockEntitySign)block.getLevel().getBlockEntity(new Vector3(block.getX(),block.getY(),block.getZ()));
			if(sign.getText()[0].equalsIgnoreCase("§l§b[ Portal Sign ]")){
				if(manager.warp(sign.getText()[1], player))
					player.sendMessage(TextFormat.AQUA+"[알림] "+TextFormat.GRAY+"이동되었습니다.");
			}
		}
		return;
	}
	/*=+=+=+=+=+=+=+*/
	public void save(){
		warps.save();
	}
	public boolean isNone(String s){
		if(s == null) return true;
		else return false;
	}
}
