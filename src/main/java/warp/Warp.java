package warp;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import warp.commands.AddWarpCommand;
import warp.commands.DelWarpCommand;
import warp.commands.WarpListCommand;
import warp.commands.WarpCommand;

/************************************
 * @author mocha,angelless
 * warp,portal plugin
 ***********************************/
public class Warp extends PluginBase implements Listener {
	public Config warps;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		warps = new Config(getDataFolder()+"/warps.json",Config.JSON);
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
	/*=+=+=+=+=+=+=+*/
	public void save(){
		warps.save();
	}
}
