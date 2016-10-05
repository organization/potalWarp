package warp;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import warp.commands.AddWarpCommand;
import warp.commands.DelWarpCommand;
import warp.commands.WarpCommand;

public class Warp extends PluginBase implements Listener {
	public static Warp instance;
	public Config warps;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		instance = this;
		this.warps = new Config(this.getDataFolder()+"/warps.json", Config.JSON);
		this.getServer().getCommandMap().register("워프추가", new AddWarpCommand());
		this.getServer().getCommandMap().register("워프삭제", new DelWarpCommand());
		this.getServer().getCommandMap().register("워프", new WarpCommand());
		super.onEnable();
	}
	@Override
	public void onDisable() {
		this.save();
		super.onDisable();
	}
	/*****/
	public void save(){
		this.warps.save();
	}
	public static Warp getInstance(){
		return instance;
	}
}
