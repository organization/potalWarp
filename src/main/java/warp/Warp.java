package warp;

import java.io.File;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import warp.commands.AddWarpCommand;

public class Warp extends PluginBase implements Listener {
	public Config warps;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		getDataFolder().mkdirs();
		warps = new Config(getDataFolder()+File.separator+"warps.json",Config.JSON);
		this.getServer().getCommandMap().register("워프추가", new AddWarpCommand(this));
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
