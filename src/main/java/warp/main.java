package warp;


public class main extends PluginBase implements Listener{
@Override
public void onEnable(){
this.getServer().getPluginManager().registerEvents(this,this);
}

}
