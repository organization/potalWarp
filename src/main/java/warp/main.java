package warp;

import java.io.File;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class main extends PluginBase implements Listener{
public Config warps,signWarps,portals;
	@Override
	public void onEnable() {
		this.getDataFolder().mkdirs();
		this.warps = new Config(new File(this.getDataFolder(), "warps.json"), Config.JSON);
		this.signWarps = new Config(new File(this.getDataFolder(), "signWarps.json"), Config.JSON);
		this.portals = new Config(new File(this.getDataFolder(), "portals.json"), Config.JSON);
		this.getServer().getPluginManager().registerEvents(this, this);
		
	}

}
